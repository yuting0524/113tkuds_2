import java.io.*;

/**
 * 題目 19 延誤等級首末定位
 * 讀入：
 *   n target
 *   n 個已排序（非遞減）的整數
 * 輸出：
 *   target 在序列中的起始索引與結束索引；若不存在則輸出 "-1 -1"
 *
 * 作法：兩次二分搜尋
 *   left = lowerBound(nums, target)
 *   right = lowerBound(nums, target + 1) - 1
 *   若 left == n 或 nums[left] != target -> -1 -1，否則輸出 left right
 * 時間複雜度 O(log n)，空間 O(1)
 */
public class LC34_SearchRange_DelaySpan {

    // 專用快速輸入
    private static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        long nextLong() throws IOException {
            int c; 
            while ((c = read()) <= ' ' && c != -1);
            boolean neg = false;
            if (c == '-') { neg = true; c = read(); }
            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }
            return neg ? -val : val;
        }
        int nextInt() throws IOException { return (int) nextLong(); }
    }

    // lower_bound: 回傳第一個 >= target 的索引（若皆小於 target 則回傳 n）
    private static int lowerBound(int[] a, int target) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= target) r = m;
            else l = m + 1;
        }
        return l;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();
        int target = fs.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = fs.nextInt();

        if (n == 0) {
            System.out.println("-1 -1");
            return;
        }

        int left = lowerBound(nums, target);
        if (left == n || nums[left] != target) {
            System.out.println("-1 -1");
            return;
        }
        int right = lowerBound(nums, target + 1) - 1;

        System.out.println(left + " " + right);
    }
}
