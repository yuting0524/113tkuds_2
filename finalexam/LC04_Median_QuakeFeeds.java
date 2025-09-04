// 檔名：LC04_Median_QuakeFeeds.java

import java.util.*;

public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 輸入 n, m
        int n = sc.nextInt();
        int m = sc.nextInt();
        
        double[] nums1 = new double[n];
        double[] nums2 = new double[m];
        
        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextDouble();
        }
        for (int j = 0; j < m; j++) {
            nums2[j] = sc.nextDouble();
        }
        
        double ans = findMedianSortedArrays(nums1, nums2);
        
        // 輸出 (保留 1 位小數)
        System.out.printf("%.1f\n", ans);
    }
    
    // 主函式：找兩個已排序陣列的中位數
    public static double findMedianSortedArrays(double[] A, double[] B) {
        if (A.length > B.length) {
            return findMedianSortedArrays(B, A); // 確保 A 是短的
        }
        
        int n = A.length, m = B.length;
        int totalLeft = (n + m + 1) / 2;
        
        int left = 0, right = n;
        while (left <= right) {
            int i = (left + right) / 2;
            int j = totalLeft - i;
            
            double Aleft = (i == 0) ? Double.NEGATIVE_INFINITY : A[i - 1];
            double Aright = (i == n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j == 0) ? Double.NEGATIVE_INFINITY : B[j - 1];
            double Bright = (j == m) ? Double.POSITIVE_INFINITY : B[j];
            
            if (Aleft <= Bright && Bleft <= Aright) {
                if ((n + m) % 2 == 1) {
                    return Math.max(Aleft, Bleft);  // 奇數長度
                } else {
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;  // 偶數長度
                }
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        
        throw new IllegalArgumentException("輸入錯誤");
    }
}
