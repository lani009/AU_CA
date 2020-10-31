#ifndef PROCESS
#define PROCESS
#include <sys/types.h>
typedef struct _Process
{
    size_t remaining_time;
    size_t burst_time;
    size_t arrival_time;
    size_t exit_time;
    size_t pid;
} Process;

int is_null_process(Process* process);
void make_process_null(Process* process);
#endif