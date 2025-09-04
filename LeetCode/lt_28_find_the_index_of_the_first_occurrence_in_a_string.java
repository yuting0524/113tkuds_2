// 題目：Find the Index of the First Occurrence in a String
// 給定字串 haystack 與 needle，回傳 needle 在 haystack 中第一次出現的索引，若不存在則回傳 -1。

class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // 遍歷所有可能的起始位置（最多到 n - m）
        for (int i = 0; i <= n - m; i++) {
            // 截取子字串並比對
            if (haystack.substring(i, i + m).equals(needle)) {
                return i;  // 找到第一次出現的位置
            }
        }
        return -1; // 沒找到
    }
}

/*
解題思路：
1. 題目本質為字串匹配，直接用滑動視窗暴力搜尋。
2. 從 haystack[0...n-m] 依序檢查長度 m 的子字串是否等於 needle。
3. 第一個符合的索引即為答案；若都沒找到，回傳 -1。

時間複雜度：O((n-m+1) * m) ≈ O(n*m)，n 為 haystack 長度，m 為 needle 長度。
空間複雜度：O(1)。
*/
