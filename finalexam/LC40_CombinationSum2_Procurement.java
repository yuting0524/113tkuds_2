import java.util.*;

public class LC40_CombinationSum2_Procurement {
    static List<List<Integer>> ans = new ArrayList<>();
    static List<Integer> path = new ArrayList<>();
    static int[] cand;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int target = sc.nextInt();
        cand = new int[n];
        for (int i = 0; i < n; i++) cand[i] = sc.nextInt();
        Arrays.sort(cand);

        backtrack(0, target);

        // 輸出
        for (List<Integer> comb : ans) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < comb.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(comb.get(i));
            }
            System.out.println(sb.toString());
        }
    }

    // 每個元素最多用一次；同層去重
    static void backtrack(int start, int remain) {
        if (remain == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < cand.length; i++) {
            // 去重：同一層若與前一個數相同就跳過
            if (i > start && cand[i] == cand[i - 1]) continue;
            int v = cand[i];
            if (v > remain) break; // 後面更大，可剪枝
            path.add(v);
            backtrack(i + 1, remain - v); // 每個只用一次
            path.remove(path.size() - 1);
        }
    }
}
