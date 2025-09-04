// 題目：Next Permutation
// 給定整數陣列 nums，就地將其改成「字典序上」的下一個排列；若不存在（已是最大排列），則改成最小排列（遞增）。

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if (n < 2) return;

        // 1) 從右往左找「第一個下降點」i：nums[i] < nums[i+1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // 2) 從右往左找第一個 > nums[i] 的位置 j，並交換 i, j
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        // 3) 將區間 [i+1, n-1] 反轉，使其成為最小遞增序
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    private void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
}

/*
解題思路（經典三步）：
1) 從右掃到左找第一個「下降點」i（nums[i] < nums[i+1]）。若找不到，代表當前為最大排列，直接反轉整列得到最小排列。
2) 在右半部從右找第一個 > nums[i] 的元素 j，交換 i 與 j，讓整體略為增大。
3) 將 i 右側區間反轉成遞增，得到「最小的更大」排列（下一個字典序）。

時間複雜度：O(n)；空間複雜度：O(1)。
*/
