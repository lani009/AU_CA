/* 프로세스 구조체 조작에 필요한 함수들 */
#ifndef PROCESS_H
#define PROCESS_H

#include <stdbool.h>
#include <sys/types.h>
typedef struct _Process
{
    size_t remaining_time;
    size_t burst_time;
    size_t arrival_time;
    size_t exit_time;
    size_t pid;
} Process;

bool is_null_process(Process *process);
void make_process_null(Process *process);
#endif
