import java.io.*;

public class M02_YouBikeNextArrival {
    // 簡單快讀
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

    static int toMin(String s){ // "HH:mm" -> minutes since 00:00
        int h = Integer.parseInt(s.substring(0,2));
        int m = Integer.parseInt(s.substring(3,5));
        return h*60 + m;
    }
    static String toHHMM(int m){ // minutes -> "HH:mm"
        int h = m/60; int mm = m%60;
        return String.format("%02d:%02d", h, mm);
    }

    public static void main(String[] args){
        int n = fs.nextInt();
        int[] a = new int[n];
        for(int i=0;i<n;i++) a[i] = toMin(fs.next());

        int q = toMin(fs.next()); // 查詢

        // lower_bound: 第一個 >= q
        int lo=0, hi=n;
        while(lo<hi){
            int mid=(lo+hi)>>>1;
            if(a[mid] >= q) hi=mid;
            else lo=mid+1;
        }
        if(lo==n) System.out.println("No bike");
        else System.out.println(toHHMM(a[lo]));
    }
}
