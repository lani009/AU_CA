/* Circular Queue의 구현. Ready_Queue의 역할을 수행한다. */

#ifndef QUEUE_H
#define QUEUE_H
#include "process.h"
#include <sys/types.h>

void offer(Process* value);
Process* poll();
void init_queue();
Process** queue_as_array();
Process* dispatch_process_by_pid(size_t pid);
size_t get_queue_size();

#endif