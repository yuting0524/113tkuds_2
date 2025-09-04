// 題目：Longest Valid Parentheses
// 給定只包含 '(' 與 ')' 的字串 s，回傳最長「有效括號子字串」的長度。

class Solution {
    public int longestValidParentheses(String s) {
        // 觀念：用堆疊存放「尚未匹配的索引」。
        // 放入一個哨兵索引 -1，方便計算長度。
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);

        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 左括號：索引入棧，等待匹配
                stack.push(i);
            } else { // c == ')'
                // 嘗試用棧頂的左括號來匹配
                stack.pop();

                if (stack.isEmpty()) {
                    // 沒東西可配，將當前右括號索引作為新的「無效邊界」
                    stack.push(i);
                } else {
                    // 當前有效區間長度 = i - 上一個未匹配位置
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}

/*
解題思路（棧 + 哨兵）：
1) 棧存放「尚未匹配」的索引；先推入 -1 做哨兵以處理起點計算。
2) 遇 '(' → 索引入棧；遇 ')' → 彈出一個索引嘗試匹配。
3) 若彈出後棧空，表示右括號過多，將當前索引作為新的無效邊界（push i）。
4) 否則，當前最長有效長度 = i - 棧頂索引（上一個未匹配位置）。

時間複雜度：O(n)
空間複雜度：O(n)（最壞情況全是 '('）
*/
