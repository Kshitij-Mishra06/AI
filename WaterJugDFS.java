import java.util.*;

public class WaterJugDFS {
    static class State {
        int jug1, jug2;
        State parent;

        State(int jug1, int jug2, State parent) {
            this.jug1 = jug1;
            this.jug2 = jug2;
            this.parent = parent;
        }
    }

    static boolean solveDFS(int jug1Capacity, int jug2Capacity, int target) {
        Stack<State> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        stack.push(new State(0, 0, null));

        while (!stack.isEmpty()) {
            State current = stack.pop();
            String key = current.jug1 + "," + current.jug2;
            
            if (visited.contains(key)) continue;
            visited.add(key);

            if (current.jug1 == target || current.jug2 == target) {
                printSolution(current);
                return true;
            }

            List<State> nextStates = getNextStates(current, jug1Capacity, jug2Capacity);
            for (State next : nextStates) {
                stack.push(next);
            }
        }

        System.out.println("No solution found using DFS.");
        return false;
    }

    static List<State> getNextStates(State current, int jug1Cap, int jug2Cap) {
        List<State> successors = new ArrayList<>();
        int x = current.jug1, y = current.jug2;

        successors.add(new State(jug1Cap, y, current));  // Fill jug1
        successors.add(new State(x, jug2Cap, current));  // Fill jug2
        successors.add(new State(0, y, current));        // Empty jug1
        successors.add(new State(x, 0, current));        // Empty jug2
        
        int pour1to2 = Math.min(x, jug2Cap - y);
        successors.add(new State(x - pour1to2, y + pour1to2, current)); // Pour jug1 -> jug2

        int pour2to1 = Math.min(y, jug1Cap - x);
        successors.add(new State(x + pour2to1, y - pour2to1, current)); // Pour jug2 -> jug1

        return successors;
    }

    static void printSolution(State state) {
        List<State> path = new ArrayList<>();
        while (state != null) {
            path.add(state);
            state = state.parent;
        }
        Collections.reverse(path);
        System.out.println("Solution Path (DFS):");
        for (State s : path) {
            System.out.println("(" + s.jug1 + ", " + s.jug2 + ")");
        }
    }

    public static void main(String[] args) {
        int jug1Capacity = 4, jug2Capacity = 3, target = 2;
        solveDFS(jug1Capacity, jug2Capacity, target);
    }
}
