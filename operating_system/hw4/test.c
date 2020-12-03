#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
void *runner();
int main(void) {
    pthread_t a;
    pthread_create(&a, NULL, runner, NULL);
    void *returner;
    pthread_join(a, &returner);

    printf("%zd", (intptr_t)returner);
}

void *runner() {
    return (void *)13;
}