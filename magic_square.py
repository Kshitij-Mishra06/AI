# Function to generate an odd-order magic square using the Siamese method
def generate_magic_square(n):
    # Initialize an n x n grid filled with 0
    magic_square = [[0 for _ in range(n)] for _ in range(n)]

    # Start placing numbers from 1 to n^2
    num = 1

    # Initial position: first row, middle column
    i, j = 0, n // 2

    # Loop until we place all numbers from 1 to n^2
    while num <= n * n:
        # Place the current number
        magic_square[i][j] = num
        num += 1

        # Calculate new position (one row up, one column right)
        new_i = (i - 1) % n
        new_j = (j + 1) % n

        # If the cell is already occupied, move down instead
        if magic_square[new_i][new_j]:
            i = (i + 1) % n
        else:
            i, j = new_i, new_j

    # Print the magic square
    print(f"\nMagic Square of size {n}x{n}:\n")
    for row in magic_square:
        print("  ".join(f"{num:2d}" for num in row))

    # Calculate the magic constant
    magic_constant = n * (n * n + 1) // 2
    print(f"\nMagic Constant: {magic_constant}")

# Take user input
try:
    n = int(input("Enter an odd number (n ≥ 3) for the size of the Magic Square: "))
    if n < 3 or n % 2 == 0:
        print("Please enter an odd integer greater than or equal to 3.")
    else:
        generate_magic_square(n)

except ValueError:
    print("Invalid input. Please enter a valid integer.")
