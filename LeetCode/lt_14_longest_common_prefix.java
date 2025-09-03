// 題目：Longest Common Prefix
// 給定一組字串陣列 strs，找出所有字串的最長共同前綴字串。
// 若沒有共同前綴，回傳空字串 ""。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串是最長前綴
        String prefix = strs[0];

        // 從第二個字串開始逐一比較
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到當前字串包含它為止
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 初始化 prefix = 第一個字串。
2. 從第二個字串開始逐一比較，檢查當前字串是否以 prefix 開頭。
   - 如果不是，縮短 prefix（去掉最後一個字元）。
   - 重複直到符合或 prefix 變成空字串。
3. 所有字串檢查完後，剩下的 prefix 就是最長共同前綴。

時間複雜度：O(n * m)，n = 字串數量，m = 最長字串長度。
空間複雜度：O(1)，僅用到常數額外空間。
*/
