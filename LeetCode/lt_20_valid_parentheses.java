// 題目：Valid Parentheses
// 給定一個只包含 '(', ')', '{', '}', '[' 和 ']' 的字串 s，判斷字串是否為有效括號。
// 有效條件：
// 1. 左括號必須由相同型別的右括號關閉。
// 2. 左括號必須以正確的順序關閉。

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            // 遇到左括號就推入 stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 若遇到右括號，但 stack 為空 → 無效
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                // 判斷是否匹配
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        // stack 最後必須為空才是有效字串
        return stack.isEmpty();
    }
}

/*
解題思路：
1) 使用堆疊（stack）來匹配括號。
2) 遍歷字串：
   - 遇到左括號 ('(', '{', '[') → push。
   - 遇到右括號 → 檢查 stack 頂端是否為對應的左括號，若不是或 stack 為空則回傳 false。
3) 遍歷完成後，若 stack 為空 → 有效，否則 false。

時間複雜度：O(n)，需遍歷字串一次。
空間複雜度：O(n)，最壞情況所有字元都是左括號。
*/
