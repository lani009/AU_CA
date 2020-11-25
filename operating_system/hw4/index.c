#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

#define FILE_PATH "./input1.txt"
#define THREAD_NUM 3

pthread_mutex_t mutex_arr[26];
size_t alphabet_arr[26];

void *count_runner(void *arg);
void save_alphabet(char c);

typedef struct _thread_arg
{
    size_t origin;
    size_t offset;
} thread_arg;


int main(void) {
    FILE *input_fd = fopen(FILE_PATH, "r");
    pthread_t pid_arr[THREAD_NUM];
    size_t file_size = 0;
    size_t thread_offset_arr[THREAD_NUM+1] = { 0 };
    memset(alphabet_arr, 0, sizeof(size_t) * 26);

    for (size_t i = 0; i < 26; i++)
    {
        pthread_mutex_init(&mutex_arr[i], NULL);
    }

    fseek(input_fd, 0, SEEK_END);
    file_size = ftell(input_fd);

    thread_offset_arr[1] = file_size / THREAD_NUM;
    for (size_t i = 2; i < THREAD_NUM; i++)
    {
        thread_offset_arr[i] = thread_offset_arr[1] * i;
    }
    thread_offset_arr[THREAD_NUM] = file_size;


    fseek(input_fd, thread_offset_arr[1], SEEK_SET);

    while (fgetc(input_fd) != 0x20)
    {
        thread_offset_arr[1]++;
    }

    fseek(input_fd, thread_offset_arr[2], SEEK_SET);

    while (fgetc(input_fd) != 0x20)
    {
        thread_offset_arr[2]++;
    }

    for (size_t i = 0; i < THREAD_NUM; i++)
    {
        thread_arg *temp = malloc(sizeof(thread_arg));
        temp->origin = thread_offset_arr[i];
        temp->offset = thread_offset_arr[i+1];
        pthread_create(&pid_arr[i], NULL, count_runner, (void *) temp);
    }

    for (size_t i = 0; i < THREAD_NUM; i++)
    {
        pthread_join(pid_arr[i], NULL);
    }
    
    for (size_t i = 0; i < 26; i++)
    {
        printf("%c: %zu\n", 'a'+(char) i, alphabet_arr[i]);
    }    

    return 0;
}

void *count_runner(void *arg) {
    thread_arg t_arg = * (thread_arg*) arg;

    char temp[50];
    FILE *fp = fopen(FILE_PATH, "r");
    fseek(fp, t_arg.origin, SEEK_SET);

    while (ftell(fp) < t_arg.offset - 1)
    {
        fscanf(fp, "%s", temp);
        save_alphabet(temp[0]);
    }
    
    fclose(fp);
    free(arg);
    return 0;
}

void save_alphabet(char c) {
    pthread_mutex_lock(&mutex_arr[c-'a']);
    alphabet_arr[c-'a']++;
    pthread_mutex_unlock(&mutex_arr[c-'a']);
}