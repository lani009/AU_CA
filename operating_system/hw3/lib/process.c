#include "process.h"

void make_process_null(Process* process) {
    process->burst_time = 0;
}

int is_null_process(Process* process) {
    if (process == 0) {
        return 1;
    }
    if (process->burst_time == 0) {
        return 1;
    } else {
        return 0;
    }
}