// 題目：Merge k Sorted Lists
// 給定 k 個已排序（升冪）的鏈結串列，請將它們合併為一個排序好的串列並回傳其頭節點。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆：每次取出目前最小的節點
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        // 將每條串列的頭節點放入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();     // 取出當前最小值
            tail.next = cur;              // 接到結果串列
            tail = tail.next;

            if (cur.next != null) {
                pq.offer(cur.next);       // 該串列仍有節點，放回堆中
            }
        }
        return dummy.next;
    }
}

/*
解題思路：
1) 使用最小堆（PriorityQueue）保存 k 條鏈表當前的頭節點。
2) 反覆取出堆中最小節點接到結果串列，若該節點有下一個，則將其下一個節點放回堆。
3) 直到堆空即完成合併。

時間複雜度：O(N log k)，N 為所有節點總數；每個節點最多進出堆一次，堆大小 ≤ k。
空間複雜度：O(k)，最小堆所需的額外空間。
*/
