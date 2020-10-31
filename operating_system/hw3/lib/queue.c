#include "queue.h"

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

void array_as_queue(Process* array, size_t length) {
    init_queue();

    for (size_t i = 0; i < length; i++)
    {
        offer(&array[i]);
    }
}

size_t get_queue_size() {
    return front > rear ? (MAX_READY_QUEUE - front + rear + 1) : (rear - front);
}
