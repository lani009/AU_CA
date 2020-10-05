#include <unistd.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>

void error_handling(char* message); 


int main(void) {
    
    int serv_sock;  // 서버 역할을 하는 소켓
    int clnt_sock;  // 클라이언트랑 1:1로 연결되어서 통신할 수 있는 소켓
    pid_t pid;
    struct sockaddr_in serv_addr;
    struct sockaddr_in clnt_addr;

    socklen_t clnt_addr_size;
    
    serv_sock = socket(PF_INET, SOCK_STREAM, 0); 
    if (serv_sock == -1) {
        error_handling("socket() error");
    }

    memset(&serv_addr, 0, sizeof(serv_addr));   // serv_addr <- 0으로 채워라
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(7250);

    if (bind(serv_sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) == -1) {
        error_handling("bind() error");
    }

    if (listen(serv_sock, 10) == -1) {
        error_handling("listen() error");
    }

    printf("wait");
        sleep(10);
        printf(".");
        sleep(10);
        printf(".");
        sleep(10);
        printf(".");

    clnt_addr_size = sizeof(clnt_addr);
    

    char buffer[100] = { NULL };
    while (1) {
         clnt_sock = accept(serv_sock, (struct sockaddr*) &clnt_addr, &clnt_addr_size);

    if (clnt_sock == -1) {
        error_handling("accept() error");
    } else {
        printf("Socket Connecting Success!\n");
    }


        pid = fork();

        if(pid!=0){
            continue;
        }

        read(clnt_sock, buffer, sizeof(buffer));
        printf("Message from client: %s\n", buffer);
        write(clnt_sock, "good", sizeof("good"));
        if (strcmp(buffer, "q") == 0) {
            break;
        }
    }

    close(clnt_sock);
    close(serv_sock);
    return 0;
}

void error_handling(char* message) {
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}