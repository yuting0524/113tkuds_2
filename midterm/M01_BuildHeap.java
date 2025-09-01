/*
 * Time Complexity: O(n)
 * 說明: 自底向上從最後一個非葉節點開始做 siftDown（heapifyDown），
 *      每個節點攤銷成本為 O(1)，總計 O(n)。等值不交換以維持相對次序。
 */

import java.io.*;

public class M01_BuildHeap {
    // ---------- Fast IO ----------
    static class FastScanner {
        private final BufferedInputStream in = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private int read() {
            if (ptr >= len) {
                try { len = in.read(buffer); ptr = 0; }
                catch (IOException e) { return -1; }
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        String next() {
            StringBuilder sb = new StringBuilder();
            int c;
            do c = read(); while (c <= 32 && c != -1);
            while (c > 32 && c != -1) { sb.append((char)c); c = read(); }
            return sb.toString();
        }
        int nextInt() {
            int c, s = 1, x = 0;
            do c = read(); while (c <= 32);
            if (c == '-') { s = -1; c = read(); }
            while (c > 32) { x = x * 10 + (c - '0'); c = read(); }
            return x * s;
        }
        long nextLong() {
            int c, s = 1; long x = 0;
            do c = read(); while (c <= 32);
            if (c == '-') { s = -1; c = read(); }
            while (c > 32) { x = x * 10 + (c - '0'); c = read(); }
            return x * s;
        }
        String nextLine() {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c != '\n') sb.append((char)c);
            return sb.toString();
        }
    }

    static FastScanner fs = new FastScanner();

    public static void main(String[] args) {
        String type = fs.next();        // "max" or "min"
        int n = fs.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        boolean isMax = type.equals("max");

        // build-heap: bottom-up
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(a, i, n, isMax);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    // 0-based heap; 等值不交換以維持相對次序
    static void siftDown(long[] a, int i, int n, boolean isMax) {
        while (true) {
            int l = i * 2 + 1, r = l + 1, b = i;
            if (l < n && better(a[l], a[b], isMax)) b = l;
            if (r < n && better(a[r], a[b], isMax)) b = r;
            if (b == i) break;
            long t = a[i]; a[i] = a[b]; a[b] = t;
            i = b;
        }
    }

    // 回傳 child 是否比 parent 更適合當父節點
    static boolean better(long child, long parent, boolean isMax) {
        if (isMax) return child > parent; // 僅在更大時交換；同值不換
        else       return child < parent; // 僅在更小時交換；同值不換
    }
}
