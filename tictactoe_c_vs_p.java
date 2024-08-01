import java.util.Random;
import java.util.Scanner;

public class tictactoe_c_vs_p {

    private static final char EMPTY = ' ';
    private static final char USER_MARKER = 'X';
    private static final char COMPUTER_MARKER = 'O';

    private static char[][] board = new char[3][3];
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        char currentPlayer = USER_MARKER;

        // Game loop
        while (true) {
            if (currentPlayer == USER_MARKER) {
                // User's turn
                userMove();
                currentPlayer = COMPUTER_MARKER;
            } else {
                // Computer's turn
                computerMove();
                currentPlayer = USER_MARKER;
            }

            displayBoard();

            // Check game status
            if (isWinner(USER_MARKER)) {
                System.out.println("Congratulations! You win!");
                break;
            } else if (isWinner(COMPUTER_MARKER)) {
                System.out.println("Sorry, you lose. The computer wins!");
                break;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }

        scanner.close();
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void userMove() {
        System.out.print("Enter your move (row[1-3] column[1-3]): ");
        int row = scanner.nextInt() - 1;
        int col = scanner.nextInt() - 1;

        while (!isValidMove(row, col)) {
            System.out.println("Invalid move. Try again.");
            System.out.print("Enter your move (row[1-3] column[1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
        }

        board[row][col] = USER_MARKER;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY;
    }

    private static void computerMove() {
        System.out.println("Computer's turn...");

        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        board[row][col] = COMPUTER_MARKER;
    }

    private static boolean isWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // Check diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
