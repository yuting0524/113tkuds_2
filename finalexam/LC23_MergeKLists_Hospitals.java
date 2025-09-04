// 檔名：LC23_MergeKLists_Hospitals.java
// 做法：最小堆 (PriorityQueue)，每次取出目前最小的數字並推進該序列的指標
// 時間 O(N log k)，空間 O(k)

import java.util.*;
public class LC23_MergeKLists_Hospitals {

    static class Node {
        int val;   // 目前值
        int li;    // list index
        int idx;   // 該 list 中的索引
        Node(int v, int l, int i) { val = v; li = l; idx = i; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀入 k
        if (!sc.hasNextInt()) {
            System.out.println();
            return;
        }
        int k = sc.nextInt();

        // 讀入 k 條升序序列（每條以 -1 結尾，可能為空）
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            List<Integer> tmp = new ArrayList<>();
            while (sc.hasNextInt()) {
                int x = sc.nextInt();
                if (x == -1) break;     // 該列結束
                tmp.add(x);
            }
            // 轉成陣列存放
            int[] arr = new int[tmp.size()];
            for (int t = 0; t < tmp.size(); t++) arr[t] = tmp.get(t);
            lists.add(arr);
        }

        // 最小堆，放各序列的當前元素
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        // 每條序列若非空，推首元素進堆
        for (int i = 0; i < lists.size(); i++) {
            int[] arr = lists.get(i);
            if (arr.length > 0) {
                pq.offer(new Node(arr[0], i, 0));
            }
        }

        // 合併
        StringBuilder out = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) out.append(' ');
            out.append(cur.val);
            first = false;

            int[] arr = lists.get(cur.li);
            int nextIdx = cur.idx + 1;
            if (nextIdx < arr.length) {
                pq.offer(new Node(arr[nextIdx], cur.li, nextIdx));
            }
        }

        // 輸出合併後序列（可能為空行）
        System.out.println(out.toString());
        sc.close();
    }
}
