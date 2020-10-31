#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include "lib/queue.h"
#include "lib/process.h"
#include "lib/timeline.h"

#define DEBUG_MODE 0b111
#define TQ 4

Process* get_process_input();
void sim_fcfs();
void sim_rr();
void sim_hrn();
void print_debug_buffer();

size_t process_length = 0;  // 입력받은 프로세스 개수
Process* process_vec = 0;    //프로세스 배열
long double FCFS_AWT = 0;
long double RR_AWT = 0;
long double HRN_AWT = 0;
size_t buffer_cur = 0;
char debug_buffer[50000] = { 0 };
size_t fcfs_buffer_eol = 0;
size_t rr_buffer_eol = 0;

int main(void) {
    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_fcfs();

    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_rr();

    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_hrn();

    print_debug_buffer();

    printf("== Average Waiting Time ==\n");
    printf("FCFS  %2.2Lf(ms)\n", FCFS_AWT);
    printf("RR    %2.2Lf(ms)\n", RR_AWT);
    printf("HRN   %2.2Lf(ms)\n", HRN_AWT);
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
        process_list[process_input].remaining_time = temp1;
        process_list[process_input].burst_time = temp1;
        process_list[process_input].pid = process_input;
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
    buffer_cur += sprintf(debug_buffer+buffer_cur, "****FCFS Scheduling Simulation****\n");
    for (size_t time = 0; time < TIMEMAX; time++)
    {
        Process* in_process = get_process_in_time(time);
        if (is_null_process(in_process) == 0) {
            // 새로 들어온 프로세스가 있을 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%-2lu new process arrival\n", time, in_process->pid);
            offer(in_process);
        }
        if (is_null_process(running_process)) {
            // CPU가 아무런 작업도 하고 있지 않은 경우
            if (get_queue_size() == 0) {
                // 큐에도 프로세스가 없을 경우
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): CPU 노는 중\n", time);
                if (is_done(time)) {
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "FCFS Scheduling Simulation Exit\n\n");
                    break;
                }
            } else {
                // 큐에 프로세스가 있을 경우
                running_process = poll();
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%-2lu dispatch process\n", time, running_process->pid);
            }
            
        } else {
            // CPU가 어떠한 프로세스를 처리하고 있는 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%-2lu process running\n", time, running_process->pid);
            if (running_process->remaining_time-- == 1) {
                // remaining burst time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외
                running_process->exit_time = time;    // 작업이 끝난 시간을 초기화

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%-2lu process finish\n", time, running_process->pid);
                if (get_queue_size() != 0) {
                    // 아직 큐에 남아있는 프로세스가 있는 경우
                    running_process = poll();
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%-2lu dispatch process\n", time, running_process->pid);
                } else {
                    // 큐에 프로세스가 없는 경우
                }
            }
        }
        buffer_cur += sprintf(debug_buffer+buffer_cur, "\n");
    }

    size_t waiting_time_sum = 0;

    buffer_cur += sprintf(debug_buffer+buffer_cur, "FCFS waiting time\n");
    for (size_t i = 0; i < process_length; i++)
    {
        buffer_cur += sprintf(debug_buffer+buffer_cur, "p%-2lu  %2lu(ms)\n", process_vec[i].pid,  \
        process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time);

        waiting_time_sum += process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time;
    }
    buffer_cur += sprintf(debug_buffer+buffer_cur, "\n\n");
    FCFS_AWT = waiting_time_sum/(long double)process_length;

    fcfs_buffer_eol = ++buffer_cur;
}

