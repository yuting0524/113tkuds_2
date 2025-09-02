/**
 * 題目：Add Two Numbers
 * 給定兩個非空的鏈錶，分別表示兩個非負整數。數字以「逆序」儲存，每個節點只包含一位數字。
 * 將兩個數字相加，並以相同形式的鏈錶回傳結果。
 *
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立虛擬頭節點(dummy)，方便處理鏈錶操作
        ListNode dummy = new ListNode(0);
        ListNode current = dummy; // 指向目前的節點
        int carry = 0; // 紀錄進位值

        // 當 l1 或 l2 尚未遍歷完，或仍有進位需要處理時，持續迴圈
        while (l1 != null || l2 != null || carry != 0) {
            // 如果 l1 不為空，取出節點值，否則設為 0
            int x = (l1 != null) ? l1.val : 0;
            // 如果 l2 不為空，取出節點值，否則設為 0
            int y = (l2 != null) ? l2.val : 0;

            // 計算兩數相加 + 進位
            int sum = x + y + carry;

            // 更新進位值
            carry = sum / 10;

            // 建立新節點，值為 sum 的個位數
            current.next = new ListNode(sum % 10);

            // 移動 current 指標到下一個節點
            current = current.next;

            // 移動 l1 和 l2 的指標到下一個節點（如果有的話）
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        // 回傳真正的結果（dummy.next）
        return dummy.next;
    }
}

/*
解題思路：
1. 題目給定兩個逆序的鏈錶，每個節點代表一位數字。
2. 我們需要模擬「小學直式加法」的過程，逐位相加，並考慮進位。
3. 使用變數 carry 紀錄進位，每次計算 sum = l1.val + l2.val + carry。
4. 建立新節點存放 sum % 10，carry 則更新為 sum / 10。
5. 使用一個虛擬頭節點 dummy，避免處理首節點時的特例。
6. 最後回傳 dummy.next 即為答案。
7. 時間複雜度 O(max(m, n))，其中 m, n 分別為 l1 與 l2 的長度。
*/
