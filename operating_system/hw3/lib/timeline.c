#include "timeline.h"
#include "process.h"

Process* process_time_vector[TIMEMAX] = { 0 };

/* 가장 늦게 도착한 프로세스의 arrival_time */
size_t final_arrival_time = 0;

/*
 * process_array로 부터 process_time_vector를 초기화 하여, timeline을 사용할 수 있게끔 한다
 */
void init_timeline(Process* process_array, size_t length) {
    for (size_t i = 0; i < length; i++)
    {
        /* map과 유사한 형태로 사용할 수 있게끔 arrival_time을 기준으로 인덱싱하여 process 구조체를 저장 */
        process_time_vector[process_array[i].arrival_time] = &process_array[i];
    }
        final_arrival_time = process_array[length-1].arrival_time;
}

/*
 * time (ms)에 arrival한 프로세스를 리턴한다
 */
Process* get_process_in_time(size_t time) {
    return process_time_vector[time];
}

/*
 * time (ms)시간 이후에 도착할 프로세스가 있는지 확인
 */
int is_done(size_t time) {
    if (time > final_arrival_time) {
        return 1;
    } else {
        return 0;
    }
}