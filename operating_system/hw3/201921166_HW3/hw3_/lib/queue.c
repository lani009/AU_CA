#include "queue.h"
#include <stdlib.h>
#include <stdio.h>

ssize_t front = -1;
ssize_t rear = -1;
Process *ready_queue[MAX_READY_QUEUE];

/*
 * Queue의 가장 끝에 원소를 추가한다. (Push)
 */
void offer(Process *value)
{
    rear = (rear + 1) % MAX_READY_QUEUE;
    ready_queue[rear] = value;
}

/*
 * Queue의 앞에 있는 원소를 반환하고, queue에서 삭제한다. (Pop)
 */
Process *poll()
{
    front = (front + 1) % MAX_READY_QUEUE;
    return ready_queue[front];
}

/*
 * Queue를 초기화하여 내용물을 비운다.
 */
void init_queue()
{
    front = -1;
    rear = -1;
}

/*
 * Queue 내부의 원소들을 배열의 형태로 반환한다. 먼저 offer(push)된 원소가 배열의 앞쪽에 위치한다.
 */
Process **queue_as_array()
{
    size_t queue_len = get_queue_size();

    Process **queue_array = malloc(sizeof(Process *) * queue_len);
    for (size_t i = front + 1, cnt = 0; cnt < queue_len; i++, cnt++)
    {
        queue_array[cnt] = ready_queue[i % MAX_READY_QUEUE];
    }

    return queue_array;
}

/**
 * 해당 pid의 Process를 반환하고 queue에서 삭제한다.
 */
Process *dispatch_process_by_pid(size_t pid)
{
    Process *process = NULL;
    size_t queue_len = get_queue_size();

    for (size_t i = front + 1; i < front + 1 + queue_len; i++)
    {
        if (ready_queue[i % MAX_READY_QUEUE]->pid == pid)
        {
            process = ready_queue[i % MAX_READY_QUEUE];
            size_t finish_condition = rear < front ? rear + MAX_READY_QUEUE : rear;
            for (size_t j = i; j < finish_condition; j++)
            {
                ready_queue[j % MAX_READY_QUEUE] = ready_queue[(j + 1) % MAX_READY_QUEUE];
            }
            rear = rear - 1 < 0 ? MAX_READY_QUEUE - 1 : rear - 1;
            return process;
        }
    }
    return process;
}

/**
 * Queue에 저장되어있는 원소의 개수를 반환한다.
 */
size_t get_queue_size()
{
    return front > rear ? (MAX_READY_QUEUE - front + rear + 1) : (rear - front);
}
