// 檔名：AdvancedStringRecursion.java
import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        System.out.println("1. 所有排列：");
        permute("", "abc");

        System.out.println("\n2. 字串匹配 'world' 是否在 'helloworld'? → " + match("helloworld", "world", 0));
        System.out.println("   字串匹配 'abc' 是否在 'helloworld'? → " + match("helloworld", "abc", 0));

        System.out.println("\n3. 移除重複字元：'banana' → " + removeDuplicates("banana"));

        System.out.println("\n4. 子字串組合（Power Set）：");
        powerSet("abc", "", 0);
    }

    // 1. 遞迴產生字串的所有排列組合（全排列）
    public static void permute(String prefix, String str) {
        if (str.length() == 0) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            permute(prefix + str.charAt(i),
                    str.substring(0, i) + str.substring(i + 1));
        }
    }

    // 2. 遞迴字串匹配：是否包含 pattern
    public static boolean match(String text, String pattern, int index) {
        if (index + pattern.length() > text.length()) return false;
        if (text.substring(index, index + pattern.length()).equals(pattern)) return true;
        return match(text, pattern, index + 1);
    }

    // 3. 遞迴移除字串中的重複字符（保留第一次出現）
    public static String removeDuplicates(String str) {
        return removeDuplicatesHelper(str, "", new HashSet<>());
    }

    private static String removeDuplicatesHelper(String remaining, String result, Set<Character> seen) {
        if (remaining.length() == 0) return result;
        char ch = remaining.charAt(0);
        if (!seen.contains(ch)) {
            seen.add(ch);
            result += ch;
        }
        return removeDuplicatesHelper(remaining.substring(1), result, seen);
    }

    // 4. 遞迴列出所有子字串組合（power set）
    public static void powerSet(String str, String current, int index) {
        if (index == str.length()) {
            System.out.println(current);
            return;
        }
        // 包含 str.charAt(index)
        powerSet(str, current + str.charAt(index), index + 1);
        // 不包含
        powerSet(str, current, index + 1);
    }
}
