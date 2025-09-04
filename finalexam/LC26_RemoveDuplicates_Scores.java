// 檔名：LC26_RemoveDuplicates_Scores.java
import java.util.*;

public class LC26_RemoveDuplicates_Scores {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int write = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[write] = nums[i];
                write++;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入 n
        int n = sc.nextInt();
        int[] nums = new int[n];

        // 輸入陣列
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // 呼叫函式
        int newLen = removeDuplicates(nums);

        // 輸出結果
        System.out.println(newLen);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }
}
