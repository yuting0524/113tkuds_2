import java.io.*;
import java.util.*;

/**
 * 題目 14 班表 k 組反轉
 * 讀入：第一個整數為 k，其後為一串整數序列（可為空）。
 * 輸出：反轉後的序列（同一行，以空白分隔）。
 *
 * 例：
 *  輸入
 *   2
 *   1 2 3 4 5
 *  輸出
 *   2 1 4 3 5
 *
 * 時間複雜度 O(n)，空間 O(1)（原地反轉）
 */
public class LC25_ReverseKGroup_Shifts {

    // 快速讀取所有整數：第一個是 k，其餘是陣列
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) { this.in = is; }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        Integer nextInt() throws IOException {
            int c, sign = 1, val = 0;
            // 跳過非數字
            do {
                c = read();
                if (c == -1) return null; // EOF
            } while (c <= ' ');

            if (c == '-' ) { sign = -1; c = read(); }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return val * sign;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        // 讀 k，若完全沒有輸入則不輸出任何東西
        Integer kBox = fs.nextInt();
        if (kBox == null) return;
        int k = kBox;

        // 讀取剩餘所有整數作為序列
        List<Integer> list = new ArrayList<>();
        Integer x;
        while ((x = fs.nextInt()) != null) list.add(x);

        int n = list.size();
        if (k <= 1 || n == 0) { // 不需要反轉的情況
            printList(list);
            return;
        }

        // 轉成陣列以原地操作
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = list.get(i);

        // 以 k 為一組做反轉
        for (int start = 0; start + k <= n; start += k) {
            reverse(a, start, start + k - 1);
        }

        // 輸出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.print(sb.toString());
    }

    // 反轉 a[l..r]
    private static void reverse(int[] a, int l, int r) {
        while (l < r) {
            int tmp = a[l];
            a[l] = a[r];
            a[r] = tmp;
            l++; r--;
        }
    }

    private static void printList(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(list.get(i));
        }
        System.out.print(sb.toString());
    }
}
