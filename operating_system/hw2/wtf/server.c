#include <unistd.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>

#include <string.h>
#include <arpa/inet.h>

void error_handling(char *message);

int people = 0;
int main(void)
{
    int survey[5] = {0, 0, 0, 0, 0};
    int serv_sock; // 서버 역할을 하는 소켓
    int clnt_sock; // 클라이언트랑 1:1로 연결되어서 통신할 수 있는 소켓
    pid_t pid;
    struct sockaddr_in serv_addr;
    struct sockaddr_in clnt_addr;

    socklen_t clnt_addr_size;

    serv_sock = socket(PF_INET, SOCK_STREAM, 0);
    if (serv_sock == -1)
    {
        error_handling("socket() error");
    }

    memset(&serv_addr, 0, sizeof(serv_addr)); // serv_addr <- 0으로 채워라
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(5555);

    if (bind(serv_sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) == -1)
    {
        error_handling("bind() error");
    }

    if (listen(serv_sock, 10) == -1)
    {
        error_handling("listen() error");
    }

    printf("연결을 기다리는 중...\n\n");

    clnt_addr_size = sizeof(clnt_addr);

    char buffer[100] = {NULL};
    while (1)
    {
        clnt_sock = accept(serv_sock, (struct sockaddr *)&clnt_addr, &clnt_addr_size);

        if (clnt_sock == -1)
        {
            error_handling("accept() error");
        }
        else
        {
            printf("소켓 연결 성공!\n");
        }

        people = people + 1;
        pid = fork();

        if (pid == 0)
        {
            read(clnt_sock, buffer, sizeof(buffer));
            printf("클라이언트 메시지: %s\n", buffer);
            sprintf(buffer, "현재 설문 완료 인원:%d\n", people);
            write(clnt_sock, buffer, sizeof(buffer));
            write(clnt_sock, "감사합니다.", sizeof("감사합니다."));
           // memset(buffer, 0x00, sizeof(buffer));
            printf("%d", people);
            break;
        }
        else if (pid != 0)
        {
            continue;
        }
    }

    close(clnt_sock);
    close(serv_sock);
    return 0;
}

void error_handling(char *message)
{
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}