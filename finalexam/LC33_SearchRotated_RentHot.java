// 題目 18 旋轉陣列搜尋
// 檔名：LC33_SearchRotated_RentHot.java
// 讀入：n target ；下一行 n 個整數（原本遞增後被旋轉）
// 輸出：target 的索引，找不到輸出 -1
import java.io.*;

public class LC33_SearchRotated_RentHot {

    // 改良二分搜尋：每次判斷哪一半有序，決定收斂方向
    static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >>> 1);
            if (nums[mid] == target) return mid;

            // 左半有序
            if (nums[l] <= nums[mid]) {
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;      // 目標在左半
                } else {
                    l = mid + 1;      // 目標在右半
                }
            } else { // 右半有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;      // 目標在右半
                } else {
                    r = mid - 1;      // 目標在左半
                }
            }
        }
        return -1;
    }

    // --------------- Main：處理輸入輸出 ----------------
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        Integer nObj = fs.nextIntOrNull();
        if (nObj == null) return; // 無輸入
        int n = nObj;
        int target = fs.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = fs.nextInt();
        System.out.println(search(nums, target));
    }

    // 快速讀取
    static class FastScanner {
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
        Integer nextIntOrNull() throws IOException {
            int c, sgn = 1, x = 0;
            do {
                c = read();
                if (c == -1) return null;
            } while (c <= ' ');
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') { x = x * 10 + (c - '0'); c = read(); }
            return x * sgn;
        }
        int nextInt() throws IOException { return nextIntOrNull(); }
    }
}
