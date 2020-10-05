/*
 * AJOU UNIVERSITY second semester of 2020 
 * Operating system - second assignment
 * 
 * LISTEN
 * IP = 0.0.0.0/32       PORT = 7777
 * 
 * 
 * server.c
 */
#include <unistd.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>

void error_hendling(char *msg);

int main() {
    const char QUESTION[] = "운영체제 과제에 대한 난이도 조사를 실시하겠습니다.\n\
다음 문항에 응답해 주시길 바라겠습니다.\n\
1. 어렵다\n\
2. 보통이다\n\
3. 쉽다";

    int server_socket;  // 서버 소켓
    int client_socket;  // 클라이언트 소켓

    struct sockaddr_in server_address;   // 서버 주소
    struct sockaddr_in clnt_addr;   // 클라이언트 주소

    socklen_t clnt_addr_size;

    server_socket = socket(PF_INET, SOCK_STREAM, 0);
    if (server_socket == -1) {
        error_hendling("socket() error");
    }

    // memset(&server_address, 0, sizeof(server_address));   // server_address 변수를 0으로 채워라.
    server_address.sin_family = AF_INET; // IPv4 프로토콜 사용
    server_address.sin_addr.s_addr = htonl(INADDR_ANY);  // 모든 사용자로부터 연결 수용
    server_address.sin_port = htons(5555);   // 5555번 포트 개방

    if (bind(server_socket, (struct sockaddr*) &server_address, sizeof(server_address)) == -1) {
        error_hendling("bind() error");
    }

    if (listen(server_socket, 5) == -1) {
        error_hendling("listen() error");
    }

    while (1) {
        clnt_addr_size = sizeof(clnt_addr);
        client_socket = accept(server_socket, (struct sockaddr*) &clnt_addr, &clnt_addr_size);
        if (client_socket == -1) {
            error_hendling("accept() error");
        }

        pid_t pid = fork(); // 분기 개시
        if (pid != 0) {
            continue;   // 부모프로세스는 while문의 상단으로 가서 다시 accept에서 connection이 올 때 까지 block.
        }
        char buffer[100];
        
        write(client_socket, QUESTION, strlen(QUESTION)+1);
        int client_ans_val;
        read(client_socket, buffer, sizeof(buffer)-1);
        client_ans_val = atoi(buffer);
        
        printf("클라이언트의 응답: %d\n", client_ans_val);
        break;
    }

    close(client_socket);
    close(server_socket);
    return 0;
}

void error_hendling(char *msg) {
    fputs(msg, stderr);
    fputc('\n', stderr);
    exit(1);
}