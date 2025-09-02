// 題目：String to Integer (atoi)
// 給定字串 s，請將其轉換為 32 位元有號整數，需符合 atoi 規則：
// 1. 忽略前導空白
// 2. 判斷正負號（若沒有則視為正數）
// 3. 讀取連續數字直到遇到非數字或字串結束
// 4. 若超出 32-bit 範圍，回傳邊界值（Integer.MIN_VALUE 或 Integer.MAX_VALUE）

class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0, n = s.length();
        // 1. 忽略前導空白
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 若全是空白
        if (i == n) return 0;

        // 2. 判斷正負號
        int sign = 1;
        char c = s.charAt(i);
        if (c == '+' || c == '-') {
            sign = (c == '-') ? -1 : 1;
            i++;
        }

        // 3. 轉換數字
        int result = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // 4. 檢查是否溢出
            if (result > Integer.MAX_VALUE / 10 || 
               (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}

/*
解題思路：
1. 先忽略所有前導空白字元。
2. 判斷接下來的字元是否為 '+' 或 '-'，決定 sign。
3. 從當前位置開始讀取連續數字，遇到非數字就停止。
4. 每次加入新數字前檢查是否會溢出：
   - result > MAX/10 或 result == MAX/10 且 digit > 7 → 溢出。
   - 若 sign = 1，回傳 Integer.MAX_VALUE。
   - 若 sign = -1，回傳 Integer.MIN_VALUE。
5. 回傳 result * sign 即為最終答案。
6. 複雜度：
   - 時間 O(n)，掃過字串一次。
   - 空間 O(1)。
*/
