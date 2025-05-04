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

def dfs(start=(0, 0)):
    visited = set()
    stack = [(start, [])]

    while stack:
        state, path = stack.pop()
        if state in visited:
            continue
        visited.add(state)
        path = path + [state]
        if is_goal(state):
            return path
        for succ in get_successors(state):
            stack.append((succ, path))
    return None

def print_path(path):
    if path:
        print("Steps to reach goal using DFS:")
        for step in path:
            print(f"Jug A: {step[0]} L, Jug B: {step[1]} L")
    else:
        print("No solution found.")

if __name__ == "__main__":
    result = dfs()
    print_path(result)
