#include <pthread.h>
#include <stdio.h>

typedef struct _thread_arg
{
    size_t file_start, file_end;
} thread_arg;

size_t alphabet_arr['z'-'a'+1] = { 0 };
pthread_mutex_t mutex_lock['z'-'a'+1];

void *thread_function(void *_arg);

int main(void) {
    pthread_t thread1, thread2, thread3;

    FILE *fp = fopen("./input1.txt", "r");

    size_t file_size;
    
    fseek(fp, 0, SEEK_END);
    file_size = ftell(fp);  // 파일의 길이 -> n

    size_t thread1_end = file_size / 3;
    size_t thread2_end = thread1_end * 2;
    size_t thread3_end = file_size;

    fseek(fp, thread1_end, SEEK_SET);

    while (fgetc(fp) != ' ')
        ;

    thread1_end = ftell(fp);

    fseek(fp, thread2_end, SEEK_SET);

    while (fgetc(fp) != ' ')
        ;

    thread2_end = ftell(fp);

    for (size_t i = 0; i < 26; i++)
    {
        pthread_mutex_init(&mutex_lock[i], NULL);
    }
    
    thread_arg thread1_arg = { 0, thread1_end };
    pthread_create(&thread1, NULL, thread_function, &thread1_arg);

    thread_arg thread2_arg = { thread1_end, thread2_end };
    pthread_create(&thread2, NULL, thread_function, &thread2_arg);

    thread_arg thread3_arg = { thread2_end, file_size };
    pthread_create(&thread3, NULL, thread_function, &thread3_arg);

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
    pthread_join(thread3, NULL);

    for (size_t i = 0; i < 26; i++)
    {
        printf("%c: %zu\n", i+'a', alphabet_arr[i]);
    }
    return 0;
}

void *thread_function(void *_arg) {
    thread_arg arg = *(thread_arg*) _arg;

    FILE *fp = fopen("./input1.txt", "r");
    fseek(fp, arg.file_start, SEEK_SET);

    char buffer[100];
    while (ftell(fp) < arg.file_end) {
        fscanf(fp, "%s", buffer);
        pthread_mutex_lock(&mutex_lock[buffer[0] - 'a']);   // 0 ~ 25
        alphabet_arr[buffer[0] - 'a']++;
        pthread_mutex_unlock(&mutex_lock[buffer[0] - 'a']);
    }
    fclose(fp);
}