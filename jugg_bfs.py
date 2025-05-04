from collections import deque

def is_goal(state):
    return state[0] == 2  # Goal: 2 liters in Jug A

def get_successors(state, a_max=4, b_max=3):
    a, b = state
    successors = set()

    successors.add((a_max, b))         # Fill Jug A
    successors.add((a, b_max))         # Fill Jug B
    successors.add((0, b))             # Empty Jug A
    successors.add((a, 0))             # Empty Jug B
    pour = min(a, b_max - b)           # Pour A -> B
    successors.add((a - pour, b + pour))
    pour = min(b, a_max - a)           # Pour B -> A
    successors.add((a + pour, b - pour))

    return successors

def bfs(start=(0, 0)):
    visited = set()
    queue = deque([(start, [])])

    while queue:
        state, path = queue.popleft()
        if state in visited:
            continue
        visited.add(state)
        path = path + [state]
        if is_goal(state):
            return path
        for succ in get_successors(state):
            queue.append((succ, path))
    return None

def print_path(path):
    if path:
        print("Steps to reach goal using BFS:")
        for step in path:
            print(f"Jug A: {step[0]} L, Jug B: {step[1]} L")
    else:
        print("No solution found.")

if _name_ == "_main_":
    result = bfs()
    print_path(result)
