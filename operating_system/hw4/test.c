#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
int main(void) {
    size_t alp[30] = { 0 };
    FILE *fp = fopen("./input2.txt", "r");
    char buffer[100];
    size_t i = 1;
    while (!feof(fp))
    {
        fscanf(fp, "%s", &buffer);
        printf("%s ", buffer);
        alp[buffer[0] - 'a']++;
        if (i++%10 == 0) {
            printf("\n");
        }
    }
    for (size_t j = 0; j < 30; j++)
    {
        printf("%c %zu\n", 'a'+j, alp[j]);
    }
    
    fclose(fp);
}