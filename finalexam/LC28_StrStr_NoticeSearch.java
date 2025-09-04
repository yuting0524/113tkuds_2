import java.util.Scanner;

public class LC28_StrStr_NoticeSearch {
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String haystack = sc.next();
        String needle = sc.next();
        System.out.println(strStr(haystack, needle));
        sc.close();
    }
}
