import java.util.ArrayList;

class Task implements Comparable<Task> {
    String name;
    int priority;
    long timestamp; // 插入順序，用來處理同優先級情況

    public Task(String name, int priority, long timestamp) {
        this.name = name;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority); // 優先級大在前
        }
        return Long.compare(this.timestamp, other.timestamp); // 時間早在前
    }

    @Override
    public String toString() {
        return name + "(優先級:" + priority + ")";
    }
}


public class PriorityQueueWithHeap {
    private ArrayList<Task> heap;
    private long timestampCounter;

    public PriorityQueueWithHeap() {
        heap = new ArrayList<>();
        timestampCounter = 0;
    }

    public void addTask(String name, int priority) {
        Task task = new Task(name, priority, timestampCounter++);
        heap.add(task);
        heapifyUp(heap.size() - 1);
    }

    public Task executeNext() {
        if (isEmpty()) throw new IllegalStateException("沒有任務可執行");
        Task top = heap.get(0);
        Task last = heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return top;
    }

    public Task peek() {
        if (isEmpty()) throw new IllegalStateException("佇列為空");
        return heap.get(0);
    }

    public void changePriority(String name, int newPriority) {
        boolean found = false;
        for (Task t : heap) {
            if (t.name.equals(name)) {
                t.priority = newPriority;
                found = true;
            }
        }
        if (found) rebuildHeap();
        else System.out.println("找不到任務：" + name);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // ===== Heap 調整方法 =====
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) break;
            swap(index, parent);
            index = parent;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (true) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largest = index;

            if (left < size && heap.get(left).compareTo(heap.get(largest)) > 0) {
                largest = left;
            }
            if (right < size && heap.get(right).compareTo(heap.get(largest)) > 0) {
                largest = right;
            }
            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else break;
        }
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void rebuildHeap() {
        for (int i = (heap.size() - 2) / 2; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // ===== 測試 =====
    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個任務: " + pq.peek()); // 緊急修復
        System.out.println("執行: " + pq.executeNext()); // 緊急修復
        System.out.println("執行: " + pq.executeNext()); // 更新

        pq.addTask("安全檢查", 2);
        pq.changePriority("備份", 4);

        while (!pq.isEmpty()) {
            System.out.println("執行: " + pq.executeNext());
        }
    }
}
