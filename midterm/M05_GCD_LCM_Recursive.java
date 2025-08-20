/*
 * Time Complexity: O(log min(a, b))
 * 說明: 以遞迴歐幾里得演算法計算 GCD；
 *      LCM 以 a / gcd * b 計算，先除後乘可避免溢位。
 */

import java.io.*;

public class M05_GCD_LCM_Recursive {
    static class FastScanner {
        BufferedInputStream in = new BufferedInputStream(System.in);
        byte[] buf = new byte[1<<16]; int ptr=0,len=0;
        int read(){ if(ptr>=len){ try{len=in.read(buf);ptr=0;}catch(IOException e){} if(len<=0) return -1; } return buf[ptr++]; }
        String next(){ StringBuilder sb=new StringBuilder(); int c;
            do c=read(); while(c<=32 && c!=-1);
            while(c>32 && c!=-1){ sb.append((char)c); c=read(); }
            return sb.toString();
        }
        long nextLong(){ return Long.parseLong(next()); }
    }
    static FastScanner fs = new FastScanner();

    static long gcd(long a, long b){
        return b==0 ? Math.abs(a) : gcd(b, a % b);
    }

    public static void main(String[] args){
        long a = fs.nextLong();
        long b = fs.nextLong();

        long g = gcd(a, b);
        long l = (a / g) * b;  // 先除後乘，避免 a*b 溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}
