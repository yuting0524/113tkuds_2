// 題目：Find First and Last Position of Element in Sorted Array
// 給定非遞減排序陣列 nums 與 target，回傳 [target 的第一個位置, 最後一個位置]；若不存在回傳 [-1, -1]。
// 需以 O(log n) 時間解決。

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);          // 第一個 >= target 的位置
        if (left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};                 // 陣列中沒有 target
        }
        int right = upperBound(nums, target) - 1;     // 第一個 > target 的位置再往左一格
        return new int[]{left, right};
    }

    // 回傳第一個 >= x 的索引（若都小於 x，回傳 n）
    private int lowerBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r) 半開區間
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    // 回傳第一個 > x 的索引（若都 <= x，回傳 n）
    private int upperBound(int[] a, int x) {
        int l = 0, r = a.length; // [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] > x) r = m;
            else l = m + 1;
        }
        return l;
    }
}

/*
解題思路：
1) 兩次二分搜尋：
   - lowerBound：找第一個 >= target 的位置；若越界或值不等於 target，表示不存在。
   - upperBound：找第一個 > target 的位置；其前一格即為最後一個 target 的位置。
2) 皆為 O(log n)；最終輸出 [first, last]。

時間複雜度：O(log n)
空間複雜度：O(1)
*/
