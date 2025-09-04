// 題目 6 連假油量促銷最大區間
// 檔名：LC11_MaxArea_FuelHoliday.java
// 輸入：n，接著一行 n 個高度
// 輸出：最大面積（以 long 印出，避免溢位）

import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] heights = new long[n];
        for (int i = 0; i < n; i++) heights[i] = sc.nextLong();
        System.out.println(maxArea(heights));
    }

    // 兩指針夾擠法：每次取較短邊並向內移動，以尋找更高的可能
    private static long maxArea(long[] h) {
        int l = 0, r = h.length - 1;
        long best = 0;
        while (l < r) {
            long area = (long) (r - l) * Math.min(h[l], h[r]);
            if (area > best) best = area;
            if (h[l] <= h[r]) l++;  // 移動較短邊
            else r--;
        }
        return best;
    }
}
