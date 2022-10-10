#include "process.h"

/*
 * 인자로 주어진 process 변수를 null(remaining_time = 0)로 초기화함
 */
void make_process_null(Process *process)
{
    process->remaining_time = 0;
}

/*
 * 인자로 주어진 process 변수가 null(remaining_time = 0)인지 확인
 */
bool is_null_process(Process *process)
{
    if (process == 0)
    {
        return true;
    }
    if (process->remaining_time == 0)
    {
        return true;
    }
    else
    {
        return false;
    }
}
