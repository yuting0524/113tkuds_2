// 檔名：LC20_ValidParentheses_AlertFormat.java
// 讀入：一行括號字串（僅含 ()[]{} ，可為空）
// 輸出：true / false

import java.io.*;
import java.util.*;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        System.out.println(isValid(s));
    }

    private static boolean isValid(String s) {
        // 關 -> 開 的對應
        Map<Character, Character> pair = new HashMap<>();
        pair.put(')', '(');
        pair.put(']', '[');
        pair.put('}', '{');

        Deque<Character> st = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 若是開括號，推入堆疊
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            }
            // 若是關括號，需與堆疊頂相符
            else if (pair.containsKey(c)) {
                if (st.isEmpty() || st.peek() != pair.get(c)) return false;
                st.pop();
            } else {
                // 若出現非括號字元（理論上不會），視為非法
                return false;
            }
        }
        // 全部匹配則堆疊應清空
        return st.isEmpty();
    }
}
