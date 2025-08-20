import java.io.*;
import java.util.*;

public class M07_BinaryTreeLeftView {
    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1<<16]; int ptr=0,len=0;
        int read(){ if(ptr>=len){ try{len=in.read(buf);ptr=0;}catch(IOException e){} if(len<=0) return -1; } return buf[ptr++]; }
        int nextInt(){ int c,s=1,x=0; do c=read(); while(c<=32); if(c=='-'){s=-1;c=read();}
            while(c>32){ x=x*10 + c-'0'; c=read(); } return x*s; }
    }
    static FastScanner fs = new FastScanner();

    static class Node { int v; Node l, r; Node(int v){ this.v=v; } }

    public static void main(String[] args){
        int n = fs.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++) a[i]=fs.nextInt();

        Node root = buildLevelOrder(a);
        StringBuilder sb = new StringBuilder();
        sb.append("LeftView:");
        if(root!=null){
            ArrayDeque<Node> q = new ArrayDeque<>();
            q.add(root);
            while(!q.isEmpty()){
                int sz=q.size();
                for(int i=0;i<sz;i++){
                    Node cur=q.poll();
                    if(i==0) sb.append(' ').append(cur.v);   // 每層第一個
                    if(cur.l!=null) q.add(cur.l);
                    if(cur.r!=null) q.add(cur.r);
                }
            }
        }
        System.out.println(sb.toString());
    }

    // 依層序（-1=null）建樹
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
}
