// 題目：Combination Sum II
// 給定整數陣列 candidates（可含重複）與目標值 target，找出所有總和為 target 的「不重複」組合。
// 限制：每個元素最多使用一次（與 I 不同）；答案集合不得含相同組合。

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // 先排序以利剪枝與跳過重複
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] a, int start, int remain, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length; i++) {
            // 同層去重：若本層已用過相同數值，跳過避免重複組合
            if (i > start && a[i] == a[i - 1]) continue;

            if (a[i] > remain) break; // 排序後可剪枝

            path.add(a[i]);                          // 選 a[i]
            backtrack(a, i + 1, remain - a[i], path, res); // i+1：每個元素只能用一次
            path.remove(path.size() - 1);            // 回溯
        }
    }
}

/*
解題思路：
1) 先排序，方便在 a[i] > remain 時剪枝，且能用「同層去重」(i > start && a[i] == a[i-1]) 避免重複組合。
2) 回溯搜尋：
   - start 指示本層可選的起點；因每元素只能用一次，遞迴時傳 i+1。
   - remain 為尚需湊的值；為 0 時收錄路徑。
3) 時間複雜度取決於答案數量與分支（最壞近指數級）；空間複雜度為 O(depth) ≤ O(n)。

*/
