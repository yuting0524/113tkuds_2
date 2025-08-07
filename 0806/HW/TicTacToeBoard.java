import java.util.Scanner;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        initBoard();
        Scanner sc = new Scanner(System.in);
        boolean gameEnded = false;

        while (!gameEnded) {
            printBoard();
            System.out.println("玩家 " + currentPlayer + " 請輸入你要下的行列（0~2）以空格分開：");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (!isValidMove(row, col)) {
                System.out.println("此位置無效，請重新輸入！");
                continue;
            }

            placeMark(row, col, currentPlayer);

            if (checkWinner(currentPlayer)) {
                printBoard();
                System.out.println("玩家 " + currentPlayer + " 獲勝！");
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("平手！");
                gameEnded = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // 換人
            }
        }

        sc.close();
    }

    // 1. 初始化棋盤
    public static void initBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = ' ';
    }

    // 2. 顯示棋盤
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 3. 檢查移動是否合法
    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    // 4. 放置棋子
    public static void placeMark(int row, int col, char player) {
        board[row][col] = player;
    }

    // 5. 判斷是否勝利
    public static boolean checkWinner(char player) {
        // 橫排與直排
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        // 對角線
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // 6. 判斷是否平手（棋盤已滿）
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }
}
