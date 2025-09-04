// 檔名：LC18_4Sum_Procurement.java
// 題目 9：採購限額 4Sum
// 讀入：第一行 n、target；第二行 n 個整數（價格）。
// 輸出：所有不重複的升序四元組（每行四個遞增整數；若無解則不輸出）。
// 解法：排序後雙層枚舉 i、j，內層以兩指針夾逼成 2-sum，並對 i、j、L、R 嚴格去重。
// 複雜度：O(n^3)

import java.io.*;
import java.util.*;

public class LC18_4Sum_Procurement {

    // 簡單的讀取器：先讀 n 與 target，再讀 n 個數
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        Long nextLong() throws IOException {
            String s = next();
            return s == null ? null : Long.parseLong(s);
        }
        Integer nextInt() throws IOException {
            String s = next();
            return s == null ? null : Integer.parseInt(s);
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();

        Integer nObj = fs.nextInt();
        if (nObj == null) return;
        int n = nObj;
        long target = fs.nextLong();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            Long v = fs.nextLong();
            if (v == null) { i--; continue; } // 保險：若行尾有雜訊，等下一個 token
            a[i] = v;
        }

        Arrays.sort(a);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue; // 去重 i
            // 剪枝（可選）：若最小四數已超過 target 或最大四數仍小於 target，可提早結束/跳過
            // 若 target、值範圍很大亦可略過剪枝以保可讀性

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) continue; // 去重 j

                int L = j + 1, R = n - 1;
                long need = target - a[i] - a[j];
                while (L < R) {
                    long two = a[L] + a[R];
                    if (two == need) {
                        out.append(a[i]).append(' ')
                           .append(a[j]).append(' ')
                           .append(a[L]).append(' ')
                           .append(a[R]).append('\n');

                        // 內層去重
                        long vL = a[L], vR = a[R];
                        do { L++; } while (L < R && a[L] == vL);
                        do { R--; } while (L < R && a[R] == vR);
                    } else if (two < need) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }

        // 若無任何組合，按題意不輸出
        System.out.print(out.toString());
    }
}
