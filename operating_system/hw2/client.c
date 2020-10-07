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
    char buffer[1024] = { 0 };
    ssize_t recv_len = 0;

    sock = socket(PF_INET, SOCK_STREAM, 0); // 소켓 생성

    if (sock == 1) {
        error("Socket creation error");
    }

    serv_addr.sin_family = AF_INET; // IPv4 인터넷 프로토콜을 사용할 것이다.
    serv_addr.sin_addr.s_addr = htonl(INADDR_LOOPBACK); // 서버 주소는 127.0.0.1이다.
    serv_addr.sin_port = htons(PORT);   // 서버 포트는 5555이다.

    if (connect(sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) == -1) {
        error("socket connection error");
    }

    read(sock, buffer, sizeof(buffer));
    printf("%s\n", buffer);
    while (1) {
        printf("your answer: ");
        scanf("%s", buffer);
        send(sock, buffer, sizeof(buffer), 0);
        
        recv_len = recv(sock, buffer, sizeof(buffer), 0);
        buffer[recv_len] = 0x00;
        if (atoi(buffer) != 0) {
            printf("%s\n", buffer);
        } else {
            break;
        }
    }
    close(sock);
    return 0;
}

inline void error(char * msg) {
    perror(msg);
    exit(EXIT_FAILURE);
}