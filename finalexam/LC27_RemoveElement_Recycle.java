import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static int removeElement(int[] nums, int val) {
        int write = 0;
        for (int x : nums) {
            if (x != val) {
                nums[write++] = x;  // 只保留不等於 val 的數字
            }
        }
        return write; // 新長度
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 輸入 n 和 val
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] nums = new int[n];
        
        // 輸入陣列
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        // 呼叫函數
        int newLen = removeElement(nums, val);
        
        // 輸出新長度
        System.out.println(newLen);
        
        // 輸出前段結果
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
        
        sc.close();
    }
}
