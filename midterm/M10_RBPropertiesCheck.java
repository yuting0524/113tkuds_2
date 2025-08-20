import java.io.*;

public class M10_RBPropertiesCheck {

    // -------- Fast IO --------
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

    static int n;
    static int[] val;          // -1 代表 null
    static boolean[] exist;    // val!=-1
    static boolean[] isRed;    // 僅對存在節點有效；null 視為黑
    static String violation = null;

    public static void main(String[] args){
        n = fs.nextInt();
        val   = new int[n];
        exist = new boolean[n];
        isRed = new boolean[n];

        for(int i=0;i<n;i++){
            val[i] = fs.nextInt();
            String c = fs.next();                 // "B" or "R"
            exist[i] = val[i] != -1;
            isRed[i] = exist[i] && c.equals("R"); // null 節點視為黑
        }

        // 規則 1：根節點必須為黑（空樹視為黑）
        if(n>0 && exist[0] && isRed[0]){
            System.out.println("RootNotBlack");
            return;
        }

        // 規則 2 & 3：遞迴檢查 紅紅相鄰 / 黑高度一致
        check(0);

        if(violation == null) System.out.println("RB Valid");
        else System.out.println(violation);
    }

    // 回傳黑高度；null(NIL) 的黑高度定義為 1
    // 若發現違規，將 violation 設為對應字串；之後可提早結束
    static int check(int i){
        if(i>=n || !exist[i]) return 1; // NIL 視為黑，黑高=1

        int l = 2*i + 1, r = l + 1;

        // 規則 2：父子不可同為紅
        if(isRed[i]){
            if(l<n && exist[l] && isRed[l] && violation==null){
                violation = "RedRedViolation at index " + l;
            }
            if(violation==null && r<n && exist[r] && isRed[r]){
                violation = "RedRedViolation at index " + r;
            }
        }
        if(violation != null) return 0;

        int hl = check(l);
        if(violation != null) return 0;
        int hr = check(r);
        if(violation != null) return 0;

        // 規則 3：黑高度一致
        if(hl != hr && violation==null){
            violation = "BlackHeightMismatch";
            return 0;
        }
        return hl + (isRed[i] ? 0 : 1);
    }
}
