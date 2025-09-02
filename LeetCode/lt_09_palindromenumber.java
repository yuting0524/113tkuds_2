// 題目：Palindrome Number
// 給定整數 x，若 x 是回文數則回傳 true，否則回傳 false。

class Solution {
    public boolean isPalindrome(int x) {
        // 負數一定不是回文（因為有負號 '-')
        // 並且若最後一位是 0，但 x 不是 0，本身也不是回文（例如 10 -> 01）
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0; // 用來存反轉的後半部分

        // 只需要反轉一半即可，避免整數溢出
        while (x > reversedHalf) {
            int digit = x % 10;              // 取最後一位
            reversedHalf = reversedHalf * 10 + digit; // 加到反轉數
            x /= 10;                         // 去掉最後一位
        }

        // 如果數字長度為偶數，則 x == reversedHalf
        // 如果數字長度為奇數，去掉 reversedHalf 的中間位（/10）再比較
        return x == reversedHalf || x == reversedHalf / 10;
    }
}

/*
解題思路：
1. 特殊情況：
   - 負數不可能是回文。
   - 如果末位是 0（例如 10），開頭就不可能是 0，所以不是回文，除非數字是 0 本身。
2. 反轉一半：
   - 直接反轉整數可能會溢出，所以只反轉「一半」。
   - 當 reversedHalf >= x 時，代表已經處理了一半數字。
3. 判斷回文：
   - 偶數位數：反轉後的前半與後半相等 → x == reversedHalf。
   - 奇數位數：反轉後會多出一位中間數，所以比較 x == reversedHalf / 10。
4. 複雜度：
   - 時間 O(log n)，每次迴圈處理一位數字。
   - 空間 O(1)，只用到常數變數。
*/
