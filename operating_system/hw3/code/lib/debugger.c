#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include "debugger.h"

int32_t buffer_cur = 0;         // 디버깅용 출력 버퍼 커서
char debug_buffer[50000] = {0}; // 디버깅용 출력 버퍼'
int32_t log_split[10] = {0};

void write_log(char *log_msg, ...)
{
    char buffer[500] = {0};
    va_list varg;

    va_start(varg, log_msg);
    vsprintf(buffer, log_msg, varg);
    buffer_cur += sprintf(debug_buffer + buffer_cur, "%s", buffer);
    va_end(varg);
}

void split_log()
{
    static int32_t log_num = 1;
    log_split[log_num++] = ++buffer_cur;
}

char *get_debug_log()
{
    char *buffer = malloc(sizeof(char) * 50000);

    int32_t i = 0;
    do
    {
        strcpy(buffer + log_split[i], debug_buffer + log_split[i]);
        i++;
    } while (log_split[i] != 0);

    return buffer;
}

int32_t get_split_position(int32_t i)
{
    return log_split[i];
}
