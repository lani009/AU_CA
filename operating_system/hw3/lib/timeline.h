/* CPU 상에서 클럭과 시간의 흐름을 표현, 조작하기 위한 함수들 */
#ifndef TIMELINE_H
#define TIMELINE_H

/* 최대 시뮬레이션 가능 시간(ms) */
#define TIMEMAX 100

#include <stdio.h>
#include <sys/types.h>
#include "process.h"
void init_timeline(Process *process_array, size_t length);
Process *get_process_in_time(size_t time);
int is_done(size_t time);
#endif