// 題目 5 北捷進出站最長有效片段
// 檔名：LC32_LongestValidParen_Metro.java

import java.util.*;

public class LC32_LongestValidParen_Metro {
    public static int longestValidParentheses(String s) {
        // 使用 stack 儲存索引，初始化放入 -1 方便計算
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // 左括號，壓入索引
                stack.push(i);
            } else {
                // 右括號，先彈出
                stack.pop();
                if (stack.isEmpty()) {
                    // 若已空，重置基準點
                    stack.push(i);
                } else {
                    // 更新最大長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 輸入一行括號字串
        String s = sc.nextLine();
        System.out.println(longestValidParentheses(s));
    }
}
