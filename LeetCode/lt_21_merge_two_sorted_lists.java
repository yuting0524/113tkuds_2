// 題目：Merge Two Sorted Lists
// 給定兩個升冪排序的單向鏈結串列 list1、list2，
// 將它們合併為一個同樣升冪排序的新串列並回傳其頭節點。

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 使用虛擬頭節點，方便統一處理邊界情況
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        // 逐一比較兩串列目前節點，較小者接到結果串列後面
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next; // 尾巴前進
        }

        // 接上剩餘未走完的那條（其中之一一定為 null）
        tail.next = (list1 != null) ? list1 : list2;

        return dummy.next; // 回傳真正的頭
    }
}

/*
解題思路：
1) 使用「兩指針」同時走訪 list1、list2，比較當前節點值，小者接到新串列尾端。
2) 任一串列走完後，直接把另一條剩餘部分接上。
3) 透過 dummy 虛擬頭節點，避免處理頭節點的特殊判斷。

時間複雜度：O(m + n)（m、n 分別為兩串列長度）。
空間複雜度：O(1)（就地拼接，不額外建立新節點）。
*/
