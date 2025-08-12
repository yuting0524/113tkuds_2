import java.util.*;

public class MovingAverageStream {

    private int size;                   // 視窗大小
    private Queue<Integer> window;      // 紀錄視窗元素
    private long sum;                   // 視窗元素總和

    // 雙堆維護中位數
    private PriorityQueue<Integer> maxHeap; // 左半最大堆 (較小半部份)
    private PriorityQueue<Integer> minHeap; // 右半最小堆 (較大半部份)
    private Map<Integer, Integer> delayed;  // 延遲刪除元素計數

    // 單調佇列維護極值
    private Deque<Integer> minDeque;    // 單調遞增隊列(隊首最小)
    private Deque<Integer> maxDeque;    // 單調遞減隊列(隊首最大)

    public MovingAverageStream(int size) {
        this.size = size;
        window = new LinkedList<>();
        sum = 0;

        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        delayed = new HashMap<>();

        minDeque = new LinkedList<>();
        maxDeque = new LinkedList<>();
    }

    public double next(int val) {
        window.offer(val);
        sum += val;

        // 中位數雙堆加入
        addNum(val);

        // 極值單調隊列加入
        addMinDeque(val);
        addMaxDeque(val);

        // 超過視窗大小，移除舊元素
        if (window.size() > size) {
            int removed = window.poll();
            sum -= removed;

            // 延遲刪除
            removeNum(removed);

            // 極值單調佇列刪除舊元素
            removeFromDeque(minDeque, removed);
            removeFromDeque(maxDeque, removed);
        }

        return (double) sum / window.size();
    }

    public double getMedian() {
        balanceHeaps();

        if (window.isEmpty()) return 0;

        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    public int getMin() {
        return minDeque.isEmpty() ? 0 : minDeque.peekFirst();
    }

    public int getMax() {
        return maxDeque.isEmpty() ? 0 : maxDeque.peekFirst();
    }

    // --- 雙堆中位數維護 ---

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        cleanTop(maxHeap);
        cleanTop(minHeap);
        balanceHeaps();
    }

    private void cleanTop(PriorityQueue<Integer> heap) {
        while (!heap.isEmpty()) {
            int top = heap.peek();
            if (delayed.containsKey(top)) {
                delayed.put(top, delayed.get(top) - 1);
                if (delayed.get(top) == 0) delayed.remove(top);
                heap.poll();
            } else {
                break;
            }
        }
    }

    private void balanceHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // --- 單調隊列維護極值 ---

    private void addMinDeque(int val) {
        while (!minDeque.isEmpty() && minDeque.peekLast() > val) {
            minDeque.pollLast();
        }
        minDeque.offerLast(val);
    }

    private void addMaxDeque(int val) {
        while (!maxDeque.isEmpty() && maxDeque.peekLast() < val) {
            maxDeque.pollLast();
        }
        maxDeque.offerLast(val);
    }

    private void removeFromDeque(Deque<Integer> deque, int val) {
        if (!deque.isEmpty() && deque.peekFirst() == val) {
            deque.pollFirst();
        }
    }

    // --- 測試 ---

    public static void main(String[] args) {
        MovingAverageStream ma = new MovingAverageStream(3);

        System.out.println(ma.next(1));    // 1.0
        System.out.println(ma.next(10));   // 5.5
        System.out.println(ma.next(3));    // 4.666...
        System.out.println(ma.next(5));    // 6.0

        System.out.println("Median: " + ma.getMedian()); // 5.0
        System.out.println("Min: " + ma.getMin());       // 3
        System.out.println("Max: " + ma.getMax());       // 10
    }
}
