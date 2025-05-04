import heapq
import math

# Euclidean Distance Heuristic (h(n))
def euclidean_distance(x1, y1, x2, y2):
    return math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

# A* Algorithm to find the shortest path
def a_star(graph, start, goal, city_coordinates):
    # Priority Queue (min-heap) for open list
    open_list = []
    heapq.heappush(open_list, (0, start))  # (f(n), city)
    
    # Dictionaries to store g(n), f(n), and parent pointers
    g_score = {start: 0}  # g(n) is the cost from start to current node
    f_score = {start: euclidean_distance(city_coordinates[start][0], city_coordinates[start][1], city_coordinates[goal][0], city_coordinates[goal][1])}  # f(n) = g(n) + h(n)
    came_from = {}
    
    while open_list:
        # Get the city with the lowest f(n) value
        _, current_city = heapq.heappop(open_list)
        
        # If we reach the goal, reconstruct the path
        if current_city == goal:
            path = []
            while current_city in came_from:
                path.append(current_city)
                current_city = came_from[current_city]
            path.append(start)
            path.reverse()
            return path, f_score[goal]
        
        # Explore neighbors
        for neighbor, distance in graph[current_city]:
            tentative_g_score = g_score[current_city] + distance
            
            # If this path is better, update the g_score and f_score
            if neighbor not in g_score or tentative_g_score < g_score[neighbor]:
                g_score[neighbor] = tentative_g_score
                f_score[neighbor] = tentative_g_score + euclidean_distance(city_coordinates[neighbor][0], city_coordinates[neighbor][1], city_coordinates[goal][0], city_coordinates[goal][1])
                heapq.heappush(open_list, (f_score[neighbor], neighbor))
                came_from[neighbor] = current_city
    
    return None, float('inf')  # If no path is found

# Example Graph: Cities with roads and distances (graph adjacency list)
graph = {
    'A': [('B', 10), ('C', 5)],
    'B': [('A', 10), ('C', 2), ('D', 1)],
    'C': [('A', 5), ('B', 2), ('D', 9), ('E', 2)],
    'D': [('B', 1), ('C', 9), ('E', 4)],
    'E': [('C', 2), ('D', 4)]
}

# City Coordinates (x, y) for Euclidean Distance Heuristic
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

# Run A* Algorithm
path, distance = a_star(graph, start, goal, city_coordinates)

# Output the result
if path:
    print(f"Shortest path from {start} to {goal}: {path}")
    print(f"Total distance: {distance}")
else:
    print(f"No path found from {start} to {goal}")
