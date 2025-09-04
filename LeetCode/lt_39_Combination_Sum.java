// 題目：Combination Sum
// 給定互不相同的正整數陣列 candidates 與目標值 target，找出所有總和為 target 的組合。
// 每個數可被重複選取多次，組合內元素順序不影響唯一性。

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);                 // 先排序，方便剪枝
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    // idx：目前可選的起始索引（允許重複選取 → 下一層仍可用 idx）
    private void backtrack(int[] a, int idx, int remain, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {                       // 命中一組
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < a.length; i++) {
            if (a[i] > remain) break;            // 排序後的剪枝
            path.add(a[i]);                      // 選 a[i]
            backtrack(a, i, remain - a[i], path, res); // i（不是 i+1）→ 允許重複選取
            path.remove(path.size() - 1);        // 回溯
        }
    }
}

/*
解題思路（回溯 + 剪枝）：
1) 因元素可重複選取，但組合不看順序，故以「索引不回退」的 DFS：
   - 參數 idx 表示這一層從 candidates[idx..] 開始選，避免排列造成重複。
   - 選到某個數後，下一層仍從 i 開始，表示可重複選同一數。
2) 先排序 candidates，可在 a[i] > remain 時直接剪枝。
3) 當 remain==0，收錄當前路徑；remain<0 不會發生（已剪枝）。

時間複雜度：取決於答案數量與搜尋樹分支，最壞情況近似 O(N^(target/min)))。
空間複雜度：O(target/min) 為遞迴深度與路徑長度。
*/
