// 題目：Search in Rotated Sorted Array
// 給定可能被旋轉過的嚴格遞增陣列 nums 與整數 target，請以 O(log n) 時間回傳 target 的索引；不存在回傳 -1。

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) return mid;

            // 判斷哪一半是有序的
            if (nums[l] <= nums[mid]) { // 左半有序
                if (nums[l] <= target && target < nums[mid]) {
                    r = mid - 1;       // 目標在左半
                } else {
                    l = mid + 1;       // 目標在右半
                }
            } else {                    // 右半有序
                if (nums[mid] < target && target <= nums[r]) {
                    l = mid + 1;       // 目標在右半
                } else {
                    r = mid - 1;       // 目標在左半
                }
            }
        }
        return -1;
    }
}

/*
解題思路（改良二分）：
1) 每次取 mid，若 nums[mid] == target 直接回傳。
2) 因為陣列是「一半有序、一半可能包含旋轉點」，比較 nums[l] 與 nums[mid]：
   - 若 nums[l] <= nums[mid]，代表左半有序；檢查 target 是否在 [nums[l], nums[mid]) 之間。
   - 否則右半有序；檢查 target 是否在 (nums[mid], nums[r]] 之間。
3) 將搜尋區間縮小到可能包含 target 的那一半，直至找到或區間空。

時間複雜度：O(log n)
空間複雜度：O(1)
*/
