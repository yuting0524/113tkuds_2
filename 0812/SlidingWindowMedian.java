import java.util.*;

public class SlidingWindowMedian {

    private PriorityQueue<Integer> smallHalf; // Max Heap
    private PriorityQueue<Integer> largeHalf; // Min Heap
    private Map<Integer, Integer> delayed;    // 延遲刪除
    private int smallSize = 0, largeSize = 0; // 有效元素數量

    public SlidingWindowMedian() {
        smallHalf = new PriorityQueue<>(Collections.reverseOrder());
        largeHalf = new PriorityQueue<>();
        delayed = new HashMap<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(k);
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (smallHalf.isEmpty() || num <= smallHalf.peek()) {
            smallHalf.offer(num);
            smallSize++;
        } else {
            largeHalf.offer(num);
            largeSize++;
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if (num <= smallHalf.peek()) {
            smallSize--;
            if (num == smallHalf.peek()) prune(smallHalf);
        } else {
            largeSize--;
            if (!largeHalf.isEmpty() && num == largeHalf.peek()) prune(largeHalf);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        if (smallSize > largeSize + 1) {
            largeHalf.offer(smallHalf.poll());
            smallSize--;
            largeSize++;
            prune(smallHalf);
        } else if (largeSize > smallSize) {
            smallHalf.offer(largeHalf.poll());
            largeSize--;
            smallSize++;
            prune(largeHalf);
        }
    }

    private void prune(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty() && delayed.containsKey(heap.peek())) {
            int num = heap.poll();
            delayed.put(num, delayed.get(num) - 1);
            if (delayed.get(num) == 0) delayed.remove(num);
        }
    }

    private double getMedian(int k) {
        if (k % 2 == 1) return smallHalf.peek();
        else return ((double) smallHalf.peek() + largeHalf.peek()) / 2.0;
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        System.out.println(Arrays.toString(swm.medianSlidingWindow(
            new int[]{1,3,-1,-3,5,3,6,7}, 3))); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
        System.out.println(Arrays.toString(swm.medianSlidingWindow(
            new int[]{1,2,3,4}, 2))); // [1.5, 2.5, 3.5]
    }
}
