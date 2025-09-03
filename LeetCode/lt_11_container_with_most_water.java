// 題目：Container With Most Water
// 給定一個整數陣列 height，表示每個位置的柱子高度。
// 請找出兩個柱子，與 x 軸形成的容器可以裝最多水，並回傳最大水量。

class Solution {
    public int maxArea(int[] height) {
        int left = 0;                    // 左指針
        int right = height.length - 1;   // 右指針
        int ans = 0;                     // 最大水量

        while (left < right) {
            // 計算當前容器的水量
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = h * width;
            ans = Math.max(ans, area);   // 更新最大值

            // 移動較矮的那一側，嘗試找更高的柱子
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 兩端指針 left、right 初始指向陣列兩端，形成最寬的容器。
2. 每次計算水量 = min(height[left], height[right]) * (right - left)。
3. 為了嘗試得到更大的面積，移動高度較小的一側（因為決定水量的短板需要提高）。
4. 持續移動指針直到 left >= right。
5. 時間複雜度 O(n)，因為每個元素最多被訪問一次。
6. 空間複雜度 O(1)，僅使用常數變數。
*/
