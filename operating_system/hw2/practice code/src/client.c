#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
void error_handling(char *message);

int main() {
    int sock;   // 소켓
    struct sockaddr_in serv_addr;   // 서버 주소
    char message[100];
    int str_len;

    sock = socket(PF_INET, SOCK_STREAM, 0); // 소켓 생성

    if (sock == 1) {
        error_handling("socket() error");
    }

    memset(&serv_addr, 0, sizeof(serv_addr));   // serv_addr 변수의 내용을 0으로 채워라.
    serv_addr.sin_family = AF_INET; // IPv4 인터넷 프로토콜을 사용할 것이다.
    serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1"); // 서버 주소는 127.0.0.1이다.
    serv_addr.sin_port = htons(5555);   // 서버 포트는 5555이다.

    if (connect(sock, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) == -1) {
        error_handling("connect() error!");
    }

    char buffer[100] = { NULL };
    printf("***PLEASE INPUT letter 'q' to QUIT PROGRAM***\n");
    while (1) {
        printf("input your message: ");
        scanf("%s", &buffer);
        write(sock, buffer, sizeof(buffer));
        
        if (strcmp(buffer, "q") == 0) break;

        read(sock, buffer, sizeof(buffer)-1);
        printf("Message from server : %s \n", buffer);
    }

    
    close(sock);
    return 0;
}

void error_handling(char * message) {
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}