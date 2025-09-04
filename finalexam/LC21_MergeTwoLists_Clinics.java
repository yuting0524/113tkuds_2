// 檔名：LC21_MergeTwoLists_Clinics.java
// 題意：合併兩個升序單向鏈結串列，輸出合併後的升序序列
// 複雜度：時間 O(n+m)；空間 O(1)（不含輸入建立串列）

import java.io.*;
import java.util.*;

public class LC21_MergeTwoLists_Clinics {

    // 基本 ListNode 定義
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int v) { this.val = v; }
        ListNode(int v, ListNode n) { this.val = v; this.next = n; }
    }

    // 合併兩個升序鏈結串列（dummy + tail）
    static ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        // 掛上殘尾
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }

    // 由陣列建串列（升序已由輸入保證）
    static ListNode buildList(int[] arr) {
        ListNode dummy = new ListNode(-1), tail = dummy;
        for (int v : arr) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // 讀一行成為 int 陣列（指定長度，可能跨多個空白）
    static int[] readInts(BufferedReader br, int len) throws IOException {
        int[] res = new int[len];
        int filled = 0;
        while (filled < len) {
            String line = br.readLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && filled < len) {
                res[filled++] = Integer.parseInt(st.nextToken());
            }
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 讀 n, m
        String first = br.readLine();
        while (first != null && first.trim().isEmpty()) first = br.readLine();
        if (first == null) return;
        st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 讀兩列資料
        int[] A = readInts(br, n);
        int[] B = readInts(br, m);

        // 建立串列並合併
        ListNode l1 = buildList(A);
        ListNode l2 = buildList(B);
        ListNode merged = mergeTwoLists(l1, l2);

        // 輸出
        StringBuilder sb = new StringBuilder();
        for (ListNode cur = merged; cur != null; cur = cur.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(cur.val);
        }
        System.out.println(sb.toString());
    }
}
