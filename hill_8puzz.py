import random

# Goal State of the 8-puzzle
GOAL_STATE = [[1, 2, 3],
              [4, 5, 6],
              [7, 8, 0]]

# Function to print the puzzle in a readable format
def print_puzzle(state):
    for row in state:
        print(row)
    print()

# Function to find the blank space (0) position
def find_blank(state):
    for i in range(3):
        for j in range(3):
            if state[i][j] == 0:
                return i, j

# Function to generate possible moves
def generate_neighbors(state):
    neighbors = []
    blank_x, blank_y = find_blank(state)

    # Directions the blank can move: up, down, left, right
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    for dx, dy in directions:
        new_x, new_y = blank_x + dx, blank_y + dy

        if 0 <= new_x < 3 and 0 <= new_y < 3:
            # Swap the blank space with the neighboring tile
            new_state = [row[:] for row in state]  # Create a copy of the current state
            new_state[blank_x][blank_y], new_state[new_x][new_y] = new_state[new_x][new_y], new_state[blank_x][blank_y]
            neighbors.append(new_state)

    return neighbors

# Heuristic function: Misplaced tiles
def misplaced_tiles(state):
    misplaced = 0
    for i in range(3):
        for j in range(3):
            if state[i][j] != GOAL_STATE[i][j] and state[i][j] != 0:
                misplaced += 1
    return misplaced

# Hill Climbing Algorithm
def hill_climbing(initial_state):
    current_state = initial_state
    while True:
        neighbors = generate_neighbors(current_state)
        if not neighbors:
            break

        # Find the neighbor with the least number of misplaced tiles
        next_state = min(neighbors, key=misplaced_tiles)

        # If no improvement, we've reached a local maximum
        if misplaced_tiles(next_state) >= misplaced_tiles(current_state):
            break

        current_state = next_state

        # If we have reached the goal state
        if current_state == GOAL_STATE:
            print("Goal reached!")
            return current_state

        print_puzzle(current_state)

    print("Failed to reach the goal.")
    return None

# Initial random state (not necessarily solvable)
initial_state = [
    [1, 2, 3],
    [5, 6, 0],
    [7, 8, 4]
]

print("Initial State:")
print_puzzle(initial_state)

# Run Hill Climbing algorithm
result = hill_climbing(initial_state)

if result:
    print("Final State (Goal State):")
    print_puzzle(result)
