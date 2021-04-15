from typing import List


def index(arr: List[float]):
    n = arr.__len__()
    arr.insert(0, -float("inf"))    # 의사코드랑 indexing이 헷갈려서 집어넣음

    A = [[0 for _ in range(n+2)] for _ in range(n+2)]   # DP Memoization matrix
    R = [[0 for _ in range(n+2)] for _ in range(n+2)]

    # 대각 방향 벡터
    for dia in range(1, n+1):
        # -x 방향 벡터
        for i in range(1, n+2-dia):
            # -y 방향 벡터
            j = dia + i - 1

            minimum = float("inf")
            r_minimum = -1
            
            # root 후보 element 순회
            for r in range(i, j+1):
                root_sum = 0    # root temp sum

                # sigma^(i)_(j) {arr[m]} - arr[r]
                for m in range(i, j+1):
                    # if (m == r):
                    #     continue
                    # else:
                    root_sum += arr[m]

                # left subtree + right subtree + added_weight = val
                val = A[i][r-1] + A[r+1][j] + root_sum

                if (val < minimum):
                    minimum = val
                    r_minimum = r


            A[i][j] = minimum
            R[i][j] = r_minimum
    
    return A, R

if __name__ == "__main__":
    A, R = index([.18, .22, .15, .1, .06, .04, .25])
    print("===== A =====")
    for row in A:
        for col in row:
            print(f"{col:.2f} ", end="")
        print()

    print("===== R =====")
    for row in R:
        for col in row:
            print(f"{col} ", end="")
        print()
