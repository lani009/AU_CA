
# Python Program implementation
# of binary insertion sort
 
 
def binary_search(A, low, high, key):
    if high > low:
        mid = (high + low) // 2

        if A[mid] == key:
            return mid

        elif A[mid] > key:
            return binary_search(A, low, mid-1, key)

        else:
            return binary_search(A, mid+1, high, key)

    elif high == low:
        if A[low] > key:
            return low
        else:
            return low + 1

    else:
        return low

def insertion_sort(a, n):
    for i in range(2, n):   # c1  *  n
        j = i-1 # c2 * (n-1)

        key = a[i]  # c3 * (n-1)
        
        index = binary_search(a, 1, j, key) # c4 * log(n-1)

        while (j >= index): # c5 * sigma_j=2~n t_j
            a[j+1] = a[j]   # c6 * 
            j -= 1          # c7
        a[j+1] = key    # c8 * (n-1)
    return a
 
print("Sorted array:")
arr = [-100, 37, 23, 0, 31, 22, 17, 12, 72, 31, 46, 100, 88, 54]
print(insertion_sort(arr, arr.__len__()))
 
# Code contributed by Mohit Gupta_OMG
