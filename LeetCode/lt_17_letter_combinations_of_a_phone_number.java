// 題目：Letter Combinations of a Phone Number
// 給定一個只包含數字 2~9 的字串 digits，回傳所有可能的字母組合（電話按鍵對應）。
// 若 digits 為空，回傳空陣列。

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;

        // 數字到字母的對應表（2~9）
        String[] map = new String[]{
            "",     // 0 (不用)
            "",     // 1 (沒有對應字母)
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        // 用回溯產生所有組合
        backtrack(digits, 0, new StringBuilder(), map, res);
        return res;
    }

    // idx：目前處理到 digits 的第幾位
    private void backtrack(String digits, int idx, StringBuilder path,
                           String[] map, List<String> res) {
        // 終止條件：已經產生與 digits 同長度的字串
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }

        String letters = map[digits.charAt(idx) - '0']; // 取出此位數字對應的字母們
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));            // 選擇一個字母
            backtrack(digits, idx + 1, path, map, res);// 往下一位遞迴
            path.deleteCharAt(path.length() - 1);      // 撤銷選擇（回溯）
        }
    }
}

/*
解題思路：
1) 建立 2~9 到字母的映射表。
2) 透過回溯（DFS）：每一位數字枚舉其對應字母，組合成長度等於 digits 的字串即收集。
3) 若 digits 為空，直接回傳空陣列。

時間複雜度：O(3^n * 4^m)，n/m 分別為對應 3 個字母與 4 個字母（7、9）的位數。
空間複雜度：O(n)（遞迴深度與路徑字串長度）。
*/