void sim_rr() {
    buffer_cur += sprintf(debug_buffer+buffer_cur, "****RR Scheduling Simulation****\n");
    Process* running_process = 0;
    size_t timer = 0;
    for (size_t time = 0; time < TIMEMAX; time++)
    {
        Process* in_process = get_process_in_time(time);
        if (is_null_process(in_process) == 0) {
            // 새로 들어온 프로세스가 있을 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu new process arrival\n", time, in_process->pid);
            offer(in_process);
        }
        if (is_null_process(running_process)) {
            // CPU가 아무런 작업도 하고 있지 않은 경우
            if (get_queue_size() == 0) {
                // 큐에도 프로세스가 없을 경우
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): CPU 노는 중\n", time);
                if (is_done(time)) {
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "RR Scheduling Simulation Exit\n\n");
                    break;
                }
            } else {
                // 큐에 프로세스가 있을 경우
                running_process = poll();
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu dispatch process\n", time, running_process->pid);
            }
            
        } else {
            // CPU가 어떠한 프로세스를 처리하고 있는 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu process running\n", time, running_process->pid);
            if (running_process->remaining_time-- == 1) {
                // remaining burst time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외
                running_process->exit_time = time;    // 작업이 끝난 시간을 초기화

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu process finish\n", time, running_process->pid);
                if (get_queue_size() != 0) {
                    // 아직 큐에 남아있는 프로세스가 있는 경우
                    running_process = poll();
                    timer = 0;  // 타이머 초기화
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu dispatch process\n", time, running_process->pid);
                } else {
                    // 큐에 프로세스가 없는 경우
                }
            }
            if (timer++ == TQ-1) {
                // 프로세스가 Time Quantum에 도달하였을 때 인터럽트 하고 큐에 저장
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): process interrupt p%lu\n", time, running_process->pid);
                offer(running_process);
                running_process = poll();
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu dispatch process\n", time, running_process->pid);
                timer = 0;
            }
        }
        buffer_cur += sprintf(debug_buffer+buffer_cur, "\n");
    }

    size_t waiting_time_sum = 0;
    buffer_cur += sprintf(debug_buffer+buffer_cur, "RR waiting time\n");
    for (size_t i = 0; i < process_length; i++)
    {
        buffer_cur += sprintf(debug_buffer+buffer_cur, "p%-2lu  %2lu(ms)\n", process_vec[i].pid,  \
        process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time);
        waiting_time_sum += process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time;
    }
    buffer_cur += sprintf(debug_buffer+buffer_cur, "\n\n");
    RR_AWT = waiting_time_sum/(long double)process_length;

    rr_buffer_eol = ++buffer_cur;
}

void sim_hrn() {
    buffer_cur += sprintf(debug_buffer+buffer_cur, "****HRN Scheduling Simulation****\n");
    Process* running_process = 0;

    for (size_t time = 0; time < TIMEMAX; time++)
    {
        Process* in_process = get_process_in_time(time);
        if (is_null_process(in_process) == 0) {
            // 새로 들어온 프로세스가 있을 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu new process arrival\n", time, in_process->pid);
            offer(in_process);
        }
        if (is_null_process(running_process)) {
            // CPU가 아무런 작업도 하고 있지 않은 경우
            if (get_queue_size() == 0) {
                // 큐에도 프로세스가 없을 경우
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): CPU 노는 중\n", time);

                if (is_done(time)) {
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "HRN Scheduling Simulation Exit\n\n");
                    break;
                }
            } else {
                // 큐에 프로세스가 있을 경우
                running_process = poll();
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu dispatch process\n", time, running_process->pid);
            }
            
        } else {
            // CPU가 어떠한 프로세스를 처리하고 있는 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu process running\n", time, running_process->pid);
            if (running_process->remaining_time-- == 1) {
                // remaining burst time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외
                running_process->exit_time = time;    // 작업이 끝난 시간을 초기화

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu process finish\n", time, running_process->pid);
                if (get_queue_size() != 0) {
                    // 아직 큐에 남아있는 프로세스가 있는 경우
                    Process** remaining_process_array = queue_as_array();
                    long double highest_hrn = -1;
                    size_t next_process_id = 0;
                    for (size_t i = 0; i < get_queue_size(); i++)
                    {
                        long double temp_hrr = (time - remaining_process_array[i]->arrival_time + remaining_process_array[i]->burst_time) \
                                                                               /(long double)remaining_process_array[i]->burst_time;
                        if (temp_hrr > highest_hrn) {
                            highest_hrn = temp_hrr;
                            next_process_id = remaining_process_array[i]->pid;
                        }
                    }
                    running_process = dispatch_process_by_pid(next_process_id);
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "%2lu(ms): p%lu dispatch process\n", time, running_process->pid);
                } else {
                    // 큐에 프로세스가 없는 경우
                }
            }    
        }
        buffer_cur += sprintf(debug_buffer+buffer_cur, "\n");
    }

    size_t waiting_time_sum = 0;
    buffer_cur += sprintf(debug_buffer+buffer_cur, "HRN waiting time\n");
    for (size_t i = 0; i < process_length; i++)
    {
        buffer_cur += sprintf(debug_buffer+buffer_cur, "p%-2lu  %2lu(ms)\n", process_vec[i].pid,  \
        process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time);
        waiting_time_sum += process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time;
    }
    buffer_cur += sprintf(debug_buffer+buffer_cur, "\n\n");
    HRN_AWT = waiting_time_sum/(long double)process_length;
}

void print_debug_buffer() {
    if (DEBUG_MODE & 0b100) {
        printf("%s", debug_buffer);
    }
    if (DEBUG_MODE & 0b010) {
        printf("%s", debug_buffer+fcfs_buffer_eol);
    }
    if (DEBUG_MODE & 0b001) {
        printf("%s", debug_buffer+rr_buffer_eol);
    }
}
