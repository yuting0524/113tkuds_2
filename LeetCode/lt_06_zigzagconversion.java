// 題目：Zigzag Conversion
// 給定字串 s 與行數 numRows，依照 Z 字型排列後，逐行讀出結果字串。

class Solution {
    public String convert(String s, int numRows) {
        // 邊界：只有 1 行或行數 ≥ 字串長度時，不會形成 Z 字型，直接回傳原字串
        if (numRows == 1 || numRows >= s.length()) return s;

        // 為每一行準備一個 StringBuilder，收集該行的字元
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0;        // 目前所在行
        int step = 1;     // 行走方向：+1 往下、-1 往上（斜向）

        // 逐字元丟進對應的行，並根據方向在行之間「來回折返」
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));  // 把當前字元放到第 r 行

            // 若走到頂端或底端，反轉方向
            if (r == 0)        step = 1;      // 從最上行往下走
            else if (r == numRows - 1) step = -1; // 從最下行往上走

            r += step; // 依方向移動到下一行
        }

        // 將每一行串接起來得到最終答案
        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }
}

/*
解題思路：
1. Z 字型排列的本質：字元在不同行之間「向下直走」與「向上斜走」來回穿梭。
2. 用 numRows 個 StringBuilder 代表每一行，掃描 s：
   - 把字元加入目前行 rows[r]。
   - 若 r 到頂(0)→ 方向改為向下；到尾(numRows-1)→ 方向改為向上。
   - 以 step=±1 控制 r 的遞增/遞減，模擬折返走位。
3. 最後把所有行依序串起即為結果。
4. 複雜度：時間 O(n)（每字元處理一次），空間 O(n)（儲存各行內容）。
5. 邊界處理：numRows=1 或 numRows≥s.length() 時，不會形成 Zigzag，直接回傳 s。
*/
