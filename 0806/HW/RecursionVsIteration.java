public class RecursionVsIteration {
    public static void main(String[] args) {
        System.out.println("1. 二項式係數 C(5,2)：");
        System.out.println("遞迴：" + combinationRec(5, 2));
        System.out.println("迭代：" + combinationIter(5, 2));

        System.out.println("\n2. 陣列元素乘積 {2,3,4}：");
        int[] arr = {2, 3, 4};
        System.out.println("遞迴：" + productRec(arr, 0));
        System.out.println("迭代：" + productIter(arr));

        System.out.println("\n3. 字串中元音數量 'Beautiful'：");
        System.out.println("遞迴：" + countVowelsRec("Beautiful", 0));
        System.out.println("迭代：" + countVowelsIter("Beautiful"));

        System.out.println("\n4. 括號是否配對正確 '(()())'：");
        System.out.println("遞迴：" + isBalancedRec("(()())", 0, 0));
        System.out.println("迭代：" + isBalancedIter("(()())"));

        System.out.println("括號是否配對正確 '(()))('：");
        System.out.println("遞迴：" + isBalancedRec("(()))(", 0, 0));
        System.out.println("迭代：" + isBalancedIter("(()))("));
    }

    // 1. 組合 C(n,k)
    public static int combinationRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combinationRec(n - 1, k - 1) + combinationRec(n - 1, k);
    }

    public static int combinationIter(int n, int k) {
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    // 2. 陣列元素乘積
    public static int productRec(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRec(arr, index + 1);
    }

    public static int productIter(int[] arr) {
        int prod = 1;
        for (int num : arr) prod *= num;
        return prod;
    }

    // 3. 計算元音字母數量
    public static int countVowelsRec(String str, int index) {
        if (index == str.length()) return 0;
        char c = Character.toLowerCase(str.charAt(index));
        return (isVowel(c) ? 1 : 0) + countVowelsRec(str, index + 1);
    }

    public static int countVowelsIter(String str) {
        int count = 0;
        for (char c : str.toLowerCase().toCharArray()) {
            if (isVowel(c)) count++;
        }
        return count;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    // 4. 檢查括號配對（只考慮 ()）
    public static boolean isBalancedRec(String str, int index, int open) {
        if (open < 0) return false;
        if (index == str.length()) return open == 0;
        char c = str.charAt(index);
        if (c == '(') return isBalancedRec(str, index + 1, open + 1);
        else if (c == ')') return isBalancedRec(str, index + 1, open - 1);
        else return isBalancedRec(str, index + 1, open);
    }

    public static boolean isBalancedIter(String str) {
        int open = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') open++;
            else if (c == ')') open--;
            if (open < 0) return false;
        }
        return open == 0;
    }
}
