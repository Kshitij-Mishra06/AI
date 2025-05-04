import java.util.*;

class EightPuzzleBestFirst {
    static class State {
        int[][] board;
        int blankX, blankY;
        int cost; // Heuristic cost
        State parent;

        State(int[][] board, int blankX, int blankY, State parent) {
            this.board = new int[3][3];
            for (int i = 0; i < 3; i++)
                this.board[i] = Arrays.copyOf(board[i], 3);
            this.blankX = blankX;
            this.blankY = blankY;
            this.parent = parent;
            this.cost = calculateManhattanDistance();
        }

        // Manhattan Distance Heuristic
        private int calculateManhattanDistance() {
            int distance = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int value = board[i][j];
                    if (value != 0) {
                        int targetX = (value - 1) / 3;
                        int targetY = (value - 1) % 3;
                        distance += Math.abs(i - targetX) + Math.abs(j - targetY);
                    }
                }
            }
            return distance;
        }

        // Check if the current state is the goal state
        boolean isGoal() {
            int[][] goal = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
            };
            return Arrays.deepEquals(this.board, goal);
        }

        // Generate possible next states
        List<State> getNextStates() {
            List<State> successors = new ArrayList<>();
            int[] dx = {0, 0, -1, 1};
            int[] dy = {-1, 1, 0, 0};

            for (int i = 0; i < 4; i++) {
                int newX = blankX + dx[i];
                int newY = blankY + dy[i];

                if (newX >= 0 && newX < 3 && newY >= 0 && newY < 3) {
                    int[][] newBoard = new int[3][3];
                    for (int r = 0; r < 3; r++)
                        newBoard[r] = Arrays.copyOf(board[r], 3);

                    newBoard[blankX][blankY] = newBoard[newX][newY];
                    newBoard[newX][newY] = 0;

                    successors.add(new State(newBoard, newX, newY, this));
                }
            }
            return successors;
        }

        // Compare states based on heuristic cost
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof State) {
                return Arrays.deepEquals(this.board, ((State) obj).board);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Arrays.deepHashCode(board);
        }
    }

    // Best First Search Algorithm
    static void bestFirstSearch(int[][] startState) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        Set<State> visited = new HashSet<>();

        // Find blank tile position
        int blankX = -1, blankY = -1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (startState[i][j] == 0) {
                    blankX = i;
                    blankY = j;
                }
            }
        }

        State initialState = new State(startState, blankX, blankY, null);
        pq.add(initialState);

        while (!pq.isEmpty()) {
            State current = pq.poll();

            if (current.isGoal()) {
                printSolutionPath(current);
                return;
            }

            visited.add(current);

            for (State next : current.getNextStates()) {
                if (!visited.contains(next)) {
                    pq.add(next);
                }
            }
        }
        System.out.println("No solution found.");
    }

    // Print the solution path
    static void printSolutionPath(State state) {
        List<State> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = state.parent;
        }
        Collections.reverse(path);

        System.out.println("Solution Path (Best First Search - Manhattan Distance Heuristic):");
        for (State s : path) {
            printBoard(s.board);
            System.out.println();
        }
    }

    // Helper method to print a board
    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num == 0 ? " " : num);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Main method
    public static void main(String[] args) {
        int[][] startState = {
            {1, 2, 3},
            {4, 0, 6},
            {7, 5, 8} // 0 represents the blank space
        };

        bestFirstSearch(startState);
    }
}
