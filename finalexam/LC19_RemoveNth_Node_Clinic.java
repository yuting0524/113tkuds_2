import java.util.*;

/**
 * 題目 10 護理紀錄刪除倒數 N 筆
 * 讀入：
 *   n
 *   n 個節點值（以空白分隔）
 *   k
 * 需求：刪除倒數第 k 個節點，輸出刪除後的序列（升序與否不影響；照原順序印出）。
 * 作法：Dummy + 雙指標（fast 先走 k 步，之後 fast/slow 同步直到 fast 到尾；刪除 slow.next）
 * 時間 O(n)，空間 O(1)
 */
public class LC19_RemoveNth_Node_Clinic {

    // 單鏈結點定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 讀 n
        if (!sc.hasNextInt()) {
            return;
        }
        int n = sc.nextInt();

        // 讀 n 個值，建鏈
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        for (int i = 0; i < n; i++) {
            int v = sc.nextInt();
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        ListNode head = dummyHead.next;

        // 讀 k
        int k = sc.nextInt();

        head = removeNthFromEnd(head, k);

        // 輸出：空串列就輸出空行
        printList(head);
    }

    // 刪除倒數第 k 個節點
    static ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy, slow = dummy;

        // fast 先走 k 步
        for (int i = 0; i < k; i++) {
            // 依題意 1 <= k <= n，不需額外檢查；保險起見加判斷
            if (fast != null) fast = fast.next;
        }

        // fast、slow 同步直到 fast 到尾端
        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // slow.next 即為要刪除的節點
        if (slow.next != null) {
            slow.next = slow.next.next;
        }

        return dummy.next;
    }

    // 輸出鏈結串列
    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(cur.val);
        }
        System.out.println(sb.toString());
    }
}
