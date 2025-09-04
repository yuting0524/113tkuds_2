// 題目：4Sum
// 給定整數陣列 nums 與目標值 target，找出所有不重複的四元組 (a,b,c,d)
// 使得 nums[a] + nums[b] + nums[c] + nums[d] == target，回傳所有組合（順序不限）。

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return res;

        Arrays.sort(nums); // 先排序，方便雙指針與去重

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重：固定 i 不重複

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重：固定 j 不重複

                int left = j + 1, right = n - 1;

                while (left < right) {
                    // 用 long 防止加總溢位
                    long sum = 0L + nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // 移動並去重
                        int lv = nums[left], rv = nums[right];
                        while (left < right && nums[left] == lv) left++;
                        while (left < right && nums[right] == rv) right--;
                    } else if (sum < target) {
                        left++;   // 總和太小，往右增大
                    } else {
                        right--;  // 總和太大，往左減小
                    }
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1) 排序後，枚舉前兩個索引 i、j，對區間 (j+1..n-1) 使用雙指針 left/right。
2) sum 與 target 比較：小則 left++，大則 right--；相等收集答案並跳過重複值。
3) 透過「相鄰相等即跳過」消除重複解（i、j、left、right 都需處理）。

時間複雜度：O(n^3)（兩層枚舉 * 內層雙指針）。
空間複雜度：O(1)（輸出結果除外）。
*/
