#ifndef TIMELINE_H
#define TIMELINE_H
#define TIMEMAX 100
#include <stdio.h>
#include <sys/types.h>
#include "process.h"
void init_timeline(Process* process_array, size_t length);
Process* get_process_in_time(size_t time);
int is_done(size_t time);
#endif