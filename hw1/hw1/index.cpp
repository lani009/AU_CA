#define _CRT_SECURE_NO_WARNINGS
#define MAX_S 3	///<최대 저장할 수 있는 인원 수
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void viewScore(void);
int getSelection(void);
void addStudent(void);
void viewIndi(void);
int getSNum(void);
void delStudent(void);

/**
* @brief 학생들의 이름, 점수를 저장
*/
typedef struct students{
	char name[50];
	int sKor;
	int sEng;
} Students;

Students* sInfo[MAX_S] = { NULL };	///<포인터를 통해 데이터를 쉽게 관리할 수 있도록, 포인터배열을 선언

/**
 * @brief 컴퓨터프로그래밍 및 실습 과제(성적관리 프로그램)
 * @author 정의철(lani)
 * @date 2019.06.04
 * @version 1.0
 */
int main(void)
{
	while (true) {
		printf("********************************\n");
		printf("[ 성적 관리 프로그램 v1.0 ]\n");
		printf("********************************\n");
		printf("1. 전체 학생 성적 보기\n");
		printf("2. 새로운 학생 등록\n");
		printf("3. 개인 성적 조회\n");
		printf("4. 학생 정보 삭제\n");
		printf("5. 프로그램 종료\n");
		printf("사용자 입력 : ");
		switch (getSelection())
		{
		case 1:
			printf("\n");
			viewScore();
			break;

		case 2:
			printf("\n");
			addStudent();
			break;

		case 3:
			printf("\n");
			viewIndi();
			break;

		case 4:
			printf("\n");
			delStudent();
			break;

		case 5:
			printf("성적 관리 프로그램을 종료합니다.");
			for (int i = 0; i < MAX_S; i++) {
				free(sInfo[i]);
			}
			return 0;

		default:
			printf("\"잘못된 입력입니다. 다시 입력해주세요\"\n\n");
			break;	//while문말고 switch문 빠져나감. -> while반복
		}
	}


	return 0;
}

/**
* @brief 전체 학생들의 점수를 보여준다.
* @details 전체 학생들의 점수를 보여준다.
* 만약 저장된 학생의 데이터가 없으면, 입력된 학생 정보가 없다고 출력
* 만약 있다면, 출력한다.
*/
void viewScore(void) {
	//나중에 과제 출제 가능 -> 코드 남겨둠
	//while (true) {
	//	printf("[ 1. 전체 학생 성적 보기 ]\n");
	//	printf("1. 이름 순으로 보기\n");
	//	printf("2. 평균 성적 순으로 보기\n");
	//	printf("3. 과목1 성적 순으로 보기\n");
	//	printf("4. 과목2 성적 순으로 보기\n");
	//	printf("5. 상위 메뉴로 이동\n");
	//	printf("사용자 입력: ");
	//	switch (getSelection())
	//	{
	//	case 1:

	//	case 2:

	//	case 3:

	//	case 4:
	//		printf("\n");
	//		break;

	//	case 5:
	//		printf("\n");
	//		return;

	//	default:
	//		printf("\"잘못된 입력입니다. 다시 입력해주세요\"\n");
	//		break;
	//	}
	//}
	if (sInfo[0] == NULL) {
		printf("입력된 학생 정보가 없습니다.\n\n");
		return;
	}
	int sNum = getSNum();	///<몇 명의 학생이 저장되어 있는지 확인
	if (sNum == -1)
		sNum = MAX_S;
	printf("[1. 전체 학생 성적 보기]\n");
	printf("[이름] [국어] [영어] [총점] [평균]\n");
	int eng;
	int kor;
	for (int i = 0; i < sNum; i++) {
		eng = sInfo[i]->sEng;
		kor = sInfo[i]->sKor;
		printf("%-7s%-7d%-7d%-7d%-7lf\n", sInfo[i]->name, kor, eng, eng+kor, ((double)eng+kor)/2);
	}
	printf("\n");
}

/**
* @brief 새로운 학생을 등록한다.
* @details 만약 학생이 MAX_S만큼 저장되어 있다면, 종료
*/
void addStudent(void) {
	int sNum = getSNum();
	if (sNum == -1) {
		printf("메모리가 가득 차서 입력이 불가능 합니다.\n\n");
		return;
	}
	sInfo[sNum] = (Students*)malloc(sizeof(Students));
	printf("[2. 새로운 학생 등록]\n");
	///학생들의 정보 등록을 위해, 이름 등의 데이터를 입력받는다.
	printf("이름:");
	scanf("%s", &sInfo[sNum]->name);
	printf("국어 점수:");
	scanf("%d", &sInfo[sNum]->sKor);
	printf("영어 점수:");
	scanf("%d", &sInfo[sNum]->sEng);
	printf("\n");
}

