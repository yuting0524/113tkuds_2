// 題目：3Sum
// 給定一個整數陣列 nums，找出所有不重複的三元組 (i, j, k)，使得：
// nums[i] + nums[j] + nums[k] == 0。
// 回傳所有符合條件的三元組，不能有重複解。

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);  // 先排序，方便用雙指針處理與去重複

        for (int i = 0; i < nums.length - 2; i++) {
            // 避免固定數重複（例如 nums[i] == nums[i-1] 時跳過）
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 移動 left，避免重複解
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 移動 right，避免重複解
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;  // 太小，往右移動以增加總和
                } else {
                    right--; // 太大，往左移動以減少總和
                }
            }
        }
        return res;
    }
}

/*
解題思路：
1. 將陣列排序，方便用雙指針尋找目標和去除重複解。
2. 固定一個數 nums[i]，在其右側用雙指針 (left, right) 找三數和為 0。
3. 若和 = 0，加入解答，並跳過重複元素避免重複解。
4. 若和 < 0，left++；若和 > 0，right--。
5. 重複直到檢查完所有可能的 i。

時間複雜度：O(n^2)，排序 O(n log n)，外層迴圈 O(n)，內層雙指針 O(n)。
空間複雜度：O(1)，除了輸出結果外不需額外空間。
*/
