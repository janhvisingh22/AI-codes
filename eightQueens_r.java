public class eightQueens_r {

    private static final int N = 8; // Number of queens (and size of the board)

    // This array will store the column index of the queen placed in each row
    private static int[] queensInRow = new int[N];

    // Method to check if placing a queen at (row, col) is safe
    private static boolean isSafe(int row, int col) {
        // Check all rows above the current row
        for (int prevRow = 0; prevRow < row; prevRow++) {
            int prevCol = queensInRow[prevRow];
            // Queens in the same column or same diagonal are not safe
            if (prevCol == col || Math.abs(prevRow - row) == Math.abs(prevCol - col)) {
                return false;
            }
        }
        return true;
    }

    // Method to solve the 8-Queens problem using backtracking
    private static boolean solve(int row) {
        if (row == N) {
            // All queens have been successfully placed
            return true;
        }

        // Try placing the queen in each column of the current row
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                queensInRow[row] = col; // Place the queen
                if (solve(row + 1)) {
                    return true; // If placing this queen leads to a solution, return true
                }
                // Backtrack: remove the queen from this position and try the next column
                queensInRow[row] = 0;
            }
        }
        return false; // If no column is safe, return false (triggering backtracking)
    }

    // Method to print the board with queens placed
    private static void printBoard() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (queensInRow[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (solve(0)) {
            System.out.println("Solution found:");
            printBoard();
        } else {
            System.out.println("No solution exists.");
        }
    }
}

