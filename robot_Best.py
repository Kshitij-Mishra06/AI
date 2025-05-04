import heapq

# Direction vectors for up, down, left, right moves
directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

# Manhattan Distance Heuristic
def manhattan_distance(x1, y1, x2, y2):
    return abs(x1 - x2) + abs(y1 - y2)

# Best First Search Algorithm
def best_first_search(grid, start, goal):
    rows, cols = len(grid), len(grid[0])
    
    # Priority queue for open nodes (min-heap)
    open_list = []
    heapq.heappush(open_list, (0, start))  # (heuristic_cost, node)
    
    # Set to keep track of visited nodes
    visited = set()
    
    # Dictionary to keep track of the path
    came_from = {}
    
    while open_list:
        # Pop the node with the lowest heuristic cost
        current_cost, current = heapq.heappop(open_list)
        x, y = current
        
        # If the goal is reached, reconstruct the path
        if current == goal:
            path = []
            while current in came_from:
                path.append(current)
                current = came_from[current]
            path.append(start)
            path.reverse()
            return path
        
        # Mark the node as visited
        visited.add(current)
        
        # Explore neighbors
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            if 0 <= nx < rows and 0 <= ny < cols and grid[nx][ny] == 0 and (nx, ny) not in visited:
                # Calculate the heuristic cost (Manhattan distance to the goal)
                heuristic_cost = manhattan_distance(nx, ny, goal[0], goal[1])
                heapq.heappush(open_list, (heuristic_cost, (nx, ny)))
                came_from[(nx, ny)] = (x, y)
    
    # If no path is found
    return None

# Example Grid
# 0 represents open space, 1 represents obstacles
grid = [
    [0, 0, 0, 1, 0],
    [0, 1, 0, 1, 0],
    [0, 1, 0, 0, 0],
    [0, 0, 0, 1, 0],
    [0, 1, 0, 0, 0]
]

# Start and Goal positions
start = (0, 0)  # top-left corner
goal = (4, 4)   # bottom-right corner

# Run Best First Search
path = best_first_search(grid, start, goal)

# Print the path
if path:
    print("Path found:", path)
else:
    print("No path found.")
