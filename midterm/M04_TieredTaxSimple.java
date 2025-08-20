/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 每一筆收入只需要做一次級距判斷 O(1)。
 * 2. 共 n 筆輸入 → 總複雜度 O(n)。
 * 3. 輸出平均值也是 O(1)，不影響整體。
 */

import java.io.*;
import java.nio.charset.StandardCharsets;

public class M04_TieredTaxSimple {
    // 級距上限
    static final long[] BOUNDS = {120_000L, 500_000L, 1_000_000L};
    // 稅率（百分比）
    static final int[] RATE = {5, 12, 20, 30};
    // 速算扣除額（從題目範例反推）
    static final long[] QUICK = {0L, 8400L, 48400L, 148400L};

    static long taxOf(long income) {
        if (income <= BOUNDS[0]) return income * RATE[0] / 100 - QUICK[0];
        if (income <= BOUNDS[1]) return income * RATE[1] / 100 - QUICK[1];
        if (income <= BOUNDS[2]) return income * RATE[2] / 100 - QUICK[2];
        return income * RATE[3] / 100 - QUICK[3];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8)
        );
        int n = Integer.parseInt(br.readLine().trim());
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(br.readLine().trim());
            long tax = taxOf(income);
            sum += tax;
            sb.append("Tax: ").append(tax).append("\n");
        }
        long avg = Math.round(sum / (double) n);
        sb.append("Average: ").append(avg).append("\n");
        System.out.print(sb.toString());
    }
}
