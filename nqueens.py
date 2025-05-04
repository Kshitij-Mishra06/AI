# Function to check if placing a queen at (row, col) is safe
def is_safe(board, row, col, n):
    # Check for queen in the same column in previous rows
    for i in range(row):
        if board[i][col] == 1:
            return False
        # Check the left upper diagonal
        if col - row + i >= 0 and board[i][col - row + i] == 1:
            return False
        # Check the right upper diagonal
        if col + row - i < n and board[i][col + row - i] == 1:
            return False
    # If no conflicts, it's safe to place a queen
    return True

# Recursive function to solve the N-Queens problem
def solve(board, row, n):
    # Base case: If all queens are placed
    if row == n:
        # Print the board configuration
        for r in board:
            # Print 'Q' for queen and '.' for empty cell
            print(" ".join(['Q' if x == 1 else '.' for x in r]))
        print()  # Empty line between solutions
        return

    # Try placing queen in all columns of the current row
    for col in range(n):
        # Check if it's safe to place a queen at (row, col)
        if is_safe(board, row, col, n):
            # Place the queen
            board[row][col] = 1
            # Recur to place the rest of the queens
            solve(board, row + 1, n)
            # Backtrack: Remove the queen for trying next possibility
            board[row][col] = 0

# Main part of the program

# Ask the user to input the value of N
try:
    n = int(input("Enter the value of N for the N-Queens problem: "))
    
    # Validate that N is a positive number
    if n <= 0:
        print("Please enter a positive integer greater than 0.")
    else:
        # Initialize an empty NxN chessboard with all zeros
        board = [[0]*n for _ in range(n)]
        # Call the solve function starting from the first row
        solve(board, 0, n)

# Handle the case where input is not an integer
except ValueError:
    print("Invalid input. Please enter a valid integer.")
