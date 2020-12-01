#include <pthread.h>
#include <stdio.h>

void *function_name(void *arg);

int main() {
    pthread_t thread1, thread2, thread3;

    pthread_create(&thread1, NULL, function_name, 2);
    pthread_create(&thread2, NULL, function_name, 3);
    pthread_create(&thread3, NULL, function_name, 4);

    pthread_join(thread1, NULL);    // join의 역할 -> 해당 라인에서 스레드가 끝날 때 까지 기다림. 만약 끝났다면 스레드 메모리를 회수
    pthread_join(thread2, NULL);
    pthread_join(thread3, NULL);
}

void *function_name(void *arg) {
    for (size_t i = 0; i < 50; i++)
    {
        printf("%d\n", (int)arg);
    }
}