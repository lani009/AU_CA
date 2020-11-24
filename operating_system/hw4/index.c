#include <stdio.h>
#include <pthread.h>

#define THREAD_NUM 3
typedef struct _thread_arg
{
    int origin;
    int offset;
} thread_arg;


int main(void) {
    FILE *input_fd = fopen("./input1.txt", "r");
    pthread_t pid_arr[THREAD_NUM];
    int file_size = 0;
    int thread_offset_arr[THREAD_NUM+1] = { 0 };

    fseek(input_fd, 0, SEEK_END);
    file_size = ftell(input_fd);

    thread_offset_arr[1] = file_size / THREAD_NUM;
    for (size_t i = 2; i < THREAD_NUM; i++)
    {
        /* code */
    }
    

    thread_offset_arr[2] = thread_offset_arr[1] * 2;

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




    pthread_create(&pid_arr[0], NULL, count_runner, (void *) ((thread_arg) {thread_offset_arr[0], thread_offset_arr[1]}));
    pthread_create(&pid_arr[1], NULL, count_runner, (void *) ((thread_arg) {thread_offset_arr[1], thread_offset_arr[2]}));
    pthread_create(&pid_arr[2], NULL, count_runner, (void *) ((thread_arg) {thread_offset_arr[2], thread_offset_arr[3]}));
    

    return 0;
}

void *count_runner(void *arg) {

}

void save_alphabet() {

}