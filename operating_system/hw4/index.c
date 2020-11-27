#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <pthread.h>

/* Input File의 위치 */
#define FILE_PATH "./input1.txt"

/* 사용되는 스레드의 수 */
#define THREAD_NUM 3

/* Alphabet 별로 mutex를 담는 배열 */
pthread_mutex_t mutex_arr[26];

/* 알파벳의 카운트 수를 담는 배열 */
size_t alphabet_arr[26];

void *count_runner(void *arg);
void save_alphabet(char c);

/* pthread를 통해 넘겨줄 매개변수 구조체 */
typedef struct _thread_arg
{
    size_t origin; // 해당 스레드가 읽어들일 문자열의 시작점
    size_t end;    // 해당 스레드가 읽어들일 문자열의 끝부분
} thread_arg;

int main(void)
{
    FILE *input_fp = fopen(FILE_PATH, "r");         // input file pointer
    pthread_t pid_arr[THREAD_NUM];                  // pthread를 담는 배열
    size_t file_size = 0;                           // input.txt 의 총 크기
    size_t thread_offset_arr[THREAD_NUM + 1] = {0}; // 각 스레드 별로 처리해야 할 오프셋
    memset(alphabet_arr, 0, sizeof(size_t) * 26);   // alphabet_arr을 0으로 채움

    /* Mutex Initialize */
    for (size_t i = 0; i < 26; i++)
    {
        pthread_mutex_init(&mutex_arr[i], NULL);
    }

    fseek(input_fp, 0, SEEK_END); // input.txt의 끝부분으로 이동
    file_size = ftell(input_fp);  // 끝부분의 커서 위치 -> 파일의 총 크기(character 단위)

    /* THREAD_NUM(스레드의 수) 만큼 텍스트 분할 */
    thread_offset_arr[1] = file_size / THREAD_NUM;
    for (size_t i = 2; i < THREAD_NUM; i++)
    {
        thread_offset_arr[i] = thread_offset_arr[1] * i;
    }
    thread_offset_arr[THREAD_NUM] = file_size;

    /* 텍스트 분할점이 단어의 중간으로 설정되었을 경우, 공백(단어의 끝점)으로 offset을 변경 */
    fseek(input_fp, thread_offset_arr[1], SEEK_SET);
    while (fgetc(input_fp) != 0x20)
    {
        thread_offset_arr[1]++;
    }

    fseek(input_fp, thread_offset_arr[2], SEEK_SET);
    while (fgetc(input_fp) != 0x20)
    {
        thread_offset_arr[2]++;
    }

    /* 스레드 생성 */
    for (size_t i = 0; i < THREAD_NUM; i++)
    {
        thread_arg *temp = malloc(sizeof(thread_arg));
        temp->origin = thread_offset_arr[i];
        temp->end = thread_offset_arr[i + 1];
        pthread_create(&pid_arr[i], NULL, count_runner, (void *)temp);
    }

    /* 스레드 조인 */
    for (size_t i = 0; i < THREAD_NUM; i++)
    {
        pthread_join(pid_arr[i], NULL);
    }

    /* 단어별 카운트 출력 */
    for (size_t i = 0; i < 26; i++)
    {
        printf("%c: %zu\n", 'a' + (char)i, alphabet_arr[i]);
    }

    /* file close */
    fclose(input_fp);

    return 0;
}

/*
 * 알파벳 별로 카운트를 세기 위한 함수
 */
void *count_runner(void *arg)
{
    /* argument를 thread_arg 타입으로 형변환 */
    thread_arg t_arg = *(thread_arg *)arg;  // count_runner function argument

    char temp[50];      // fscanf 문자열 담는 char 배열
    FILE *fp = fopen(FILE_PATH, "r");

    /* file cursor를 t_arg.origin으로 설정 */
    fseek(fp, t_arg.origin, SEEK_SET);

    /* file cursor이 t_arg.end에 도달하기 전까지 알파벳 카운트 */
    while (ftell(fp) < t_arg.end)
    {
        fscanf(fp, "%s", temp);     // 파일에서 문자열 받아오기
        save_alphabet(temp[0]);     // 문자열(단어)의 첫번째 글자 save
    }

    fclose(fp);     // file close
    free(arg);      // thread_arg 메모리 해제
}

/*
 * 알파벳 배열에 alphabet++ 연산을 하기 위한 함수
 */
void save_alphabet(char c)
{
    pthread_mutex_lock(&mutex_arr[c - 'a']);    // 해당 알파벳에 해당하는 mutex를 lock
    alphabet_arr[c - 'a']++;                    // 해당 알파벳 배열에 1 증가 연산
    pthread_mutex_unlock(&mutex_arr[c - 'a']);  // 해당 알파벳에 해당하는 mutex를 unlock
}