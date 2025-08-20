import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    // ---------- Fast IO ----------
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
        int nextInt(){ return Integer.parseInt(next()); }
    }
    static FastScanner fs = new FastScanner();

    static boolean seenHHMM = false;
    static int parseTime(String s){
        if (s.indexOf(':') >= 0){
            seenHHMM = true;
            int h = Integer.parseInt(s.substring(0, 2));
            int m = Integer.parseInt(s.substring(3, 5));
            return h*60 + m;
        } else return Integer.parseInt(s);
    }
    static String toHHMM(int minutes){
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    static class E {
        int t; int which; int idx;
        E(int t,int which,int idx){ this.t=t; this.which=which; this.idx=idx; }
    }

    public static void main(String[] args){
        int K = fs.nextInt();
        List<int[]> lists = new ArrayList<>(K);
        for(int i=0;i<K;i++){
            int len = fs.nextInt();
            int[] arr = new int[len];
            for(int j=0;j<len;j++){
                String token = fs.next();
                arr[j] = parseTime(token);
            }
            lists.add(arr);
        }

        PriorityQueue<E> pq = new PriorityQueue<>((a,b)-> a.t - b.t);
        for(int i=0;i<K;i++){
            int[] arr = lists.get(i);
            if(arr.length>0) pq.add(new E(arr[0], i, 0));
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while(!pq.isEmpty()){
            E e = pq.poll();
            if(!first) sb.append(' ');
            first = false;
            sb.append(seenHHMM ? toHHMM(e.t) : e.t);
            int[] arr = lists.get(e.which);
            int ni = e.idx + 1;
            if(ni < arr.length) pq.add(new E(arr[ni], e.which, ni));
        }
        System.out.println(sb.toString());
    }
}
