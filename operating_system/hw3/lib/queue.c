#include "queue.h"

#define MAX 100

ssize_t front = -1;
ssize_t rear = -1;
Process queue[MAX];

void offer(Process* value) {
    rear = (rear+1)%MAX;
    queue[rear] = *value;
}

Process* poll() {
    front = (front+1)%MAX;
    return &queue[front];
}

void init() {
    front = -1;
    rear = -1;
}

void array_as_queue(Process* array, size_t length) {
    init();

    for (size_t i = 0; i < length; i++)
    {
        offer(&array[i]);
    }
}

size_t get_queue_size() {
    return front > rear ? (MAX - front + rear + 1) : (rear - front);
}
