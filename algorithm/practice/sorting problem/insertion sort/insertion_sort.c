// Recursive C++ program for insertion sort
#include <stdio.h>

// Recursive function to sort an array using
// insertion sort
void insertionSortRecursive(int arr[], int n)
{
    // Base case
    if (n < 2)
        return;

    // Sort first n-1 elements
    insertionSortRecursive(arr, n - 1);

    // Insert last element at its correct position
    // in sorted array.
    int key = arr[n];
    int i = n - 1;

    /* Move elements of arr[0..i-1], that are 
      greater than key, to one position ahead 
      of their current position */
    while (i > 0 && arr[i] > key)
    {
        arr[i + 1] = arr[i];
        i--;
    }
    arr[i + 1] = key;
}

// A utility function to print an array of size n
void printArray(int arr[], int n)
{
    for (int i = 1; i <= n; i++)
        printf("%d ", arr[i]);
}

/* Driver program to test insertion sort */
int main()
{
    int arr[] = {-1, 5, 0, -4, 1, 8, 7, 6, 41, 9, 12, 153};
    int n = sizeof(arr) / sizeof(int);

    insertionSortRecursive(arr, n);
    printArray(arr, n-1);

    return 0;
}
