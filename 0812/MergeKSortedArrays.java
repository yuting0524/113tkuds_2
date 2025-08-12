import java.util.*;

public class MergeKSortedArrays {

    // 儲存值與來源陣列、索引
    static class Element implements Comparable<Element> {
        int value;
        int arrayIndex;
        int elementIndex;

        public Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Element other) {
            return Integer.compare(this.value, other.value); // Min Heap
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>();

        // 初始化：每個陣列的第一個元素
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        // 合併
        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < arrays[current.arrayIndex].length) {
                minHeap.offer(new Element(arrays[current.arrayIndex][nextIndex], current.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1,4,5}, {1,3,4}, {2,6}};
        int[][] arr2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] arr3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(arr1)); // [1,1,2,3,4,4,5,6]
        System.out.println(mergeKSortedArrays(arr2)); // [1,2,3,4,5,6,7,8,9]
        System.out.println(mergeKSortedArrays(arr3)); // [0,1]
    }
}
