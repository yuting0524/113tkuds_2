// 題目：Divide Two Integers
// 在不能使用乘法(*)、除法(/)、取餘(%) 的情況下，計算 dividend / divisor，結果向 0 取整。
// 需處理 32 位元整數邊界，若結果溢位則回傳 Integer.MAX_VALUE。

class Solution {
    public int divide(int dividend, int divisor) {
        // 邊界：唯一天然會溢位的情況：(-2^31) / (-1) = 2^31，超出 int 最大值
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 決定結果符號：異號為負，同號為正
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 轉為 long 後取絕對值，避免 abs(Integer.MIN_VALUE) 溢位
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int ans = 0;

        // 位移減法（倍增法）：
        // 嘗試用 b * 2^i 去逼近 a（以位移取代乘法），從大位元往小位元試
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {          // 等價於 a >= b << i，但避免 (b<<i) 溢位用右移比較
                a -= (b << i);            // 減去對應倍數
                ans += (1 << i);          // 商加上 2^i
            }
        }

        return negative ? -ans : ans;
    }
}

/*
解題思路：
1) 先處理唯一可能上溢的情況：INT_MIN / -1 ⇒ 回傳 Integer.MAX_VALUE。
2) 用 long 接住 dividend / divisor 的絕對值，避免 INT_MIN 取絕對值溢位。
3) 位移倍增法：
   - 嘗試從最高位 (2^31) 到最低位，判斷 divisor * 2^i 是否能從被除數中扣掉；
   - 能扣就扣，並把答案加上相應的 2^i。
   - 以位移(<<, >>) 取代乘法與除法。
4) 根據原始符號決定正負。

時間複雜度：O(32) ≈ O(1)
空間複雜度：O(1)
*/
