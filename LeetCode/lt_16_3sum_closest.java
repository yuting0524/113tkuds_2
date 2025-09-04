// 題目：3Sum Closest
// 給定整數陣列 nums 與目標值 target，請找出三個數的總和，使其「最接近」 target，並回傳該總和。

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);                 // 先排序，方便雙指針掃描
        int n = nums.length;

        // 用 bestSum 記錄目前最接近的總和；先以前三個數做初始值
        int bestSum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < n - 2; i++) {
            // 可選：若與前一個 i 相同可略過，避免重複起點（非必要，僅微幅優化）
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // 若更接近目標就更新
                if (Math.abs(sum - target) < Math.abs(bestSum - target)) {
                    bestSum = sum;
                }
                // 直接命中就可提前回傳
                if (sum == target) return sum;

                // 調整雙指針：和太小就 left++；太大就 right--
                if (sum < target) {
                    left++;
                    // 可選：跳過相同值，減少重複計算
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        return bestSum;
    }
}

/*
解題思路：
1) 先排序 nums，固定一個索引 i，對區間 (i+1 .. n-1) 用雙指針 left/right 找總和最接近 target。
2) 每次計算 sum，若更接近就更新 bestSum；sum < target → left++；sum > target → right--；相等直接回傳。
3) （可選）略過相鄰重複值，減少不必要的嘗試。

時間複雜度：O(n^2)（外層 n 次、內層雙指針 O(n)）。
空間複雜度：O(1)（就地排序外不需額外空間）。
*/
