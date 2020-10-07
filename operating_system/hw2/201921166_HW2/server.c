/*
 * AJOU UNIVERSITY second semester of 2020 
 * Operating system - second assignment
 * 
 * server.c
 */
#include <unistd.h>
#include <sys/socket.h>
#include <wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>

#define PORT 5555
#define MAXLISTEN 10

void error(char *msg);

int main() {
    const char QUESTION[] = "운영체제 과제에 대한 난이도 조사를 실시하겠습니다.\n\
다음 문항에 응답해 주시길 바라겠습니다.\n\
1. 어렵다\n\
2. 보통이다\n\
3. 쉽다";   // 난이도 조사 질의

    const char* ANSW[] = { "어렵다", "보통이다", "쉽다" };

    const char ERPY[] = "1에서 3사이로 입력해 주세요."; // 답문 에러 반송문

    int server_socket;  // 서버 소켓
    int client_socket;  // 클라이언트 소켓
    struct sockaddr_in server_address;   // 서버 주소
    struct sockaddr_in client_address;   // 클라이언트 주소
    char buffer[1024] = { 0 };  // 버퍼 초기화
    socklen_t addr_size = sizeof(client_address);
    pid_t pid = 0;
    server_socket = socket(PF_INET, SOCK_STREAM, 0);    // 서버 소켓 생성
    if (server_socket == -1) {
        error("server socket creation failed");
    }

    server_address.sin_family = AF_INET; // IPv4 프로토콜 사용
    server_address.sin_addr.s_addr = INADDR_ANY;  // 모든 사용자로부터 연결 수용
    server_address.sin_port = htons(PORT);   // PORT번 포트 개방

    // Socket에 address, port 바인딩
    if (bind(server_socket, (struct sockaddr*) &server_address, sizeof(server_address)) == -1) {
        error("binding error");
    }

    // 최대 수용 Connection -> MAXLISTEN
    if (listen(server_socket, MAXLISTEN) == -1) {
        error("lieten error");
    }

    for (;;) {
        // client의 connection 요청 수락 후, client socket의 디스크립터 client_socket 변수에 초기화
        client_socket = accept(server_socket, (struct sockaddr*) &client_address, &addr_size);
        if (client_socket == -1) {
            error("accept error");
        }

        pid = fork(); // 분기 개시
        if (pid != 0) {
            continue;   // 부모프로세스는 while문의 상단으로 가서 다시 accept에서 connection이 올 때 까지 block.
        }
        
        write(client_socket, QUESTION, sizeof(buffer));   // 클라이언트에게 설문 전달
        ssize_t client_ans_val;
        
        for (;;) {
            read(client_socket, buffer, sizeof(buffer));
            client_ans_val = atoi(buffer);  // string -> 정수형
            if (client_ans_val >= 1 && client_ans_val <= 3) {
                // 사용자 응답이 1~3 사이일 경우
                write(client_socket, "0", 2);   // 정상 '0' 전송
                break;
            } else {
                write(client_socket, ERPY, sizeof(ERPY));
            }
        }

        char client_address_string[INET_ADDRSTRLEN] = { 0 };
        inet_ntop(AF_INET, &client_address.sin_addr.s_addr, client_address_string, INET_ADDRSTRLEN);    // 클라이언트 ip 취득
        printf("클라이언트(IP: %s)의 응답: %zd, %s\n", client_address_string, client_ans_val, ANSW[client_ans_val -1]);
        break;
    }   // for end

    if (pid != 0) {
        wait(NULL);
        close(server_socket);
    } else {
        close(client_socket);
    }
    return 0;
}

void error(char *msg) {
    perror(msg);
    exit(EXIT_FAILURE);
}