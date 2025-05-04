import heapq

# Manhattan Distance Heuristic (h(n))
def manhattan_distance(x1, y1, x2, y2):
    return abs(x2 - x1) + abs(y2 - y1)

# A* Algorithm to find the shortest path for robot navigation
def a_star(grid, start, goal):
    # Priority Queue (min-heap) for open list
    open_list = []
    heapq.heappush(open_list, (0, start))  # (f(n), (x, y))
    
    # Dictionaries to store g(n), f(n), and parent pointers
    g_score = {start: 0}
    f_score = {start: manhattan_distance(start[0], start[1], goal[0], goal[1])}
    came_from = {}
    
    # Directions for moving (up, down, left, right)
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    
    while open_list:
        # Get the node with the lowest f(n) value
        _, current = heapq.heappop(open_list)
        
        # If we reach the goal, reconstruct the path
        if current == goal:
            path = []
            while current in came_from:
                path.append(current)
                current = came_from[current]
            path.append(start)
            path.reverse()
            return path
        
        # Explore neighbors
        for direction in directions:
            neighbor = (current[0] + direction[0], current[1] + direction[1])
            
            # Check if neighbor is within bounds and not blocked
            if 0 <= neighbor[0] < len(grid) and 0 <= neighbor[1] < len(grid[0]) and grid[neighbor[0]][neighbor[1]] == 0:
                tentative_g_score = g_score[current] + 1  # Assuming uniform cost for each move
                
                # If this path is better, update the g_score and f_score
                if neighbor not in g_score or tentative_g_score < g_score[neighbor]:
                    g_score[neighbor] = tentative_g_score
                    f_score[neighbor] = tentative_g_score + manhattan_distance(neighbor[0], neighbor[1], goal[0], goal[1])
                    heapq.heappush(open_list, (f_score[neighbor], neighbor))
                    came_from[neighbor] = current
    
    return None  # If no path is found

# Example Grid (0 = free space, 1 = obstacle)
grid = [
    [0, 0, 0, 0, 0],
    [0, 1, 1, 1, 0],
    [0, 0, 0, 1, 0],
    [0, 1, 0, 0, 0],
    [0, 0, 0, 0, 0]
]

# Start and Goal positions
start = (0, 0)  # Top-left corner
goal = (4, 4)   # Bottom-right corner

# Run A* Algorithm
path = a_star(grid, start, goal)

# Output the result
if path:
    print(f"Shortest path from {start} to {goal}: {path}")
else:
    print(f"No path found from {start} to {goal}")
