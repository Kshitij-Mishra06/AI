import java.util.*;

public class tic {
    public static char[][] matrix = {
        {' ',' ',' '},
        {' ',' ',' '},
        {' ',' ',' '}
    };

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {

        while (true) {
            String choice = "Y";

        System.out.println("Welcome to Tic-Tac-Toe! You are 'X' and the computer is 'O'.");
        printBoard();
        
        for(int chance=  0; chance < 9; chance++) {
            if(chance % 2 == 0){
                humanMove();
            }
            else {
                computerMove();
            }

            printBoard();

            if (checkWinner('X') == true) {
                System.out.println("You are Winner!!!");
                break;
            } else if(checkWinner('O') == true){
                System.out.println("You lose!!! Computer is the Winner.");
                break;
            }
        }

            System.out.println("Do you want to Continue: -");
            choice = sc.nextLine();
            if (choice.equals("N")) {
                System.out.println("Thanks for Playing!!!");
                break;
                
            }
        }

        sc.close();
    }

    public static void printBoard(){
        for(char[] i : matrix){
            for(char j : i){
                System.out.print(" "+ j + " " );  
            }
            System.out.println();
            System.out.println("--+--+--");
        }
    }

    public static void humanMove() {
        while (true) {
            System.out.print("\n");
            System.out.print("Please enter the row: -");
            int row = sc.nextInt();
            System.out.print("\n");
            System.out.print("Please enter the column: -");
            int col = sc.nextInt();

            if (row > -1 && row < 3 && col > -1 && col < 3 && matrix[row][col] == ' ') {
                matrix[row][col] = 'X';
                break;
            }
            else {
                System.out.println("Please enter the valid move!!!");
            }
        }
    }

    public static void computerMove() {
        int row = 0;
        int col = 0;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (matrix[row][col] != ' ');

        matrix[row][col] = 'O';
        System.out.println("Computer played move 'O' at ["+ row + "][" + col + "]");
    }

    public static boolean  checkWinner(char player){
        for(int i = 0; i < 3; i++) {
            if(matrix[i][0] == player && matrix[i][1] == player && matrix[i][2] == player || matrix[0][i] == player && matrix[1][i] == player && matrix[2][i] == player) {
                return true;
            }
        }

        return((matrix[0][0] == player && matrix[1][1] == player && matrix[2][2] == player || matrix[0][2] == player && matrix[2][2] == player && matrix[2][0] == player));
    }
}
