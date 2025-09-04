// 題目：Sudoku Solver
// 將 9x9 的數獨盤面補齊（只保證唯一解）。'.' 代表空格。
// 規則：每列、每行、每個 3x3 宮內，數字 1~9 皆不得重複。

class Solution {
    // 記錄是否使用過：第 r 列 / 第 c 行 / 第 b 宮（b = (r/3)*3 + c/3）中的數字 d
    private boolean[][] row = new boolean[9][10];
    private boolean[][] col = new boolean[9][10];
    private boolean[][] box = new boolean[9][10];
    private boolean solved = false;

    public void solveSudoku(char[][] board) {
        // 先根據盤面把已出現的數字標記起來
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;
                int d = ch - '0';
                int b = (r / 3) * 3 + (c / 3);
                row[r][d] = col[c][d] = box[b][d] = true;
            }
        }
        dfs(board, 0, 0);
    }

    // 逐格掃描（row-major），遇到空格嘗試填 1..9
    private void dfs(char[][] board, int r, int c) {
        if (r == 9) {              // 全部填完
            solved = true;
            return;
        }
        int nr = (c == 8) ? r + 1 : r;   // 下一格座標
        int nc = (c == 8) ? 0     : c + 1;

        if (board[r][c] != '.') {  // 已有數字，跳到下一格
            dfs(board, nr, nc);
            return;
        }

        int b = (r / 3) * 3 + (c / 3);
        for (int d = 1; d <= 9 && !solved; d++) {
            if (!row[r][d] && !col[c][d] && !box[b][d]) {
                // 放入 d
                board[r][c] = (char) ('0' + d);
                row[r][d] = col[c][d] = box[b][d] = true;

                dfs(board, nr, nc);      // 繼續往下一格

                if (!solved) {           // 回溯
                    board[r][c] = '.';
                    row[r][d] = col[c][d] = box[b][d] = false;
                }
            }
        }
    }
}

/*
解題思路（回溯 + 三個約束表）：
1) 用三個布林表 row[9][10]、col[9][10]、box[9][10] 記錄「某列/行/3x3 宮是否出現過數字 d」。
2) 先掃盤面把已填數字登記；之後從 (0,0) 依序掃格子：
   - 若是'.'，嘗試填入 1..9，且需同時滿足三個約束皆為 false；
   - 放入後遞迴到下一格；若之後失敗則回溯撤銷。
3) 當 r==9 代表整個盤面被合法填滿，設 solved=true 並停止後續嘗試。

時間複雜度：最壞情況指數級；實作上因強約束與剪枝可在題目資料內迅速解出。
空間複雜度：O(1)（固定大小的 9x10 布林表與遞迴深度上限 81）。
*/
