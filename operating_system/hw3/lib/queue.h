#ifndef QUEUE_H
#define QUEUE_H
#include "process.h"
#include <sys/types.h>
void offer(Process* value);
Process* poll();
void array_as_queue(Process* array, size_t length);
void init_queue();
size_t get_queue_size();
#endif