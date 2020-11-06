#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Process
{
    int burstTime;
    int arrivalTime;
    int exitTime;
    int pid;
    int timer;
    int serviceTime;

} Process;

Process *readyQueue[100] = {0};
Process *finishQueue[100] = {0};
Process *pc[100] = {0}; // burst 두개의 값을 담는 배열
int queueFront = 0;
int queueRear = 0;

void pushToReadyQueue(Process *process)
{
    readyQueue[queueRear++] = process;
}

Process *popFromReadyQueue()
{

    return readyQueue[queueFront++];
}

int isReadyQueueEmpty()
{
    return queueFront == queueRear ? 1 : 0;
}

void readyQueueInit()
{
    int queueFront = 0;
    int queueRear = 0;
}

int main()
{
    int count = 0; //input에 주어진 프로세스의 개수

    FILE *f;

    f = fopen("input.txt", "r"); // 파일 읽기

    int arrivalTime;
    int burstTime;
    
    while (fscanf(f, "%d %d", &burstTime, &arrivalTime) != -1)
    {
        pc[count] = malloc(sizeof(Process));
        readyQueue[count] = malloc(sizeof(Process));
        finishQueue[count] = malloc(sizeof(Process));
        pc[count]->burstTime = burstTime;
        pc[count]->pid = count;
        pc[count]->timer = 0;
        pc[count]->exitTime = 0;
        pc[count]->serviceTime = 0;
        pc[count++]->arrivalTime = arrivalTime;
    }

    fclose(f); //파일입출력 종료

    // hrn 우선 순위 = (대기시간 + 서비스 시간)/서비스 시간

    //PC[][] 이 대기큐의 역할을 한다.
    //arrivalPC[][] 는 도착한 프로세스를 보관하는 큐의 역할을 한다.
    //eecute PC[][] 는 실행되고 있는 프로세스의 정보를 가지고 있는 배열
    int time = 0;
    int exitTime[count + 1];
    int waitingTime[count + 1];
    int finishPC = 0;

    /* running state에 있는 process */
    Process *executePC = NULL;

    for (int i = 0; i < count; i++)
    {
        exitTime[i] = 0;
    }

    for (int i = 0; i < count; i++)
    {
        waitingTime[i] = 0;
    }

    int j = 0;
    int timer = 0;

    for (time = 0; time < 1000; time++)
    {
        printf("time : %d\n", time);
        if (isReadyQueueEmpty() && finishPC == count) //cpu 종료 조건 : 레디큐에 아무것도 없거나, count ==finishPC 일때
        {
            printf("cpu 동작을 종료합니다.\n");
            break;
        }
        

        if (pc[0] != NULL)
        {
            if (time == pc[0]->arrivalTime)
            {

                //input.txt의 프로세스들의 정보가 arrivalTime이 현재 시간일 경우 레디큐에 push
                pushToReadyQueue(pc[0]);

                for (int i = 0; i < count; i++)
                {
                    if (i == count - 1)
                    {

                        pc[i] = NULL;
                    }
                    else
                    {
                        pc[i] = pc[i + 1];
                    }
                }
            }
        }

      
        if (executePC == NULL) //실행중인 cpu가 없는경우
        {
            if (!isReadyQueueEmpty())
            {
                executePC = popFromReadyQueue();
                printf("%d초 : %d프레세스 할당\n", time, executePC->pid);

                executePC->timer += 1;
                executePC->serviceTime += 1;
            }
            else
            {
                printf("cpu 쉬는중\n");
                continue;
            }
        }
        else //실행중인 cpu가 있는경우
        {
            if (executePC->timer == 4)
            {
                if (executePC->burstTime - executePC->serviceTime == 0)
                {
                    printf("%d초 : %d프레세스 종료\n", time, executePC->pid);
                    executePC->timer = 0;
                    executePC->exitTime=time;
                    finishQueue[finishPC]=executePC;
                    printf("finish큐에 옮겨진 프로세스 : %d\n",finishQueue[finishPC]->pid);
                    executePC = NULL;
                    finishPC++;
                    if (!isReadyQueueEmpty())
                    {                                    //레디큐 있으면 실행
                        executePC = popFromReadyQueue(); //기다리는 프로세스 실행
                        executePC->timer += 1;
                        printf("%d초 : %d프로세스 할당\n", time, executePC->pid);
                        executePC->serviceTime += 1;
                    }
                }
                else
                {
                    printf("%d초 : %d프레세스 중단\n", time, executePC->pid);
                    executePC->timer = 0;
                    pushToReadyQueue(executePC);
                    if (!isReadyQueueEmpty())
                    {                                    //레디큐 있으면 실행
                        executePC = popFromReadyQueue(); //기다리는 프로세스 실행
                        executePC->timer += 1;
                        printf("%d초 : %d프로세스 할당\n", time, executePC->pid);
                        executePC->serviceTime += 1;
                    }
                }
            }
            else
            {

                if (executePC->burstTime - executePC->serviceTime == 0)
                {
                    printf("%d초 : %d프레세스 종료\n", time, executePC->pid);
                    
                    executePC->timer = 0;
                    executePC->exitTime=time;
                    finishQueue[finishPC]=executePC;
                    printf("finish큐에 옮겨진 프로세스 : %d\n",finishQueue[finishPC]->pid);
                    executePC = NULL;
                    finishPC++;

                    if (!isReadyQueueEmpty())
                    {                                    //레디큐 있으면 실행
                        executePC = popFromReadyQueue(); //기다리는 프로세스 실행
                        executePC->timer += 1;
                        printf("%d초 : %d프로세스 할당\n", time, executePC->pid);
                        executePC->serviceTime += 1;
                    }
                }
                else
                {
                    executePC->timer += 1;
                    executePC->serviceTime += 1;
                }
            }
        }
    }

       int sum = 0;

    for (int i = 0; i < count; i++)
    {
        sum = sum + (finishQueue[i]->exitTime - (finishQueue[i]->burstTime) - (finishQueue[i]->arrivalTime));
         printf("%d\n", (finishQueue[i]->exitTime - (finishQueue[i]->burstTime) - (finishQueue[i]->arrivalTime)));
      // printf("%d\n", finishQueue[i]->exitTime);
    }

    printf("\nRR의 평균 대기 시간 : %f\n", ((double)sum / (double)count));

    free(*pc); 
    free(*finishQueue);
    free(*readyQueue); 






}
