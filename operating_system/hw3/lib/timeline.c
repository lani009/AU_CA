#include "timeline.h"
#include "process.h"

Process* process_time_vector[TIMEMAX] = { 0 };
size_t final_arrival_time = 0;
void init_timeline(Process* process_array, size_t length) {
    for (size_t i = 0; i < length; i++)
    {
        process_time_vector[process_array[i].arrival_time] = &process_array[i];  // map과 유사한 형태로 사용할 수 있게끔 arrival_time을 기준으로 인덱싱하여 process 구조체를 저장
    }
        final_arrival_time = process_array[length-1].arrival_time;
}

Process* get_process_in_time(size_t time) {
    return process_time_vector[time];
}

int is_done(size_t time) {
    if (time > final_arrival_time) {
        return 1;
    } else {
        return 0;
    }
}