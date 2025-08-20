import java.io.*;

public class M08_BSTRangedSum {
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
        int L = fs.nextInt(), R = fs.nextInt();

        Node root = buildLevelOrder(a);
        long ans = rangeSum(root, L, R);
        System.out.println("Sum: " + ans);
    }

    // 由層序（-1 = null）建樹
    static Node buildLevelOrder(int[] a){
        int n=a.length;
        if(n==0 || a[0]==-1) return null;
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++) if(a[i]!=-1) nodes[i]=new Node(a[i]);
        for(int i=0;i<n;i++) if(nodes[i]!=null){
            int l=2*i+1, r=l+1;
            if(l<n) nodes[i].l=nodes[l];
            if(r<n) nodes[i].r=nodes[r];
        }
        return nodes[0];
    }

    // 依 BST 性質剪枝
    static long rangeSum(Node x, int L, int R){
        if(x==null) return 0;
        if(x.v < L) return rangeSum(x.r, L, R);
        if(x.v > R) return rangeSum(x.l, L, R);
        return x.v + rangeSum(x.l, L, R) + rangeSum(x.r, L, R);
    }
}
