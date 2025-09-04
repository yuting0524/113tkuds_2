// 題目：Substring with Concatenation of All Words
// 給定字串 s 與字串陣列 words（每個 word 長度相同），
// 找出 s 中所有起始索引，使得從該索引起的長度 = words.length * wordLen 的子字串
// 恰好是 words 的某個排列串接（每個 word 使用次數與 words 中一致）。

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return ans;

        int n = s.length();
        int k = words.length;
        int m = words[0].length();
        int total = k * m;
        if (n < total) return ans;

        // 需求表：每個單字需要出現幾次
        Map<String, Integer> need = new HashMap<>();
        for (String w : words) need.put(w, need.getOrDefault(w, 0) + 1);

        // 依照 word 長度做 m 種偏移的滑動視窗（確保切片對齊單字邊界）
        for (int offset = 0; offset < m; offset++) {
            int left = offset;                    // 視窗左端（對齊 word 邊界）
            int count = 0;                        // 視窗內 word 的數量
            Map<String, Integer> window = new HashMap<>();

            // 右端以 word 長度為步長前進
            for (int right = offset; right + m <= n; right += m) {
                String w = s.substring(right, right + m);

                if (need.containsKey(w)) {
                    // 放入當前 word
                    window.put(w, window.getOrDefault(w, 0) + 1);
                    count++;

                    // 若某個 word 超量，從左端縮小直到不超量
                    while (window.get(w) > need.get(w)) {
                        String lw = s.substring(left, left + m);
                        window.put(lw, window.get(lw) - 1);
                        left += m;
                        count--;
                    }

                    // 視窗剛好包含 k 個 word → 一個解
                    if (count == k) {
                        ans.add(left);

                        // 視窗右移一個 word，繼續找下一個
                        String lw = s.substring(left, left + m);
                        window.put(lw, window.get(lw) - 1);
                        left += m;
                        count--;
                    }
                } else {
                    // 非需求單字：清空視窗，從下一個位置重新開始
                    window.clear();
                    count = 0;
                    left = right + m;
                }
            }
        }
        return ans;
    }
}

/*
解題思路（滑動視窗 + HashMap）：
1) 建立 need 映射表：記錄 words 中每個單字的需求次數。
2) 因所有單字長度固定為 m，對 m 種「起點偏移」各做一次滑動視窗（確保切片對齊）。
3) 視窗步長為 m：
   - 若當前切片 w 在 need 中 → 加入 window，若某字超量則從左端縮小視窗。
   - 當視窗內單字數量 count == k，代表找到一個合法起點 left，收錄後把左端再右移一格以繼續尋找。
   - 若 w 不在 need 中 → 清空 window，從下一個對齊位置重新開始。
4) 時間複雜度：O(m * (n / m)) ≈ O(n)。每個位置最多進出一次。
   空間複雜度：O(Σ不同單字數) ≤ O(k)。
*/
