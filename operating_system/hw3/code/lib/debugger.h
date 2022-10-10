/* 디버깅 용 메시지 저장을 위한 헤더 */
#ifndef DEBUGGER_H
#define DEBUGGER_H

#include <sys/types.h>

void write_log(char *log_msg, ...);
void split_log();
char *get_debug_log();
int32_t get_split_position(int32_t i);

#endif
