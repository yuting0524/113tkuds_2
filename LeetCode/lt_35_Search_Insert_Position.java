// 題目：Search Insert Position
// 給定一個排序好的整數陣列 nums 和一個目標值 target，
// 如果 target 存在，回傳其索引；若不存在，回傳它應插入的位置（保持升序）。
// 必須以 O(log n) 完成。

class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;                 // 找到目標值
            } else if (nums[mid] < target) {
                l = mid + 1;                // 往右搜尋
            } else {
                r = mid - 1;                // 往左搜尋
            }
        }
        return l; // 當 target 不存在時，l 即為插入位置
    }
}

/*
解題思路：
1. 使用二分搜尋 (Binary Search)。
2. 若找到 target，直接回傳索引。
3. 若沒找到，最終 left (l) 會停在 target 應插入的位置。
   - 因為 l 指向第一個大於 target 的位置，這就是插入點。
4. 時間複雜度 O(log n)，符合題目要求。

範例：
nums = [1,3,5,6], target = 5 → 回傳 2
nums = [1,3,5,6], target = 2 → 回傳 1
nums = [1,3,5,6], target = 7 → 回傳 4
nums = [1,3,5,6], target = 0 → 回傳 0
*/
