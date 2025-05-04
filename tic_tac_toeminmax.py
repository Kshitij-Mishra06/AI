import math

# Initialize empty board
board = [[' ' for _ in range(3)] for _ in range(3)]

# Check if any moves left
def is_moves_left(board):
    for row in board:
        if ' ' in row:
            return True
    return False

# Evaluate the board
def evaluate(b):
    # Rows
    for row in b:
        if row[0] == row[1] == row[2]:
            if row[0] == 'X':
                return 10
            elif row[0] == 'O':
                return -10
    # Columns
    for col in range(3):
        if b[0][col] == b[1][col] == b[2][col]:
            if b[0][col] == 'X':
                return 10
            elif b[0][col] == 'O':
                return -10
    # Diagonals
    if b[0][0] == b[1][1] == b[2][2]:
        if b[0][0] == 'X':
            return 10
        elif b[0][0] == 'O':
            return -10
    if b[0][2] == b[1][1] == b[2][0]:
        if b[0][2] == 'X':
            return 10
        elif b[0][2] == 'O':
            return -10
    return 0

# Minimax function
def minimax(board, depth, is_max):
    score = evaluate(board)

    # Terminal conditions
    if score == 10 or score == -10:
        return score
    if not is_moves_left(board):
        return 0

    if is_max:  # AI turn
        best = -math.inf
        for i in range(3):
            for j in range(3):
                if board[i][j] == ' ':
                    board[i][j] = 'X'
                    best = max(best, minimax(board, depth + 1, False))
                    board[i][j] = ' '
        return best
    else:  # Human turn
        best = math.inf
        for i in range(3):
            for j in range(3):
                if board[i][j] == ' ':
                    board[i][j] = 'O'
                    best = min(best, minimax(board, depth + 1, True))
                    board[i][j] = ' '
        return best

# Find the best move for AI
def find_best_move(board):
    best_val = -math.inf
    best_move = (-1, -1)
    for i in range(3):
        for j in range(3):
            if board[i][j] == ' ':
                board[i][j] = 'X'
                move_val = minimax(board, 0, False)
                board[i][j] = ' '
                if move_val > best_val:
                    best_move = (i, j)
                    best_val = move_val
    return best_move

# Display the board
def print_board(board):
    for row in board:
        print('|'.join(row))
        print('-'*5)

# Main Game Loop
def play_game():
    print("Welcome to Tic Tac Toe! You are 'O'. AI is 'X'.")
    print_board(board)

    while is_moves_left(board) and evaluate(board) == 0:
        # Human move
        r, c = map(int, input("Enter row and column (0-2) separated by space: ").split())
        if board[r][c] != ' ':
            print("Cell already taken. Try again.")
            continue
        board[r][c] = 'O'
        print_board(board)

        if evaluate(board) != 0 or not is_moves_left(board):
            break

        # AI move
        ai_move = find_best_move(board)
        board[ai_move[0]][ai_move[1]] = 'X'
        print("AI has made its move:")
        print_board(board)

    score = evaluate(board)
    if score == 10:
        print("AI wins!")
    elif score == -10:
        print("You win!")
    else:
        print("It's a draw!")

# Run the game
play_game()
