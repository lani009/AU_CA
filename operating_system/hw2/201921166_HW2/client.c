/*
 * AJOU UNIVERSITY second semester of 2020 
 * Operating system - second assignment
 * 
 * client.c
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define PORT 5555
void error(char *msg);

int main() {
    int sock = 0;   // 소켓
    struct sockaddr_in serv_addr;   // 서버 주소
    char buffer[1024] = { 0 };  // 버퍼 초기화
    ssize_t recv_len = 0;

    sock = socket(PF_INET, SOCK_STREAM, 0); // 소켓 생성

    if (sock == -1) {
        error("Socket creation error");
    }

    serv_addr.sin_family = AF_INET; // IPv4 프로토콜을 사용
    serv_addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK); // 서버 위치는 루프백 주소로 지정
    serv_addr.sin_port = htons(PORT);   // 서버 포트는 PORT번이 개방됨

    // 서버에 연결 요청
    if (connect(sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) == -1) {
        error("socket connection error");
    }

    read(sock, buffer, sizeof(buffer));
    printf("%s\n", buffer); // 소켓 input 내용 출력
    for (;;) {
        printf("your answer: ");
        scanf("%s", buffer);
        send(sock, buffer, sizeof(buffer), 0);
        
        recv_len = recv(sock, buffer, sizeof(buffer), 0);
        buffer[recv_len] = 0x00;    // 끝문자에 null 지정하여 char* 타입이 string으로 올바르게 읽히게 하기 위함.
        if (atoi(buffer) != 0) {
            // 서버의 출력이 0이 아닐 경우, 다시 설문조사
            printf("%s\n", buffer);
        } else {
            break;
        }
    }
    close(sock);    // 소켓 종료
    return 0;
}

inline void error(char * msg) {
    perror(msg);
    exit(EXIT_FAILURE);
}