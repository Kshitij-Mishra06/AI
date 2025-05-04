import java.util.Scanner;

public class MagicSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an odd number for the magic square size: ");
        int n = scanner.nextInt();
        scanner.close();
        
        if (n % 2 == 0) {
            System.out.println("Only odd numbers are allowed.");
            return;
        }
        
        int[][] magicSquare = generateMagicSquare(n);
        printMagicSquare(magicSquare);
    }

    private static int[][] generateMagicSquare(int n) {
        int[][] magicSquare = new int[n][n];
        int row = 0, col = n / 2;
        
        for (int num = 1; num <= n * n; num++) {
            magicSquare[row][col] = num;
            int newRow = (row - 1 + n) % n;
            int newCol = (col + 1) % n;
            
            if (magicSquare[newRow][newCol] != 0) {
                row = (row + 1) % n;
            } else {
                row = newRow;
                col = newCol;
            }
        }
        
        return magicSquare;
    }

    private static void printMagicSquare(int[][] magicSquare) {
        int n = magicSquare.length;
        System.out.println("Magic Square:");
        for (int[] row : magicSquare) {
            for (int num : row) {
                System.out.printf("%4d", num);
            }
            System.out.println();
        }
    }
}
