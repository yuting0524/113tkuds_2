// 題目：Valid Sudoku
// 判斷 9x9 的數獨盤面是否有效（只需檢查已填的格子）。
// 規則：每一列、每一行、每個 3x3 子盒都不可出現重複的 1~9。

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // rows[i][d] 表示第 i 列是否已出現數字 d
        // cols[j][d] 表示第 j 行是否已出現數字 d
        // box[k][d]  表示第 k 個 3x3 子盒是否已出現數字 d（k = (i/3)*3 + j/3）
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] box  = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') continue;          // 空格跳過

                int d = c - '0';                 // 轉成對應數字 1..9
                int b = (i / 3) * 3 + (j / 3);   // 3x3 子盒索引 0..8

                // 若任何一處已經出現過 d，則無效
                if (rows[i][d] || cols[j][d] || box[b][d]) return false;

                rows[i][d] = cols[j][d] = box[b][d] = true; // 登記出現
            }
        }
        return true; // 全部檢查通過即為有效
    }
}

/*
解題思路：
1) 逐格掃描，對每個已填數字 d（1~9）檢查三個約束是否違反：
   - 行 i 是否已出現 d
   - 列 j 是否已出現 d
   - 所在 3x3 子盒 b 是否已出現 d（b = (i/3)*3 + j/3）
2) 任何一處重複即回傳 false；全部通過則 true。
   只檢查已填的格子（'.' 略過）。

時間複雜度：O(81) ≈ O(1)
空間複雜度：O(1) 使用固定大小的布林表。
*/
