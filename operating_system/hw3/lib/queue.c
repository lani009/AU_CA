#include "queue.h"
#include <stdlib.h>
#include <stdio.h>

#define MAX_READY_QUEUE 100

ssize_t front = -1;
ssize_t rear = -1;
Process* ready_queue[MAX_READY_QUEUE];

void offer(Process* value) {
    rear = (rear+1)%MAX_READY_QUEUE;
    ready_queue[rear] = value;
}

Process* poll() {
    front = (front+1)%MAX_READY_QUEUE;
    return ready_queue[front];
}

void init_queue() {
    front = -1;
    rear = -1;
}

Process** queue_as_array() {
    size_t queue_len = get_queue_size();

    Process** queue_array = malloc(sizeof(Process*) * queue_len);
    for (size_t i = front + 1, cnt = 0; cnt < queue_len; i++, cnt++)
    {
        queue_array[cnt] = ready_queue[i%MAX_READY_QUEUE];
    }
    
    return queue_array;
}

Process* dispatch_process_by_pid(size_t pid) {
    Process* selected_process = NULL;
    size_t queue_len = get_queue_size();

    for (size_t i = front + 1; i < front + 1 + queue_len; i++)
    {
        if (ready_queue[i%MAX_READY_QUEUE]->pid == pid) {
            selected_process = ready_queue[i%MAX_READY_QUEUE];
            size_t finish_condition = rear < front ? rear + MAX_READY_QUEUE : rear;
            for (size_t j = i; j < finish_condition; j++)
            {
                ready_queue[j%MAX_READY_QUEUE] = ready_queue[(j+1)%MAX_READY_QUEUE];
            }
            rear = rear - 1 < 0 ? MAX_READY_QUEUE - 1 : rear - 1;
            return selected_process;
        }
    }
    return selected_process;
}

size_t get_queue_size() {
    return front > rear ? (MAX_READY_QUEUE - front + rear + 1) : (rear - front);
}
