#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include "lib/queue.h"
#include "lib/process.h"
#include "lib/timeline.h"

/*
 * Scheduling 알고리즘 실행 과정을 보기 위한 디버깅용 매크로
 * 
 * [USAGE]
 * FCFS: 0b100
 * RR:   0b010
 * HRN:  0b001
 * 
 * [EXAMPLE]
 * FCFS + RR: 0b110
 * all:       0b111
 * nothing:   0b000
 */
#define DEBUG_MODE 0b000

/* Time Quantum */
#define TQ 4

/* 함수 정의 */
Process* get_process_input();
void sim_fcfs();
void sim_rr();
void sim_hrn();
void print_debug_buffer();

/* 전역 변수 정의 */
size_t process_length = 0;  // 입력받은 프로세스 개수
Process* process_vec = 0;   //프로세스 배열
long double FCFS_AWT = 0;   // FCFS Average Waiting Time
long double RR_AWT = 0;     // RR Average Waiting Time
long double HRN_AWT = 0;    // HRN Average Waiting Time
size_t buffer_cur = 0;      // 디버깅용 출력 버퍼 커서
char debug_buffer[50000] = { 0 };   // 디버깅용 출력 버퍼
size_t fcfs_buffer_eol = 0;         // FCFS 디버깅 커서의 위치(EOL of FCFS)
size_t rr_buffer_eol = 0;           // RR 디버깅 커서의 위치(EOL of RR)

int main(void) {
    /* input.txt로 부터 프로세스 정보를 받아옴 */
    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_fcfs();         // FCFS 시뮬레이션
    free(process_vec);  // 메모리 free

    /* input.txt로 부터 프로세스 정보를 받아옴 */
    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_rr();           // RR 시뮬레이션
    free(process_vec);  // 메모리 free

    /* input.txt로 부터 프로세스 정보를 받아옴 */
    process_vec  = get_process_input();
    init_timeline(process_vec, process_length);
    sim_hrn();          // HRN 시뮬레이션
    free(process_vec);  // 메모리 free

    /* 디버깅용 출력 버퍼를 flush 한다 */
    print_debug_buffer();

    printf("== Average Waiting Time ==\n");
    printf("FCFS  %2.2Lf(ms)\n", FCFS_AWT);
    printf("RR    %2.2Lf(ms)\n", RR_AWT);
    printf("HRN   %2.2Lf(ms)\n", HRN_AWT);
    return 0;
}

/*
 * input.txt에 저장된 값을 Process 배열의 형태로 변환한다.
 */
Process* get_process_input() {
    FILE *fp = fopen("input.txt", "r");

    int tmp_result; // fscanf 반환값
    int temp1, temp2;   // burst_time, arrival_time
    size_t process_size = 5;    // 받을 수 있는 프로세스 개수

    Process* process_list = malloc(sizeof(Process) * process_size); // 프로세스 배열 메모리 할당
    size_t process_input = 0;   // 입력받은 프로세스 개수

    /* EOL에 도달할 때 까지 계속해서 입력을 받음 */
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
    process_length = process_input; // 입력받은 프로세스의 개수 저장

    fclose(fp);
    
    return process_list;
}

/*
 * FCFS Scheduling 알고리즘을 시뮬레이션 한다.
 */
