import heapq
import math

# Euclidean Distance Heuristic
def euclidean_distance(x1, y1, x2, y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

# Best-First Search Algorithm for shortest path
def best_first_search(graph, start, goal, city_coordinates):
    # Priority Queue (min-heap) to store cities based on heuristic
    pq = []
    heapq.heappush(pq, (0, start))  # (heuristic_cost, city)
    
    # Dictionary to store the path from start to goal
    came_from = {}
    
    # Set of visited cities
    visited = set()
    
    while pq:
        # Get the city with the lowest heuristic cost
        current_cost, current_city = heapq.heappop(pq)
        
        # If the goal is reached, reconstruct the path
        if current_city == goal:
            path = []
            while current_city in came_from:
                path.append(current_city)
                current_city = came_from[current_city]
            path.append(start)
            path.reverse()
            return path
        
        visited.add(current_city)
        
        # Explore neighbors
        for neighbor, distance in graph[current_city]:
            if neighbor not in visited:
                # Calculate the heuristic cost (Euclidean distance to goal)
                goal_x, goal_y = city_coordinates[goal]
                neighbor_x, neighbor_y = city_coordinates[neighbor]
                heuristic_cost = euclidean_distance(neighbor_x, neighbor_y, goal_x, goal_y)
                
                # Add neighbor to the priority queue with heuristic cost
                heapq.heappush(pq, (heuristic_cost, neighbor))
                came_from[neighbor] = current_city
    
    return None  # Return None if no path is found

# Example Graph: Cities with roads and distances (graph adjacency list)
graph = {
    'A': [('B', 10), ('C', 5)],
    'B': [('A', 10), ('C', 2), ('D', 1)],
    'C': [('A', 5), ('B', 2), ('D', 9), ('E', 2)],
    'D': [('B', 1), ('C', 9), ('E', 4)],
    'E': [('C', 2), ('D', 4)]
}

# City Coordinates (x, y)
city_coordinates = {
    'A': (0, 0),
    'B': (2, 2),
    'C': (1, 3),
    'D': (4, 1),
    'E': (5, 5)
}

# Start and Goal cities
start = 'A'
goal = 'E'

# Run Best-First Search
path = best_first_search(graph, start, goal, city_coordinates)

# Output the result
if path:
    print(f"Shortest path from {start} to {goal}: {path}")
else:
    print(f"No path found from {start} to {goal}")
