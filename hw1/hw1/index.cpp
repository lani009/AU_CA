#define _CRT_SECURE_NO_WARNINGS
#define MAX_S 3	///<�ִ� ������ �� �ִ� �ο� ��
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
* @brief �л����� �̸�, ������ ����
*/
typedef struct students{
	char name[50];
	int sKor;
	int sEng;
} Students;

Students* sInfo[MAX_S] = { NULL };	///<�����͸� ���� �����͸� ���� ������ �� �ֵ���, �����͹迭�� ����

/**
 * @brief ��ǻ�����α׷��� �� �ǽ� ����(�������� ���α׷�)
 * @author ����ö(lani)
 * @date 2019.06.04
 * @version 1.0
 */
int main(void)
{
	while (true) {
		printf("********************************\n");
		printf("[ ���� ���� ���α׷� v1.0 ]\n");
		printf("********************************\n");
		printf("1. ��ü �л� ���� ����\n");
		printf("2. ���ο� �л� ���\n");
		printf("3. ���� ���� ��ȸ\n");
		printf("4. �л� ���� ����\n");
		printf("5. ���α׷� ����\n");
		printf("����� �Է� : ");
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
			printf("���� ���� ���α׷��� �����մϴ�.");
			for (int i = 0; i < MAX_S; i++) {
				free(sInfo[i]);
			}
			return 0;

		default:
			printf("\"�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���\"\n\n");
			break;	//while������ switch�� ��������. -> while�ݺ�
		}
	}


	return 0;
}

/**
* @brief ��ü �л����� ������ �����ش�.
* @details ��ü �л����� ������ �����ش�.
* ���� ����� �л��� �����Ͱ� ������, �Էµ� �л� ������ ���ٰ� ���
* ���� �ִٸ�, ����Ѵ�.
*/
void viewScore(void) {
	//���߿� ���� ���� ���� -> �ڵ� ���ܵ�
	//while (true) {
	//	printf("[ 1. ��ü �л� ���� ���� ]\n");
	//	printf("1. �̸� ������ ����\n");
	//	printf("2. ��� ���� ������ ����\n");
	//	printf("3. ����1 ���� ������ ����\n");
	//	printf("4. ����2 ���� ������ ����\n");
	//	printf("5. ���� �޴��� �̵�\n");
	//	printf("����� �Է�: ");
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
	//		printf("\"�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���\"\n");
	//		break;
	//	}
	//}
	if (sInfo[0] == NULL) {
		printf("�Էµ� �л� ������ �����ϴ�.\n\n");
		return;
	}
	int sNum = getSNum();	///<�� ���� �л��� ����Ǿ� �ִ��� Ȯ��
	if (sNum == -1)
		sNum = MAX_S;
	printf("[1. ��ü �л� ���� ����]\n");
	printf("[�̸�] [����] [����] [����] [���]\n");
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
* @brief ���ο� �л��� ����Ѵ�.
* @details ���� �л��� MAX_S��ŭ ����Ǿ� �ִٸ�, ����
*/
void addStudent(void) {
	int sNum = getSNum();
	if (sNum == -1) {
		printf("�޸𸮰� ���� ���� �Է��� �Ұ��� �մϴ�.\n\n");
		return;
	}
	sInfo[sNum] = (Students*)malloc(sizeof(Students));
	printf("[2. ���ο� �л� ���]\n");
	///�л����� ���� ����� ����, �̸� ���� �����͸� �Է¹޴´�.
	printf("�̸�:");
	scanf("%s", &sInfo[sNum]->name);
	printf("���� ����:");
	scanf("%d", &sInfo[sNum]->sKor);
	printf("���� ����:");
	scanf("%d", &sInfo[sNum]->sEng);
	printf("\n");
}

/**
* @brief �л� ������ ������ ��ȸ
* @details �л��� �̸��� �޾Ƽ� �׿� �´� �����͸� ����Ѵ�.
* ���� �Է¹��� �̸��� ���� �л��� �������� ���
* ���� �̸��� �л� �����͸� �� ����Ѵ�.
*/
void viewIndi(void) {
	if (sInfo[0] == NULL) {
		printf("�Էµ� �л� ������ �����ϴ�.\n\n");
		return;
	}
	printf("[3. ���� ���� ��ȸ]\n");
	printf("ã�� �л� �̸�:");
	char name[50];
	scanf("%s", name);
	
	int sNum = getSNum();
	if (sNum == -1)
		sNum = MAX_S;

	int index[MAX_S];	///<���� �̸��� ���� �л��� �������� ��츦 ����� ���� �̸��� ���� �л��� �ε������� ����.
	int num = 0;	///<name�� ��ġ�ϴ� �л��� ��
	for (int i = 0; i < sNum; i++) {
		if (!strcmp(sInfo[i]->name, name)) {
			index[num] = i;
			num++;
		}
	}
	if (num == 0) {
		printf("ã�� �л��� �������� �ʽ��ϴ�.\n");
	}
	else {
		printf("[�̸�] [����] [����] [����] [���]\n");
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
* @brief �Է��� �̸��� ���� �л��� ����
* @details ���� ���� �̸��� ���� �л��� �������� ���, ���� ���� ���� �л��� ����.
* ������ ������ sInfo�迭�� ��ҵ��� ������ ����.
*/
void delStudent(void) {
	if (sInfo[0] == NULL) {
		printf("�Էµ� �л� ������ �����ϴ�.\n\n");
		return;
	}
	printf("[4. �л� ���� ����]\n");
	printf("������ �л� �̸�:");
	char name[50];
	scanf("%s", name);
	int sNum = getSNum();

	if (sNum == -1)
		sNum = MAX_S;

	for (int i = 0; i < sNum; i++) {
		if (!strcmp(sInfo[i]->name, name)) {
			free(sInfo[i]);	///< �л��� ������ ��, �����Ҵ��� �޸𸮸� ����
			sInfo[i] = NULL;
			///�л����� ������ ����.
			if (!(i == MAX_S - 1)) {
				for (int j = i; j < sNum - 1; j++) {	//���� �� ������
					sInfo[j] = sInfo[j + 1];
				}
				sInfo[sNum - 1] = NULL;
			}
			printf("%s�� �����Ǿ����ϴ�.\n\n", name);
			return;
		}
	}
	printf("������ �л��� �������� �ʽ��ϴ�.\n\n");
}

/**
* @brief �޴��� ���� ������� �Է��� �ޱ� ���� �Լ�.
* @details ������� �Է��� ���� �Ŀ� �����Ѵ�.
* @return ������� �Է� int��
* @warning ����ڰ� ���� �̿��� ���� �Է��� ��� �޴� ȭ���� ���ѹݺ� �ȴ�.
*/
int getSelection(void) {
	int a;
	return scanf("%d", &a), a;
}

/**
* @brief sInfo�迭�� ����� ��� ������ Ȯ���Ѵ�.
* @details ���� sInfo�迭�� ����� ����� ������ MAX_S�� ������ ���ٸ� -1�� �����Ѵ�.
* @return sInfo�� ����� ����� ����. ���� MAX_S�� ���ٸ� -1
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