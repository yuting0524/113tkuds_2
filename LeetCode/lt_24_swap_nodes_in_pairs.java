// 題目：Swap Nodes in Pairs
// 給定一個鏈結串列，將每兩個相鄰的節點交換，並回傳新的頭節點。
// 節點值不可更改，只能交換節點本身。

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
    public ListNode swapPairs(ListNode head) {
        // 虛擬頭節點，方便處理頭節點交換
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;

        // 每次處理兩個節點
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;

            // 調整指標實現交換
            first.next = second.next;
            second.next = first;
            cur.next = second;

            // cur 前進到下一組
            cur = first;
        }

        return dummy.next;
    }
}

/*
解題思路：
1) 使用 dummy 節點指向 head，避免處理頭節點交換時的特殊情況。
2) 迴圈內每次取兩個節點 (first, second)，交換它們的連結。
3) 將 cur 移到第一個節點 (交換後在第二位)，繼續下一組。

時間複雜度：O(n)，每個節點走訪一次。
空間複雜度：O(1)，原地交換。
*/
