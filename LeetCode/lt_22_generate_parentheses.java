// 題目：Generate Parentheses
// 給定 n 對括號，生成所有「合法的括號組合」。

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder cur, int open, int close, int n) {
        // 當字串長度達到 2n，代表已經生成一組合法括號
        if (cur.length() == n * 2) {
            res.add(cur.toString());
            return;
        }

        // 還可以放左括號
        if (open < n) {
            cur.append('(');
            backtrack(res, cur, open + 1, close, n);
            cur.deleteCharAt(cur.length() - 1); // 回溯
        }

        // 還可以放右括號（前提：已放的左括號數量 > 右括號數量）
        if (close < open) {
            cur.append(')');
            backtrack(res, cur, open, close + 1, n);
            cur.deleteCharAt(cur.length() - 1); // 回溯
        }
    }
}

/*
解題思路：
1) 使用回溯（Backtracking）生成所有可能組合。
2) 規則：
   - 只有在 open < n 時，才能放 '('。
   - 只有在 close < open 時，才能放 ')'。
3) 當字串長度等於 2 * n 時，代表已完成一個合法組合。

時間複雜度：O(4^n / sqrt(n))，為卡特蘭數的增長量。
空間複雜度：O(n)，遞迴深度與臨時字串長度。
*/
