// 題目：Count and Say
// 定義數數並說序列 (count-and-say)。
// 基本：countAndSay(1) = "1"
// 遞迴：countAndSay(n) = 對 countAndSay(n-1) 做 run-length encoding（連續相同數字數量+該數字）。
// 例如：n=4 → "1211"。

class Solution {
    public String countAndSay(int n) {
        // 基本情況
        if (n == 1) return "1";

        // 從 2 開始依次生成
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = buildNext(result);
        }
        return result;
    }

    // 輔助函數：將當前字串轉換為下一項
    private String buildNext(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }
}

/*
解題思路：
1. 題目是 Run-Length Encoding 的應用。
2. n=1 時返回 "1"，之後每次用上一項字串生成下一項。
   - 例如 "21" → "1211"（一個2、一個1）。
3. 使用 StringBuilder 將字串轉換更高效。
4. 時間複雜度：O(m*n)，其中 m 為生成字串的平均長度。
5. 空間複雜度：O(m)。
*/