void sim_fcfs() {
    Process* running_process = 0;   // CPU를 점유하고 있는 프로세스
    buffer_cur += sprintf(debug_buffer+buffer_cur, "****FCFS Scheduling Simulation****\n");
    for (size_t current_time = 0; current_time < TIMEMAX; current_time++)
    {
        Process* in_process = get_process_in_time(current_time);    // time (ms)에 arrival한 프로세스를 받아온다.
        if (is_null_process(in_process) == 0) {
            // 새로 들어온 프로세스가 있을 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%-2zu new process arrival\n", current_time, in_process->pid);
            offer(in_process);  // 큐에 프로세스 저장
        }
        if (is_null_process(running_process)) {
            // CPU가 아무런 작업도 하고 있지 않은 경우
            if (get_queue_size() == 0) {
                // 큐에도 프로세스가 없을 경우
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): CPU 노는 중\n", current_time);
                if (is_done(current_time)) {
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "FCFS Scheduling Simulation Exit\n\n");
                    break;  // 모든 작업이 끝나고, 더이상 도착할 프로세스가 없는 경우 시뮬레이션을 종료한다. 
                }
            } else {
                // 큐에 프로세스가 있을 경우
                running_process = poll();   // 큐에서 프로세스를 빼온다.
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%-2zu dispatch process\n", current_time, running_process->pid);
            }
            
        } else {
            // CPU가 어떠한 프로세스를 처리하고 있는 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%-2zu process running\n", current_time, running_process->pid);
            if (--running_process->remaining_time == 0) {
                // remaining burst time 을 1 줄이고, 만약에 작업이 끝난 경우
                running_process->exit_time = current_time;    // 작업이 끝난 시간을 초기화

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%-2zu process finish\n", current_time, running_process->pid);
                if (get_queue_size() != 0) {
                    // 아직 큐에 남아있는 프로세스가 있는 경우
                    running_process = poll();   // 큐에서 프로세스를 빼온다.
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%-2zu dispatch process\n", current_time, running_process->pid);
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
        buffer_cur += sprintf(debug_buffer+buffer_cur, "p%-2zu  %2zu(ms)\n", process_vec[i].pid,  \
        process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time);

        /* Waiting time = Turnaround time - Burst time
         * Turnaround time = Exit time - Arrival time
         * 따라서
         * Waiting time = Exit time - Arrival time - Burst time
         */
        waiting_time_sum += process_vec[i].exit_time - process_vec[i].arrival_time - process_vec[i].burst_time;
    }
    buffer_cur += sprintf(debug_buffer+buffer_cur, "\n\n");
    FCFS_AWT = waiting_time_sum/(long double)process_length;

    fcfs_buffer_eol = ++buffer_cur;
}

void sim_rr() {
    buffer_cur += sprintf(debug_buffer+buffer_cur, "****RR Scheduling Simulation****\n");
    Process* running_process = 0;
    size_t timer = 0;   // Time Quantum을 준수하기 위한 타이머 생성
    for (size_t current_time = 0; current_time < TIMEMAX; current_time++)
    {
        Process* in_process = get_process_in_time(current_time);
        if (is_null_process(in_process) == 0) {
            // 새로 들어온 프로세스가 있을 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu new process arrival\n", current_time, in_process->pid);
            offer(in_process);
        }
        if (is_null_process(running_process)) {
            // CPU가 아무런 작업도 하고 있지 않은 경우
            if (get_queue_size() == 0) {
                // 큐에도 프로세스가 없을 경우
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): CPU 노는 중\n", current_time);
                if (is_done(current_time)) {
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "RR Scheduling Simulation Exit\n\n");
                    break;
                }
            } else {
                // 큐에 프로세스가 있을 경우
                running_process = poll();
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu dispatch process\n", current_time, running_process->pid);
            }
            
        } else {
            // CPU가 어떠한 프로세스를 처리하고 있는 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu process running\n", current_time, running_process->pid);
            if (--running_process->remaining_time == 0) {
                // remaining burst time 을 1 줄이고, 만약에 0 일 경우 작업이 끝난 것이므로 running_process에서 제외
                running_process->exit_time = current_time;    // 작업이 끝난 시간을 초기화

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu process finish\n", current_time, running_process->pid);
                if (get_queue_size() != 0) {
                    // 아직 큐에 남아있는 프로세스가 있는 경우
                    running_process = poll();
                    timer = 0;  // 타이머 초기화
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu dispatch process\n", current_time, running_process->pid);
                } else {
                    // 큐에 프로세스가 없는 경우
                }
            }
            if (++timer == TQ) {
                // 프로세스가 Time Quantum에 도달하였을 때 인터럽트 하고 큐에 저장

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): process interrupt p%zu\n", current_time, running_process->pid);
                offer(running_process); // 현재 실행중이던 프로세스를 큐에 담는다.
                running_process = poll();   // 큐에서 새로운 프로세스를 꺼내온다.
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu dispatch process\n", current_time, running_process->pid);
                timer = 0;  // timer 0으로 초기화
            }
        }
        buffer_cur += sprintf(debug_buffer+buffer_cur, "\n");
    }

    size_t waiting_time_sum = 0;
    buffer_cur += sprintf(debug_buffer+buffer_cur, "RR waiting time\n");
    for (size_t i = 0; i < process_length; i++)
    {
        buffer_cur += sprintf(debug_buffer+buffer_cur, "p%-2zu  %2zu(ms)\n", process_vec[i].pid,  \
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

    for (size_t current_time = 0; current_time < TIMEMAX; current_time++)
    {
        Process* in_process = get_process_in_time(current_time);
        if (is_null_process(in_process) == 0) {
            // 새로 들어온 프로세스가 있을 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu new process arrival\n", current_time, in_process->pid);
            offer(in_process);
        }
        if (is_null_process(running_process)) {
            // CPU가 아무런 작업도 하고 있지 않은 경우
            if (get_queue_size() == 0) {
                // 큐에도 프로세스가 없을 경우
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): CPU 노는 중\n", current_time);

                if (is_done(current_time)) {
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "HRN Scheduling Simulation Exit\n\n");
                    break;
                }
            } else {
                // 큐에 프로세스가 있을 경우
                running_process = poll();
                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu dispatch process\n", current_time, running_process->pid);
            }
            
        } else {
            // CPU가 어떠한 프로세스를 처리하고 있는 경우
            buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu process running\n", current_time, running_process->pid);
            if (--running_process->remaining_time == 0) {
                // remaining burst time 을 1 줄이고, 만약에 이미 0 일 경우 running_process에서 제외
                running_process->exit_time = current_time;    // 작업이 끝난 시간을 초기화

                buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu process finish\n", current_time, running_process->pid);
                if (get_queue_size() != 0) {
                    // 아직 큐에 남아있는 프로세스가 있는 경우
                    Process** remaining_process_array = queue_as_array();   // 큐에 남아있는 프로세스들을 배열의 형태로 변환한다.
                    long double hrr = -1;   // highest response ratio를 계산하기 위한 임시변수
                    size_t next_process_id = 0;     // response ration가 가장 높은 프로세스의 pid를 담기 위한 임시변수
                    for (size_t i = 0; i < get_queue_size(); i++)
                    {
                        /*
                         * Response Ratio = (Waiting time + Burst time) / Burst time
                         * Waiting time = Current time - Arrival time
                         * 따라서
                         * Response Ratio = (Current time - Arrival time + Burst time) / Burst time
                         */
                        long double temp_hrr = (current_time - remaining_process_array[i]->arrival_time + remaining_process_array[i]->burst_time) \
                                                                               /(long double)remaining_process_array[i]->burst_time;
                        if (temp_hrr > hrr) {
                            // 높은 Response ration가 나올 때 마다 hrr와 pid를 갱신
                            hrr = temp_hrr;
                            next_process_id = remaining_process_array[i]->pid;
                        }
                    }
                    free(remaining_process_array);  // 동적할당 된 메모리 반납
                    running_process = dispatch_process_by_pid(next_process_id); // Response Ratio가 가장 높은 프로세스를 ready queue 꺼내온다.
                    buffer_cur += sprintf(debug_buffer+buffer_cur, "%2zu(ms): p%zu dispatch process\n", current_time, running_process->pid);
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
        buffer_cur += sprintf(debug_buffer+buffer_cur, "p%-2zu  %2zu(ms)\n", process_vec[i].pid,  \
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
