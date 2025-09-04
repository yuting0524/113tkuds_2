// 題目：Remove Nth Node From End of List
// 給定一個單向鏈結串列 head，刪除倒數第 n 個節點，並回傳修改後的 head。

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立虛擬頭節點，處理刪除頭節點的情況更方便
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 先讓 fast 走 n+1 步，確保 slow 停在待刪節點的前一個
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // 同時移動 fast 和 slow，直到 fast 到尾端
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next 就是要刪除的節點
        slow.next = slow.next.next;

        return dummy.next; // 回傳真正的頭節點
    }
}

/*
解題思路：
1) 使用快慢指針技巧：
   - fast 先走 n+1 步，使得 slow 與 fast 之間差距 n+1。
   - 然後 fast、slow 一起移動，當 fast 到尾端時，slow 剛好在待刪節點的前一個。
2) 修改 slow.next 指向，即刪除目標節點。
3) 使用 dummy 虛擬頭節點處理刪除頭節點的特殊情況。

時間複雜度：O(L)，L 為鏈表長度，fast/slow 各走一遍。
空間複雜度：O(1)。
*/
