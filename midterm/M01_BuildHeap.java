/*
 * Time Complexity: O(n log K)
 * 說明: 用大小為 K 的 min-heap 維護 Top-K（qty 小、同 qty 名稱較大者視為更差而先被丟掉）；
 *      最後把堆中的元素依 (qty 降冪, name 升冪) 排序輸出。
 */

import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    // -------- Fast IO（含防呆讀整數） --------
    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1<<16]; int ptr=0,len=0;
        int read(){ if(ptr>=len){ try{len=in.read(buf);ptr=0;}catch(IOException e){} if(len<=0) return -1; } return buf[ptr++]; }
        String next(){
            StringBuilder sb=new StringBuilder(); int c;
            do c=read(); while(c<=32 && c!=-1);
            while(c>32 && c!=-1){ sb.append((char)c); c=read(); }
            return sb.toString();
        }
        // 只在需要數字時使用：自動跳過非數字 token（避免把 "n"、"len" 當數字）
        int nextIntSafe(){
            String s = next();
            while(!isInt(s)) s = next();
            return Integer.parseInt(s);
        }
        static boolean isInt(String s){
            int i=0, n=s.length();
            if(n==0) return false;
            if(s.charAt(0)=='-' || s.charAt(0)=='+'){ if(n==1) return false; i=1; }
            for(; i<n; i++){ char c=s.charAt(i); if(c<'0'||c>'9') return false; }
            return true;
        }
    }
    static FastScanner fs = new FastScanner();

    static class Item {
        String name; int qty;
        Item(String n,int q){ name=n; qty=q; }
    }

    public static void main(String[] args){
        int n = fs.nextIntSafe();
        int K = fs.nextIntSafe();

        // min-heap：把「較差」放前面以便被踢掉
        PriorityQueue<Item> pq = new PriorityQueue<>((a,b)->{
            if(a.qty!=b.qty) return Integer.compare(a.qty, b.qty); // 數量小者更差
            return b.name.compareTo(a.name);                       // 同數量時，名稱較大者更差
        });

        for(int i=0;i<n;i++){
            String name = fs.next();          // 品名（不含空白）
            int qty = fs.nextIntSafe();       // 銷量
            pq.offer(new Item(name, qty));
            if(pq.size() > K) pq.poll();      // 保持大小為 K
        }

        List<Item> ans = new ArrayList<>(pq);
        Collections.sort(ans, (a,b)->{
            if(a.qty!=b.qty) return Integer.compare(b.qty, a.qty); // 高到低
            return a.name.compareTo(b.name);                       // 名稱升冪
        });

        for(Item it: ans) System.out.println(it.name + " " + it.qty);
    }
}
