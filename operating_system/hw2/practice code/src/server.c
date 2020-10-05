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

void error_hendling(char *message);

int main() {
    int serv_sock;  // 서버 역할을 하는 소켓
    int clnt_sock;  // 클라이언트랑 1:1로 연결되어서 통신할 수 있는 소켓

    struct sockaddr_in serv_addr;
    struct sockaddr_in clnt_addr;

    socklen_t clnt_addr_size;
    // 192.168.0.1 <- IPv4
    // TCP
    serv_sock = socket(PF_INET, SOCK_STREAM, 0);    // 1
    if (serv_sock == -1) {
        error_hendling("socket() error");
    }

    memset(&serv_addr, 0, sizeof(serv_addr));   // serv_addr <- 0으로 채워라
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
    serv_addr.sin_port = htons(5555);

    if (bind(serv_sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) == -1) {
        error_hendling("bind() error");
    }

    if (listen(serv_sock, 5) == -1) {
        error_hendling("listen() error");
    }

    printf("Waiting for client connection...\n");

    clnt_addr_size = sizeof(clnt_addr);
    clnt_sock = accept(serv_sock, (struct sockaddr*) &clnt_addr, &clnt_addr_size);

    if (clnt_sock == -1) {
        error_hendling("accept() error");
    } else {
        printf("Client Connection request accepted\n");
    }

    char buffer[100] = { NULL };
    while (1) {
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

void error_hendling(char *message) {
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}