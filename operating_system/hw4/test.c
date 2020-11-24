#include <stdio.h>

int main(void) {
    FILE *fp = fopen("./input1.txt", "r");
    fseek(fp, 1, SEEK_SET);
    char arr[100];
    for (size_t i = 0; i < 100; i++)
    {
        fscanf(fp, "%s", &arr);
        printf("%s\n", arr);
    }

    fclose(fp);
    
}