#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include "lib/queue.h"
#include "lib/process.h"
#include "lib/timeline.h"

#define DEBUG_MODE 0b100

size_t process_length = 0;  // 입력받은 프로세스 개수
Process* process_vec = 0;    //프로세스 배열
long double FCFS_WAITING_TIME_AVG = 0;
Process* get_process_input();
void sim_fcfs();

int main(void) {
    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_fcfs();



    
    printf("waiting_time_avg = %Lf\n", FCFS_WAITING_TIME_AVG);
    return 0;
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
    Process* running_process = 0;
    size_t remaining_burst_time;
    for (size_t time = 0; time < TIMEMAX; time++)
    {
        Process* in_process = get_process_in_time(time);
        if (is_null_process(in_process) == 0) {
            // time(ms)에 프로세스가 새로 들어왔을 경우
            printf("%lu(ms): new process arrival p%lu\n", time, in_process->arrival_time);
            in_process->new_time = time;    // 새로 들어온 시간을 초기화
            if (is_null_process(running_process)) {
                // CPU가 아무런 작업도 하고 있지 않은 경우
                printf("%lu(ms): CPU 노는 중\n", time);
                if (get_queue_size() == 0) {
                    // 큐에도 프로세스가 없을 경우
                    remaining_burst_time = in_process->burst_time;
                    running_process = in_process;
                    printf("%lu(ms): process start p%lu\n", time, running_process->arrival_time);
                } else {
                    // 큐에 프로세스가 있을 경우
                    running_process = poll();
                    printf("%lu(ms): poll process p%lu\n", time, running_process->arrival_time);
                    remaining_burst_time = running_process->burst_time;
                }
                
            } else {
                // CPU가 어떠한 프로세스를 처리하고 있는 경우
                printf("%lu(ms): process running p%lu\n", time, running_process->arrival_time);
                offer(in_process);
                printf("%lu(ms): offer process p%lu\n", time, in_process->arrival_time);
                if (remaining_burst_time-- == 1) {
                    // remaining_burst_time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외
                    running_process->finish_time = time;    // 작업이 끝난 시간을 초기화
                    // remaining_burst_time이 0이 되어 작업이 끝난 경우
                    make_process_null(running_process);
                    printf("%lu(ms): process finish p%lu\n", time, running_process->arrival_time);
                    if (get_queue_size() != 0) {
                        // 아직 큐에 남아있는 프로세스가 있는 경우
                        running_process = poll();
                        printf("%lu(ms): poll process p%lu\n", time, running_process->arrival_time);
                        remaining_burst_time = running_process->burst_time;
                    } else {
                        // 큐에 프로세스가 없는 경우
                    }
                }
            }
        } else {
            // time(ms)에 프로세스가 들어오지 않았을 경우
            if (is_null_process(running_process)) {
                // CPU가 아무런 작업도 하고 있지 않은 경우
                printf("%lu(ms): CPU 노는 중\n", time);
            } else {
                // CPU가 어떠한 프로세스를 처리하고 있는 경우
                printf("%lu(ms): process running p%lu\n", time, running_process->arrival_time);
                if (remaining_burst_time-- == 1) {
                    // remaining_burst_time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외
                    running_process->finish_time = time;    // 작업이 끝난 시간을 초기화
                    // remaining_burst_time이 0이 되어 작업이 끝난 경우
                    make_process_null(running_process);
                    printf("%lu(ms): process finish p%lu\n", time, running_process->arrival_time);
                    if (get_queue_size() != 0) {
                        // 아직 큐에 남아있는 프로세스가 있는 경우
                        running_process = poll();
                        remaining_burst_time = running_process->burst_time;
                        printf("%lu(ms): poll process p%lu\n", time, running_process->arrival_time);
                    } else {
                        // 큐에 프로세스가 없는 경우
                    }
                }
            }

        }
        printf("\n");
    }

    size_t waiting_time_sum = 0;
    for (size_t i = 0; i < process_length; i++)
    {
        waiting_time_sum += process_vec[i].finish_time - process_vec[i].new_time;
    }
    FCFS_WAITING_TIME_AVG = waiting_time_sum/(long double)process_length;
    if (!(DEBUG_MODE & 0b100)) {
        system("clear");
    }
}

void sim_rr() {
    
}