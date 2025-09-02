// 題目：Regular Expression Matching
// 實作 isMatch(s, p)：判斷字串 s 是否能被模式 p 完整匹配
// 規則：'.' 可匹配任意單一字元；'*' 可匹配前一個元素的 0 次或多次。

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();

        // dp[i][j]：s 的前 i 個字元（s[0..i-1]）是否能被 p 的前 j 個字元（p[0..j-1]）匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字串配空樣式
        dp[0][0] = true;

        // 初始化第一列：s 為空、p 可能為像 "a*b*c*" 這種可消去的樣式
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                // '*' 把前一個元素消掉（使用 0 次）
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 逐步填表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1); // 目前樣式字元
                char sc = s.charAt(i - 1); // 目前字串字元

                if (pc == '*') {
                    // '*' 代表「前一個樣式字元」可用 0 次或多次
                    // 1) 用 0 次：跳過「前一個字元 + '*'」兩格
                    dp[i][j] = dp[i][j - 2];

                    // 2) 用 >=1 次：若 s 的當前字元可與「前一個樣式字元」匹配
                    //    則可消耗 s 的一個字元，仍用同一個 '*'（因此留在 j）
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else if (pc == '.' || pc == sc) {
                    // 一般匹配或 '.'：看子問題 dp[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 不相等且不是 '.' '*' → 無法匹配，維持 false
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}

/*
解題思路（DP）：
1. 定義狀態：
   dp[i][j] 表示 s[0..i-1] 是否能被 p[0..j-1] 完整匹配。

2. 邊界初始化：
   - dp[0][0] = true（空字串配空樣式）
   - 第一列（i=0）：當 p 形如 "a*b*c*" 等可完全被 '*' 消去時，dp[0][j] 可能為 true。
     轉移式：若 p[j-1]=='*' → dp[0][j] = dp[0][j-2]

3. 轉移：
   - 若 p[j-1] 是一般字元或 '.'：
       若 p[j-1] == s[i-1] 或 p[j-1]=='.'，則 dp[i][j] = dp[i-1][j-1]
   - 若 p[j-1] 是 '*'（代表「前一個字元」可重複）：
       設 prev = p[j-2]
       (a) 使用 0 次 prev：dp[i][j] = dp[i][j-2]
       (b) 使用 >=1 次 prev：若 prev=='.' 或 prev==s[i-1]，
           則 dp[i][j] |= dp[i-1][j]（吃掉 s 的一個字元，仍停在 j）

4. 回傳 dp[m][n] 即可。

5. 複雜度：
   - 時間：O(m*n)
   - 空間：O(m*n)（可進一步用兩行滾動陣列降為 O(n)）
*/
