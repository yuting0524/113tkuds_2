// 題目：Roman to Integer
// 給定一個羅馬數字字串 s (長度 <= 15)，請將其轉換為整數並回傳。
// 羅馬數字符號：
// I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
//
// 規則：
// 1. 若較小值在較大值左邊，表示要減去（如 IV = 4, IX = 9）。
// 2. 否則就直接加總（如 VI = 6, VIII = 8）。
// 3. 題目保證輸入是有效的羅馬數字（範圍 1 ~ 3999）。

class Solution {
    public int romanToInt(String s) {
        // 映射表：符號 -> 整數
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ans = 0;
        // 從左到右遍歷字串
        for (int i = 0; i < s.length(); i++) {
            int value = map.get(s.charAt(i));
            // 若右邊比左邊大 => 減去 (IV, IX, XL, XC, CD, CM)
            if (i + 1 < s.length() && value < map.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans;
    }
}

/*
解題思路：
1. 建立一個符號對應數值的映射表。
2. 從左到右掃描字串：
   - 若當前符號比右邊小，代表這是一個減法組合，將其值減去。
   - 否則加上當前值。
3. 最後回傳總和。

時間複雜度：O(n)，其中 n = s.length()，每個字元處理一次。
空間複雜度：O(1)，映射表大小固定。
*/
