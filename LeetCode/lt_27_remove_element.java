// 題目：Remove Element
// 給定整數陣列 nums 與整數 val，就地移除所有等於 val 的元素，
// 並回傳移除後陣列的有效長度 k（前 k 個位置為不等於 val 的元素，順序不重要）。

class Solution {
    public int removeElement(int[] nums, int val) {
        // 兩端指針法（順序不重要 → 可用尾端覆蓋，減少搬移）
        int left = 0;              // 掃描指針
        int right = nums.length;   // 開區間尾端（下一個可覆蓋的位置）

        while (left < right) {
            if (nums[left] == val) {
                // 用最後一個有效元素覆蓋它，並縮小有效範圍
                nums[left] = nums[right - 1];
                right--;           // 縮短有效長度
                // left 不前進：需再檢查被覆蓋過來的值
            } else {
                left++;            // 此位置保留，往下一格
            }
        }
        // right 即為剩餘元素數量（有效長度 k）
        return right;
    }
}

/*
解題思路：
1) 因為輸出不要求保序，所以採「尾端覆蓋」最省搬移：
   - 掃描到等於 val 的位置，就用尾端的元素覆蓋，並將尾端縮小一格。
   - 掃描到不等於 val 的位置，left++。
2) 當 left 與 right 交會，right 就是有效長度 k；前 k 個位置即為結果。

時間複雜度：O(n)    空間複雜度：O(1)
*/
