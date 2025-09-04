import java.util.*;

public class LC39_CombinationSum_PPE {
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

        dfs(0, target);

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

    // 可以重複使用同一價格：i 不前進即可再次選同元素
    static void dfs(int idx, int remain) {
        if (remain == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (remain < 0 || idx == cand.length) return;

        // 選 cand[idx]
        path.add(cand[idx]);
        dfs(idx, remain - cand[idx]); // 重複使用
        path.remove(path.size() - 1);

        // 不選 cand[idx]，往後
        dfs(idx + 1, remain);
    }
}