/**
* @brief 학생 개인의 성적을 조회
* @details 학생의 이름을 받아서 그에 맞는 데이터를 출력한다.
* 만약 입력받은 이름을 가진 학생이 여러명일 경우
* 같은 이름의 학생 데이터를 다 출력한다.
*/
void viewIndi(void) {
	if (sInfo[0] == NULL) {
		printf("입력된 학생 정보가 없습니다.\n\n");
		return;
	}
	printf("[3. 개인 성적 조회]\n");
	printf("찾을 학생 이름:");
	char name[50];
	scanf("%s", name);
	
	int sNum = getSNum();
	if (sNum == -1)
		sNum = MAX_S;

	int index[MAX_S];	///<같은 이름을 가진 학생이 여러명일 경우를 대비해 같은 이름을 가진 학생의 인덱스들을 저장.
	int num = 0;	///<name과 일치하는 학생의 수
	for (int i = 0; i < sNum; i++) {
		if (!strcmp(sInfo[i]->name, name)) {
			index[num] = i;
			num++;
		}
	}
	if (num == 0) {
		printf("찾는 학생이 존재하지 않습니다.\n");
	}
	else {
		printf("[이름] [국어] [영어] [총점] [평균]\n");
		int eng;
		int kor;
		for (int i = 0; i < num; i++) {
			eng = sInfo[index[i]]->sEng;
			kor = sInfo[index[i]]->sKor;
			printf("%-7s%-7d%-7d%-7d%-7lf\n", sInfo[index[i]]->name, kor, eng, eng + kor, ((double)eng + kor) / 2);
		}
	}
	printf("\n");
}

/**
* @brief 입력한 이름을 가진 학생을 삭제
* @details 만약 같은 이름을 가진 학생이 여러명일 경우, 그중 가장 앞의 학생을 삭제.
* 삭제가 끝나면 sInfo배열의 요소들을 앞으로 당긴다.
*/
void delStudent(void) {
	if (sInfo[0] == NULL) {
		printf("입력된 학생 정보가 없습니다.\n\n");
		return;
	}
	printf("[4. 학생 정보 삭제]\n");
	printf("삭제할 학생 이름:");
	char name[50];
	scanf("%s", name);
	int sNum = getSNum();

	if (sNum == -1)
		sNum = MAX_S;

	for (int i = 0; i < sNum; i++) {
		if (!strcmp(sInfo[i]->name, name)) {
			free(sInfo[i]);	///< 학생을 삭제한 후, 동적할당한 메모리를 해제
			sInfo[i] = NULL;
			///학생들을 앞으로 당긴다.
			if (!(i == MAX_S - 1)) {
				for (int j = i; j < sNum - 1; j++) {	//삭제 후 재정렬
					sInfo[j] = sInfo[j + 1];
				}
				sInfo[sNum - 1] = NULL;
			}
			printf("%s가 삭제되었습니다.\n\n", name);
			return;
		}
	}
	printf("삭제할 학생이 존재하지 않습니다.\n\n");
}

/**
* @brief 메뉴에 대한 사용자의 입력을 받기 위한 함수.
* @details 사용자의 입력을 받은 후에 리턴한다.
* @return 사용자의 입력 int형
* @warning 사용자가 정수 이외의 값을 입력할 경우 메뉴 화면이 무한반복 된다.
*/
int getSelection(void) {
	int a;
	return scanf("%d", &a), a;
}

/**
* @brief sInfo배열에 저장된 요소 개수를 확인한다.
* @details 만약 sInfo배열에 저장된 요소의 개수가 MAX_S의 개수와 같다면 -1을 리턴한다.
* @return sInfo에 저장된 요소의 개수. 만약 MAX_S와 같다면 -1
*/
int getSNum(void) {
	int sNum = -1;	///<MAX test flag = -1
	for (int i = 0; i < MAX_S; i++) {
		if (sInfo[i] == NULL) {
			sNum = i;
			break;
		}
	}
	return sNum;
}