
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 確保 nums1 是較短的陣列，避免二分搜尋時超出範圍
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        // 左右邊界（針對 nums1 做二分搜尋）
        int left = 0, right = m;

        while (left <= right) {
            // 切割 nums1 的位置
            int i = (left + right) / 2;
            // 切割 nums2 的位置（保證左半邊 + 右半邊長度平衡）
            int j = (m + n + 1) / 2 - i;

            // 取出四個關鍵值，若超出邊界則用極值代替
            int maxLeftA = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRightA = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int maxLeftB = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRightB = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // 判斷是否切割正確：左半邊最大 <= 右半邊最小
            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                // 總長度為偶數
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2.0;
                } else {
                    // 總長度為奇數
                    return Math.max(maxLeftA, maxLeftB);
                }
            } 
            // 若 nums1 左半邊太大，需往左移動
            else if (maxLeftA > minRightB) {
                right = i - 1;
            } 
            // 若 nums1 右半邊太小，需往右移動
            else {
                left = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}

/*
解題思路：
1. 題目要求 O(log(m+n))，因此不能直接合併陣列後取中位數。
2. 採用「二分搜尋切割」法：
   - 在 nums1 中切割位置 i，nums2 中切割位置 j，使得
     左半邊長度 = 右半邊長度 (或差 1)。
   - 確保 maxLeftA <= minRightB 且 maxLeftB <= minRightA，
     則代表切割正確。
3. 中位數計算：
   - 若總長度奇數 → 中位數為左半邊最大值。
   - 若總長度偶數 → 中位數為 (左半邊最大值 + 右半邊最小值) / 2。
4. 為避免邊界錯誤，若切割到陣列開頭或結尾，用 ±∞ 代替。
5. 時間複雜度：O(log(min(m, n)))，只對短陣列做二分搜尋。
   空間複雜度：O(1)。
*/
