import java.util.Scanner;

public class TicTacToeMinimax {
    static final char PLAYER = 'X';
    static final char AI = 'O';
    static final char EMPTY = '_';

    static char[][] board = {
        { '_', '_', '_' },
        { '_', '_', '_' },
        { '_', '_', '_' }
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printBoard();

        while (true) {
            // Player move
            System.out.print("Enter your move (row and column: 0 1): ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (board[row][col] != EMPTY) {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            board[row][col] = PLAYER;

            if (isGameOver()) break;

            // AI move
            System.out.println("AI is making a move...");
            Move bestMove = findBestMove();
            board[bestMove.row][bestMove.col] = AI;

            printBoard();
            if (isGameOver()) break;
        }

        sc.close();
    }

    static boolean isGameOver() {
        int score = evaluate(board);
        if (score == 10) {
            System.out.println("AI wins!");
            return true;
        } else if (score == -10) {
            System.out.println("You win!");
            return true;
        } else if (!isMovesLeft(board)) {
            System.out.println("It's a draw!");
            return true;
        }
        return false;
    }

    static void printBoard() {
        System.out.println("Current Board:");
        for (char[] row : board) {
            for (char cell : row) System.out.print(cell + " ");
            System.out.println();
        }
    }

    static boolean isMovesLeft(char[][] board) {
        for (char[] row : board)
            for (char cell : row)
                if (cell == EMPTY)
                    return true;
        return false;
    }

    static int evaluate(char[][] b) {
        // Check rows, cols, diagonals
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == b[i][1] && b[i][1] == b[i][2]) {
                if (b[i][0] == AI) return +10;
                else if (b[i][0] == PLAYER) return -10;
            }
            if (b[0][i] == b[1][i] && b[1][i] == b[2][i]) {
                if (b[0][i] == AI) return +10;
                else if (b[0][i] == PLAYER) return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == AI) return +10;
            else if (b[0][0] == PLAYER) return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == AI) return +10;
            else if (b[0][2] == PLAYER) return -10;
        }

        return 0;
    }

    static int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        if (score == 10 || score == -10) return score;
        if (!isMovesLeft(board)) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == EMPTY) {
                        board[i][j] = AI;
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = EMPTY;
                    }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] == EMPTY) {
                        board[i][j] = PLAYER;
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = EMPTY;
                    }
            return best;
        }
    }

    static Move findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == EMPTY) {
                    board[i][j] = AI;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = EMPTY;

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }

        return bestMove;
    }

    static class Move {
        int row, col;
    }
}
