def print_board(board):
    for row in board:
        print(" | ".join(row))
        print("-" * 5)

def check_win(board, player):
    for i in range(3):
        if all([board[i][j] == player for j in range(3)]) or \
           all([board[j][i] == player for j in range(3)]):
            return True
    if all([board[i][i] == player for i in range(3)]) or \
       all([board[i][2-i] == player for i in range(3)]):
        return True
    return False

def is_draw(board):
    return all([cell != ' ' for row in board for cell in row])

board = [[' ']*3 for _ in range(3)]
player = 'X'

while True:
    print_board(board)
    row = int(input(f"Player {player}, enter row (0-2): "))
    col = int(input(f"Player {player}, enter col (0-2): "))
    
    if board[row][col] != ' ':
        print("Cell occupied! Try again.")
        continue
    
    board[row][col] = player
    if check_win(board, player):
        print_board(board)
        print(f"Player {player} wins!")
        break
    if is_draw(board):
        print_board(board)
        print("It's a draw!")
        break
    player = 'O' if player == 'X' else'X'
