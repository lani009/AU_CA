#include <stdio.h>
#include <stdlib.h>
#include "lib/queue.h"
#include "lib/process.h"
#include "lib/timeline.h"

size_t process_length = 0;  // 입력받은 프로세스 개수
Process* process_vec = NULL;    //프로세스 배열

Process* get_process_input();
void sim_fcfs();

int main(void) {
    process_vec  = get_process_input();
}


Process* get_process_input() {
    FILE *fp = fopen("input.txt", "r");

    int tmp_result; // fscanf 결과값
    int temp1, temp2;   // bufst_time, arrival_time
    size_t process_size = 5;    // 받을 수 있는 프로세스 개수

    Process* process_list = malloc(sizeof(Process) * process_size); // 프로세스 배열 메모리 할당
    size_t process_input = 0;   // 입력받은 프로세스 개수
    while ((tmp_result = fscanf(fp, "%d %d", &temp1, &temp2)) != -1) {
        process_list[process_input].burst_time = temp1;
        process_list[process_input++].arrival_time = temp2;
        
        // 담을 공간이 부족할 경우 배열 길이 증량
        if (process_input == process_size - 1) {
            process_size += 5;
            process_list = realloc(process_list, sizeof(Process) * process_size);
        }
    }
    process_length = process_input;

    fclose(fp);
    
    return process_list;
}

void sim_fcfs() {
    size_t waiting_time_temp;
    size_t waiting_time_sum = 0;
    Process* running_process = NULL;
    size_t remaining_burst_time;
    // array_as_queue(process_vec, process_length);
    for (size_t time = 0;; time++)
    {
        Process* in_process = get_process_in_time(time);
        if (in_process != NULL) {
            // time(ms)에 프로세스가 새로 들어왔을 경우
            printf("%zu(ms): new process arrival\n", time);
            if (running_process == NULL) {
                // CPU가 아무런 작업도 하고 있지 않은 경우
                printf("%zu(ms): CPU 노는 중\n");
                if (get_queue_size() == 0) {
                    // 큐에도 프로세스가 없을 경우
                    remaining_burst_time = in_process->burst_time;
                    printf("%zu(ms): process start p%zu", time, running_process->arrival_time);
                    running_process = in_process;
                } else {
                    // 큐에 프로세스가 있을 경우
                    running_process = poll();
                    printf("%zu(ms): poll process p%zu\n", time, running_process->arrival_time);
                    remaining_burst_time = running_process->burst_time;
                }
                
            } else {
                // CPU가 어떠한 프로세스를 처리하고 있는 경우
                printf("%zu(ms): process running p%zu", time, running_process->arrival_time);
                if (remaining_burst_time-- == 0) {
                    // remaining_burst_time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외

                    // remaining_burst_time이 0이 되어 작업이 끝난 경우
                    running_process = NULL;
                    printf("%zu(ms): process finish p%zu", time, running_process->arrival_time);
                    if (get_queue_size() != 0) {
                        // 아직 큐에 남아있는 프로세스가 있는 경우
                        running_process = poll();
                        printf("%zu(ms): poll process p%zu\n", time, running_process->arrival_time);
                        remaining_burst_time = running_process->burst_time;
                    } else {
                        // 큐에 프로세스가 없는 경우
                    }
                } else {
                    // 프로세스 작업이 아직 끝나지 않은 경우
                    offer(in_process);
                    printf("%zu(ms): offer process p%zu\n", time, in_process->arrival_time);
                }
            }
        } else {
            // time(ms)에 프로세스가 들어오지 않았을 경우
            if (running_process == NULL) {
                // CPU가 아무런 작업도 하고 있지 않은 경우
                printf("%zu(ms): CPU 노는 중\n", time);
            } else {
                // CPU가 어떠한 프로세스를 처리하고 있는 경우
                printf("%zu(ms): process running p%zu", time, running_process->arrival_time);
                if (remaining_burst_time-- == 0) {
                    // remaining_burst_time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외

                    // remaining_burst_time이 0이 되어 작업이 끝난 경우
                    running_process = NULL;
                    printf("%zu(ms): process finish p%zu", time, running_process->arrival_time);
                    if (get_queue_size() != 0) {
                        // 아직 큐에 남아있는 프로세스가 있는 경우
                        running_process = poll();
                        remaining_burst_time = running_process->burst_time;
                        printf("%zu(ms): poll process p%zu\n", time, running_process->arrival_time);
                    } else {
                        // 큐에 프로세스가 없는 경우
                    }
                }
                
            }

        }
    }
    
}