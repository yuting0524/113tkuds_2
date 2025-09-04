// 題目：Reverse Nodes in k-Group
// 給定鏈結串列 head 與整數 k，每 k 個節點為一組進行反轉；若最後不足 k 個，保持原樣。
// 要求僅能更改節點連結（不可改值），並儘量使用 O(1) 額外空間。

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            // 1) 找到當前這組的第 k 個節點（groupEnd）；不足 k 個則結束
            ListNode groupEnd = groupPrev;
            for (int i = 0; i < k && groupEnd != null; i++) groupEnd = groupEnd.next;
            if (groupEnd == null) break;

            ListNode nextGroupHead = groupEnd.next; // 下一組的起點
            // 2) 反轉本組 [groupStart .. groupEnd]
            ListNode prev = nextGroupHead;
            ListNode cur = groupPrev.next; // groupStart
            while (cur != nextGroupHead) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }
            // 3) 串接回主鍊：groupPrev -> groupEnd(新頭)；原起點變成本組尾
            ListNode newGroupHead = groupEnd;
            ListNode newGroupTail = groupPrev.next;
            groupPrev.next = newGroupHead;
            groupPrev = newGroupTail;
        }
        return dummy.next;
    }
}

/*
解題思路：
1) 以 dummy 作為虛擬頭，維護每一組反轉前的前驅節點 groupPrev。
2) 檢查從 groupPrev 之後是否有足夠 k 個節點；不足則結束。
3) 以「頭插法」原地反轉這 k 個節點：
   - 用 prev 指向下一組的頭 nextGroupHead 作為邊界，
   - 將本組節點逐一指向 prev，完成後 prev 會是本組新頭。
4) 將 groupPrev.next 指向新頭，並把 groupPrev 移到本組新尾（原來的 groupStart），進入下一輪。

時間複雜度：O(n)（每節點恰好被走訪/翻轉一次）
空間複雜度：O(1)
*/
