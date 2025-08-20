/*
 * Time Complexity: O(n)
 * 說明: 由層序建樹 O(n)；一次 DFS 檢查 BST(帶上下界) 與 AVL(回傳高度、檢平衡) 各 O(n)。
 */

import java.io.*;

public class M09_AVLValidate {
    // ---- Fast IO ----
    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1<<16]; int ptr=0,len=0;
        int read(){ if(ptr>=len){ try{len=in.read(buf);ptr=0;}catch(IOException e){} if(len<=0) return -1; } return buf[ptr++]; }
        int nextInt(){ int c,s=1,x=0; do c=read(); while(c<=32); if(c=='-'){s=-1;c=read();}
            while(c>32){ x=x*10 + c-'0'; c=read(); } return x*s; }
    }
    static FastScanner fs = new FastScanner();

    static class Node { int v; Node l,r; Node(int v){this.v=v;} }

    public static void main(String[] args){
        int n = fs.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++) a[i]=fs.nextInt();

        Node root = buildLevelOrder(a);

        if(!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }
        if(!isAVL(root)) {
            System.out.println("Invalid AVL");
            return;
        }
        System.out.println("Valid");
    }

    // 由層序（-1=null）建樹
    static Node buildLevelOrder(int[] a){
        int n=a.length;
        if(n==0 || a[0]==-1) return null;
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++) if(a[i]!=-1) nodes[i]=new Node(a[i]);
        for(int i=0;i<n;i++) if(nodes[i]!=null){
            int l=2*i+1, r=l+1;
            if(l<n) nodes[i].l = nodes[l];
            if(r<n) nodes[i].r = nodes[r];
        }
        return nodes[0];
    }

    // 檢 BST：帶上下界 (min < val < max)
    static boolean isBST(Node x, long lo, long hi){
        if(x==null) return true;
        if(!(lo < x.v && x.v < hi)) return false;
        return isBST(x.l, lo, x.v) && isBST(x.r, x.v, hi);
    }

    // 檢 AVL：回傳是否 AVL（利用高度判斷）
    static boolean isAVL(Node root){
        return heightOrBad(root) != BAD;
    }
    static final int BAD = -1_000_000_000; // 特殊值代表不符合 AVL
    // null 的高度定為 -1；葉節點高度 0
    static int heightOrBad(Node x){
        if(x==null) return -1;
        int hl = heightOrBad(x.l);
        if(hl==BAD) return BAD;
        int hr = heightOrBad(x.r);
        if(hr==BAD) return BAD;
        if(Math.abs(hl-hr) > 1) return BAD;
        return Math.max(hl, hr) + 1;
    }
}
