// 檔名：LC03_NoRepeat_TaipeiMetroTap.java
// 讀入：一行字串 s（可為空）
// 輸出：最長「無重複字元子字串」長度（整數）

import java.io.*;
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";                  // 若沒有輸入，視為空字串

        // 假設可見 ASCII：使用 256 大小的 last index 陣列，比 HashMap 更快
        int[] last = new int[256];
        Arrays.fill(last, -1);

        int left = 0;                           // 滑動視窗左界
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) & 0xFF;       // 轉為 0..255
            if (last[ch] >= left) {            // 遇到重複字元：左界跳到其上一位置的右邊
                left = last[ch] + 1;
            }
            last[ch] = i;                      // 更新該字元最後位置
            ans = Math.max(ans, i - left + 1); // 視窗長度
        }

        System.out.println(ans);
    }
}
