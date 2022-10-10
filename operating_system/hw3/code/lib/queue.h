/* Circular Queue의 구현. Ready_Queue의 역할을 수행한다. */

#ifndef QUEUE_H
#define QUEUE_H
#include <sys/types.h>
#include "process.h"

/* Queue의 최대 길이 */
#define MAX_READY_QUEUE 100

void offer(Process *value);
Process *poll();
void init_queue();
Process **queue_as_array();
Process *dispatch_process_by_pid(int32_t pid);
size_t get_queue_size();

#endif
