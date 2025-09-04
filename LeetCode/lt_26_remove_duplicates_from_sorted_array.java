// 題目：Remove Duplicates from Sorted Array
// 給定非遞減排序陣列 nums，就地刪除重複元素，使每個元素只出現一次，並回傳不重複元素的個數 k。
// 要求：O(1) 額外空間，前 k 個位置需是去重後的元素（順序不變）。

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int slow = 1;                  // 慢指針：下一個要填入「新元素」的位置
        for (int fast = 1; fast < nums.length; fast++) {
            // 只要遇到與前一個不同的元素，就是新的不重複值
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;                // 已填入一個新元素，慢指針右移
            }
        }
        return slow;                   // slow 即不重複元素個數 k
    }
}

/*
解題思路（雙指針）：
1) 陣列已排序，相同元素會連在一起。
2) fast 掃描整個陣列；當 nums[fast] != nums[fast-1] 時，代表遇到新值，寫到 nums[slow]，並 slow++。
3) 迴圈結束後，前 slow 個元素即為去重後的結果，回傳 slow。

時間複雜度：O(n)
空間複雜度：O(1)
*/
