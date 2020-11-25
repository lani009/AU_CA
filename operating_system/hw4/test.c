#include <stdio.h>

int main(void) {
    FILE *fp = fopen("./input1.txt", "r");
    char temp[50] = { 0 };

    size_t arr[30] = { 0 };
    while (!feof(fp))
    {
        fscanf(fp, "%s", temp);
        arr[temp[0] - 'a']++;
    }

    for (size_t i = 0; i < 26; i++)
    {
        printf("%c: %zu\n", i+'a', arr[i]);
    }
    
    

    fclose(fp);
    
}