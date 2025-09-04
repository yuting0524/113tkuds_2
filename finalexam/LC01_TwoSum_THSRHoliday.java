// 檔名：LC01_TwoSum_THSRHoliday.java
// 讀入：第一行 n 與 target；第二行 n 個整數
// 輸出：兩個索引（0-based），或 -1 -1（若無解）

import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 第一行：n 與 target
        st = new StringTokenizer(nextNonEmptyLine(br));
        int n = Integer.parseInt(st.nextToken());
        long target = Long.parseLong(st.nextToken()); // 以 long 避免加總溢位

        // 第二行：n 個整數
        long[] nums = new long[n];
        st = new StringTokenizer(nextNonEmptyLine(br));
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) { // 若數字跨多行也能處理
                st = new StringTokenizer(nextNonEmptyLine(br));
            }
            nums[i] = Long.parseLong(st.nextToken());
        }

        // HashMap<需要的數值, 其對應索引>
        Map<Long, Integer> need = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long x = nums[i];
            if (need.containsKey(x)) {
                System.out.println(need.get(x) + " " + i);
                return;
            }
            long want = target - x;
            // 記錄「想配到的值 -> 目前索引」
            need.put(want, i);
        }

        // 無解
        System.out.println("-1 -1");
    }

    // 讀掉空行
    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) return line;
        }
        return "";
    }
}
