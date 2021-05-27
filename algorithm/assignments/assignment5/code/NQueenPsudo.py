solution = list()

def N_Queens_new(row, n, N, board):
    if n == 0:
        return True

    for j in range(1, N+1):
        if not is_attack(row, j, board, N):
            board[row][j] = 1
            solution.append((row, j))
            if N_Queens_new(row+1, n-1, N, board):
                if n == N:
                    return solution
                else:
                    return True

            board[row][j] = 0
            solution.remove((row, j))

    return False

def is_attack(i, j, board, N):
    for k in range(1, i):
        if board[k][j]==1:
            return True
    k = i-1
    l = j-1
    while k >= 1 and l >= 1:
        if board[k][l]==1:
            return True
        k = k-1
        l = l-1
    k = i-1
    l = j+1
    while k >= 1 and l <= N:
        if board[k][l]==1:
            return True
        k = k-1
        l = l+1

    return False

n = 4
board = list()
for _ in range(n+1):
    board.append([0 for _ in range(n+1)])
a = N_Queens_new(1, n, n, board)
print(a)

for row in board:
    print(row)
