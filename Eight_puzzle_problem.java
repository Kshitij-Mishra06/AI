import java.util.*;

public class Eight_puzzle_problem {
    public static void main(String[] args) {
        int[][] initialState = {
            {1, 2, 3}, {4, 0, 6}, {7, 5, 8}
        };

        puzzle pz = new puzzle(initialState);
        pz.startSolving();
    }
}

class puzzle {
    public static int[][] finalstate = {
        {1,2,3},
        {4,5,6},
        {7,8,0}
    };

    int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int[][] initialState = new int[3][3];

    public int heuristicValue;
    public int bestHeuristic;
    public int zeroPositionX;
    public int zeroPositionY;
    
    public puzzle(int[][] initialState){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) {
                this.initialState[i][j] = initialState[i][j];
            }
        }
    }

    public void startSolving(){
        calculateHeuristicValue();
        findZeroPosition();

        while (heuristicValue > 0) {
            System.out.println("loop,"+heuristicValue);
            letsSolve();
            calculateHeuristicValue();
        }

        // Print final solved state
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(initialState[i]));
        }
    }    

    public void calculateHeuristicValue(){
        heuristicValue = 0; // Reset heuristic before calculating
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(initialState[i][j] != finalstate[i][j]) {
                    heuristicValue++;
                }
            }
        }
    }

    public void findZeroPosition() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(initialState[i][j] == 0) {
                    zeroPositionX = i;
                    zeroPositionY = j;
                    return; // Exit once found
                }
            }
        }
    }

    public void letsSolve() {
        findZeroPosition(); // Ensure zero's position is known
    
        bestHeuristic = Integer.MAX_VALUE;
        int bestX = zeroPositionX;
        int bestY = zeroPositionY;
        Random rand = new Random();
    
        for(int i = 0; i < 4; i++) {
            int directionX = moves[i][0];
            int directionY = moves[i][1];
    
            int newX = zeroPositionX + directionX;
            int newY = zeroPositionY + directionY;
    
            if(newX >= 0 && newX <= 2 && newY >= 0 && newY <= 2) {
                int temp = initialState[zeroPositionX][zeroPositionY];
                initialState[zeroPositionX][zeroPositionY] = initialState[newX][newY];
                initialState[newX][newY] = temp;
    
                calculateHeuristicValue();
    
                // Accept worse moves with a small probability
                if (heuristicValue < bestHeuristic || rand.nextDouble() < 0.1) {
                    bestHeuristic = heuristicValue;
                    bestX = newX;
                    bestY = newY;
                }
    
                // Revert swap
                temp = initialState[zeroPositionX][zeroPositionY];
                initialState[zeroPositionX][zeroPositionY] = initialState[newX][newY];
                initialState[newX][newY] = temp;
            }
        }
    
        // Apply the best move found
        int temp = initialState[zeroPositionX][zeroPositionY];
        initialState[zeroPositionX][zeroPositionY] = initialState[bestX][bestY];
        initialState[bestX][bestY] = temp;
    
        findZeroPosition(); // Update zero position
    }    
}
