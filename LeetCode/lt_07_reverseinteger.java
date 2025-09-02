// 題目：Reverse Integer
// 給定一個 32 位元整數 x，請將它的數字反轉後回傳。
// 若反轉後超過 32-bit 整數範圍 [-2^31, 2^31-1]，則回傳 0。

class Solution {
    public int reverse(int x) {
        int result = 0; // 儲存反轉後的結果

        while (x != 0) {
            int digit = x % 10; // 取出最後一位數字
            x /= 10;            // 去掉最後一位

            // 檢查是否溢出（正數情況）
            if (result > Integer.MAX_VALUE / 10 || 
               (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0; // 超過正數範圍
            }
            // 檢查是否溢出（負數情況）
            if (result < Integer.MIN_VALUE / 10 || 
               (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0; // 超過負數範圍
            }

            // 將 digit 加入反轉後的結果
            result = result * 10 + digit;
        }
        return result;
    }
}

/*
解題思路：
1. 使用「逐位取出」方式反轉數字：
   - digit = x % 10 取最後一位數字
   - x /= 10 移除最後一位
   - result = result * 10 + digit 加入結果
2. 溢出判斷：
   - 因為 result * 10 可能超過 32-bit 範圍，所以提前檢查：
     - 若 result > MAX/10，或 result == MAX/10 且 digit > 7，會溢出。
     - 若 result < MIN/10，或 result == MIN/10 且 digit < -8，會溢出。
   - 這裡的 7 與 -8 是因為 Integer.MAX_VALUE = 2147483647、Integer.MIN_VALUE = -2147483648。
3. 若過程中發現溢出，直接回傳 0。
4. 時間複雜度 O(log|x|)：每次迴圈處理一位數字。
   空間複雜度 O(1)。
*/
