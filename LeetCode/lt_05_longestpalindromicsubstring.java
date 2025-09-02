// 題目：Longest Palindromic Substring
// 給定字串 s，回傳 s 中最長的回文子字串。

class Solution {
    public String longestPalindrome(String s) {
        // 邊界處理：空字串或長度為 1 直接回傳
        if (s == null || s.length() < 2) return s;

        int start = 0, end = 0; // 紀錄目前找到的最長回文範圍 [start, end]

        // 以每個索引為「中心」嘗試往左右擴展
        for (int i = 0; i < s.length(); i++) {
            // case1：奇數長度回文（單一中心，例如 "aba" 的 'b'）
            int len1 = expandFromCenter(s, i, i);
            // case2：偶數長度回文（雙中心，例如 "abba" 的中間兩個 'b'）
            int len2 = expandFromCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            // 若較長，更新最長範圍：中心在 i，長度 len
            if (len > end - start + 1) {
                // 由中心與長度反推起訖位置
                int half = (len - 1) / 2;
                start = i - half;
                end   = i + len / 2; // 奇偶皆適用的小技巧
            }
        }
        return s.substring(start, end + 1);
    }

    // 從給定的左右指標向外擴展，回傳能形成的回文長度
    private int expandFromCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 跳出時 left、right 已經越界或不相等，所以長度是 (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}

/*
解題思路（中心擴展法）：
1. 回文的特性是「左右對稱」。最長回文一定能以某個「中心」向外對稱擴展。
2. 針對每個索引 i，各自處理兩種中心：
   - 奇數長度：以 i 為中心（left=i, right=i）
   - 偶數長度：以 i 和 i+1 為中心（left=i, right=i+1）
3. 從中心向外比對字元相等就持續擴展，直到不相等或越界，計算該回文長度。
4. 全程追蹤最長的 [start, end] 範圍，最後回傳 s.substring(start, end+1)。
5. 複雜度：
   - 時間 O(n^2)：每個中心最壞向外擴展 O(n)，共有 O(n) 個中心。
   - 空間 O(1)：只用到幾個指標與變數。
備註：若需 O(n) 可用 Manacher，但實作較複雜；本法易寫、易懂且在 n ≤ 1000 足夠通過。
*/
