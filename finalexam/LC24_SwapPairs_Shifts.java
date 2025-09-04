// 題目 13 班表兩兩交換  (LC24_SwapPairs_Shifts.java)
// 讀入一行整數，視為單向鏈結串列；將節點兩兩交換後，輸出交換後的序列。
// 例：1 2 3 4  ->  2 1 4 3
import java.io.*;
import java.util.*;

public class LC24_SwapPairs_Shifts {
    // 單向鏈結節點
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { this.val = v; }
    }

    // 兩兩交換（迭代，O(n) 時間／O(1) 空間）
    static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {
            ListNode a = prev.next;       // 第一個
            ListNode b = a.next;          // 第二個

            // 交換 a 與 b
            a.next = b.next;
            b.next = a;
            prev.next = b;

            // prev 前進到下一組前
            prev = a;
        }
        return dummy.next;
    }

    // 把一行以空白分隔的整數建成鏈結串列
    static ListNode buildList(String line) {
        StringTokenizer st = new StringTokenizer(line);
        ListNode dummy = new ListNode(0), cur = dummy;
        while (st.hasMoreTokens()) {
            cur.next = new ListNode(Integer.parseInt(st.nextToken()));
            cur = cur.next;
        }
        return dummy.next;
    }

    // 輸出鏈結串列（空白分隔）
    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(p.val);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            // 無輸入或空列：輸出空行（題目允許空／單一節點不變）
            System.out.println();
            return;
        }
        ListNode head = buildList(line);
        ListNode ans = swapPairs(head);
        printList(ans);
    }
}
