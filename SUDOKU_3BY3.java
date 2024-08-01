public class SUDOKU_3BY3 {
    public boolean solveSudoku(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, i, j, num)) {
                            board[i][j] = num;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == num && (i != row || j != col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 5},
                {0, 7, 0},
                {1, 0, 0}
        };

        SUDOKU_3BY3 solver = new SUDOKU_3BY3();
        if (solver.solveSudoku(board)) {
            System.out.println("Solution:");
            solver.printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
