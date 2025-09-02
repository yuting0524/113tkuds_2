import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 需要的另一個數字
            if (map.containsKey(complement)) {
                // 找到答案，回傳對應的索引
                return new int[] { map.get(complement), i };
            }
            // 沒找到就把當前數字存進 map
            map.put(nums[i], i);
        }
        
        // 題目保證一定有解，所以這裡理論上不會執行
        return new int[] {};
    }
}

/*
解題思路：
1. 題目要求找到兩個數字相加等於 target。
2. 直覺的暴力法是兩層迴圈檢查所有組合，時間複雜度 O(n^2)。
3. 為了加速，我們使用 HashMap：
   - Key: 數值
   - Value: 該數值的索引
4. 在迴圈中，對每個 nums[i] 計算「差值 complement = target - nums[i]」。
   - 如果 complement 已經存在於 HashMap → 找到答案，回傳對應索引。
   - 如果 complement 不存在 → 將 nums[i] 與 i 存進 HashMap，繼續搜尋。
5. 時間複雜度為 O(n)，空間複雜度為 O(n)。
*/