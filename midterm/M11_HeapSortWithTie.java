/*
 * Time Complexity: O(n log n)
 * 說明：Build-Heap O(n)，接著做 n 次 siftDown，每次 O(log n)，
 *       總計 O(n + n log n) = O(n log n)；
 * Space Complexity: O(1)（原地排序）
 */

import java.io.*;

public class M11_HeapSortWithTie {

    // 讀取：整數快讀
    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1<<16]; int ptr=0,len=0;
        int read(){ if(ptr>=len){ try{len=in.read(buf);ptr=0;}catch(IOException e){} if(len<=0) return -1; } return buf[ptr++]; }
        int nextInt(){
            int c,s=1,x=0; do c=read(); while(c<=32);
            if(c=='-'){ s=-1; c=read(); }
            while(c>32){ x = x*10 + (c-'0'); c=read(); }
            return x*s;
        }
    }
    static FastScanner fs = new FastScanner();

    static class Node {
        int score, idx; // idx = 輸入順序
        Node(int s, int i){ score = s; idx = i; }
    }

    // 比較「a 是否比 b 大」（用於 Max-Heap）
    // 規則：分數大者較大；若同分，idx 大者較大
    // 這樣用 Max-Heap 排完後，整體是遞增，且同分會依 idx 小的在前
    static boolean greater(Node a, Node b){
        if (a.score != b.score) return a.score > b.score;
        return a.idx > b.idx; // later index treated as greater
    }

    static void heapSort(Node[] a){
        int n = a.length;
        // build max-heap: 自底向上 siftDown
        for(int i = n/2 - 1; i >= 0; i--) siftDown(a, i, n);

        // 反覆把最大值丟到尾端
        for(int end = n - 1; end > 0; end--){
            swap(a, 0, end);
            siftDown(a, 0, end); // heap 範圍縮到 [0, end)
        }
    }

    // 維護 max-heap
    static void siftDown(Node[] a, int i, int n){
        while(true){
            int l = i*2 + 1, r = l + 1, b = i;
            if (l < n && greater(a[l], a[b])) b = l;
            if (r < n && greater(a[r], a[b])) b = r;
            if (b == i) break;
            swap(a, i, b);
            i = b;
        }
    }

    static void swap(Node[] a, int i, int j){
        Node t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void main(String[] args){
        int n = fs.nextInt();
        Node[] arr = new Node[n];
        for(int i=0;i<n;i++){
            int s = fs.nextInt();
            arr[i] = new Node(s, i);
        }

        heapSort(arr);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(i>0) sb.append(' ');
            sb.append(arr[i].score);
        }
        System.out.println(sb.toString());
    }
}
