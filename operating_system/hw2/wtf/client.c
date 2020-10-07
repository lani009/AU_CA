#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
void error_handling(char *message);

char topic[]={"본인이 생각하는 운영체제 과제의 난이도를 선택해주세요\n1.매우 어렵다\n2.어렵다\n3.보통이다.\n4.쉽다\n5.매우 쉽다"};

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
    printf("%s",topic);
        printf("\n번호를 입력해주세요: ");
    while (1) {
        scanf("%s", &buffer);
        write(sock, buffer, sizeof(buffer));
        
        size_t read_len = read(sock, buffer, sizeof(buffer));
        buffer[read_len] = 0x00;
        printf("%s\n", buffer);
        read_len = read(sock, buffer, sizeof(buffer));
        buffer[read_len] = 0x00;
        printf("%s", buffer);

        // read_len = read(sock, buffer, sizeof(buffer));
        // buffer[read_len] = 0x00;
        // printf(" %s\n", buffer);
        break;
    }

    
    close(sock);
    return 0;
}

void error_handling(char * message) {
    fputs(message, stderr);
    fputc('\n', stderr);
    exit(1);
}