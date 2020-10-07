#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
	pid_t pid;
    
	pid = fork();	// 이 Line을 기점으로, 프로세스가 2개로 분기됨. (부모 프로세스, 자식 프로세스)

    for (int i = 0; i < 100; i++)
    {
        if (pid == 0) {
            printf("I am child process -> %d\n", i);
        } else {
            printf("I am parent process -> %d\n", i);
        }
        sleep(0.5);
    }
    wait(NULL);
    
    return 0;
}
