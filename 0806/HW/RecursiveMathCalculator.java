public class RecursiveMathCalculator {
    public static void main(String[] args) {
        // 組合數 C(n, k)
        System.out.println("C(5, 2) = " + combination(5, 2));

        // 卡塔蘭數 Catalan(n)
        System.out.println("Catalan(4) = " + catalan(4));

        // 漢諾塔步數 Hanoi(n)
        System.out.println("Hanoi(3) = " + hanoiMoves(3));

        // 回文數判斷
        System.out.println("Is 12321 a palindrome? " + isPalindrome(12321));
        System.out.println("Is 12345 a palindrome? " + isPalindrome(12345));
    }

    // 1. 計算組合數 C(n, k) = C(n-1,k-1) + C(n-1,k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 2. 計算卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n == 0 || n == 1) return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 3. 漢諾塔移動步數 hanoi(n) = 2 * hanoi(n-1) + 1
    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 4. 判斷是否為回文數（數字）
    public static boolean isPalindrome(int num) {
        return num == reverse(num, 0);
    }

    // 輔助：反轉數字
    private static int reverse(int num, int rev) {
        if (num == 0) return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }
}
