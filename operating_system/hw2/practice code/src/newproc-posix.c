#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main()
{
	pid_t pid;

	pid = fork();	// 이 Line을 기점으로, 프로세스가 2개로 분기됨. (부모 프로세스, 자식 프로세스)
	
	printf("hello my ID is: %d\n", pid);

	if (pid < 0) { /* error occurred */
		fprintf(stderr, "Fork Failed\n");
		return 1;
	}
	else if (pid == 0) { /* child process */
		printf("I am the child %d\n",pid);
	}
	else { /* parent process */
		/* parent will wait for the child to complete */
		printf("I am the parent %d\n",pid);
		wait(NULL);
		
		printf("Child Complete\n");
	}
    
    return 0;
}
