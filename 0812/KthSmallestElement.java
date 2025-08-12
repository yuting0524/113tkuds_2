import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    // 方法 1: 使用大小為 K 的 Max Heap
    public static int kthSmallest_MaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll(); // 移除最大值
            }
        }
        return maxHeap.peek();
    }

    // 方法 2: 使用 Min Heap 並取出 K 次
    public static int kthSmallest_MinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int val = -1;
        for (int i = 0; i < k; i++) {
            val = minHeap.poll();
        }
        return val;
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];
            System.out.println("陣列: " + java.util.Arrays.toString(arr) + ", K=" + k);
            System.out.println("方法1 (MaxHeap, O(n log k)) → " + kthSmallest_MaxHeap(arr, k));
            System.out.println("方法2 (MinHeap, O(n log n)) → " + kthSmallest_MinHeap(arr, k));
            System.out.println();
        }
    }
}
