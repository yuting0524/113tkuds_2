import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int i = 0, j = s.length() - 1;
        boolean ok = true;

        while (i < j) {
            char L = s.charAt(i);
            char R = s.charAt(j);

            // 只保留英文字母
            while (i < j && !isLetter(L)) { i++; L = s.charAt(i); }
            while (i < j && !isLetter(R)) { j--; R = s.charAt(j); }

            if (i < j) {
                if (toLower(L) != toLower(R)) { ok = false; break; }
                i++; j--;
            }
        }
        System.out.println(ok ? "Yes" : "No");
    }

    static boolean isLetter(char c){
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
    static char toLower(char c){
        if (c >= 'A' && c <= 'Z') return (char)(c - 'A' + 'a');
        return c;
    }
}
