// 檔名：LC17_PhoneCombos_CSShift.java
// 題目 8：手機門號組合（Phone keypad）
// 讀入一行字串 digits（只包含 '2'~'9'），逐位對應到電話鍵盤的字母並輸出所有字母組合。
// 輸出：每行一組合；若輸入為空字串則不輸出任何東西。

import java.util.*;

public class LC17_PhoneCombos_CSShift {
    private static final String[] MAP = {
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        String digits = sc.nextLine().trim();

        // 空字串：不輸出
        if (digits.isEmpty()) return;

        // 產生並逐行輸出
        StringBuilder path = new StringBuilder();
        dfs(digits, 0, path);
    }

    private static void dfs(String digits, int idx, StringBuilder path) {
        if (idx == digits.length()) {
            System.out.println(path.toString());
            return;
        }
        char d = digits.charAt(idx);
        // 假設輸入只會是 '2'~'9'；若要防呆，碰到其他字元可直接略過或結束
        String letters = MAP[d - '2'];
        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            dfs(digits, idx + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
