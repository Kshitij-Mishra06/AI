import java.util.Scanner;

public class n_queens {
    private int n;
    private char[][] board;

    public n_queens(int n) {
        this.n = n;
        board = new char[n][n];

        // Initialize board with empty spaces
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';
    }

    // Function to check if a queen can be placed at (row, col)
    private boolean isSafe(int row, int col) {
        // Check vertical column
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q')
                return false;

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q')
                return false;

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q')
                return false;

        return true; // Safe to place queen
    }

    // Backtracking function to place queens row by row
    private boolean solve(int row) {
        if (row == n) {
            printBoard();
            return true; // Solution found
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) { // Check if the move is valid
                board[row][col] = 'Q'; // Place the queen
                if (solve(row + 1)) // Recur for next row
                    return true;
                board[row][col] = '.'; // Backtrack
            }
        }
        return false; // No valid placement found for this row
    }

    public void solveNQueens() {
        if (!solve(0))
            System.out.println("No solution exists!");
    }

    // Function to print the board
    private void printBoard() {
        for (char[] row : board) {
            for (char cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter value of N: ");
        int n = scanner.nextInt();
        scanner.close();

        n_queens nQueens = new n_queens(n);
        nQueens.solveNQueens();
    }
}
