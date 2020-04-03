#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
/**
* @brief 사람의 개인정보를 저장
*/
void sort(int n);

typedef struct person {
	char name[50];
	char phone[50];
	int wHour;
	int payPHour;
	int totlaPay;
}Person;

Person** pData;	///<Person* pData[N]형식으로 저장하기 위해 이중포인터 선언

/**
* @brief 급여 관리 프로그램
* @details 사람의 이름과 시급을 저장. input 받은 값을 오름차순으로 정리한다.
*/
int main(void) {
	int n;
	FILE* rOpen;
	FILE* wOpen;
	///File Opening Validation Check
	if (!(rOpen = fopen("input.txt", "r"))) {
		printf("System Error");
		return 0;
	}
	fscanf(rOpen, "%d", &n);
	pData = (Person * *)malloc(sizeof(Person*) * n);
	for (int i = 0; i < n; i++) {
		pData[i] = (Person*)malloc(sizeof(Person));
	}

	for (int i = 0; i < n; i++) {
		fscanf(rOpen, "%s", &pData[i]->name);
		fscanf(rOpen, "%s", &pData[i]->phone);
		fscanf(rOpen, "%d", &pData[i]->wHour);
		fscanf(rOpen, "%d", &pData[i]->payPHour);
		pData[i]->totlaPay = pData[i]->payPHour * pData[i]->wHour;
	}
	sort(n);

	if (!(wOpen = fopen("output.txt", "w"))) {
		printf("System Error");
		return 0;
	}

	fprintf(wOpen, "Name\tPhone\t\tWorking Hour\tpay/hour\ttotal pay\n");
	for (int i = 0; i < n; i++) {
		fprintf(wOpen, "%s\t%s\t%d\t\t%d\t%d\t\n", pData[i]->name, pData[i]->phone, pData[i]->wHour, pData[i]->payPHour, pData[i]->totlaPay);
	}

	fclose(wOpen);
	fclose(rOpen);
	return 0;
}

/**
* @brief 임금 내림차순 정렬을 위해 버블소트
* @details 내림차순 정렬한다
* @param n input의 개수
*/
void sort(int n) {
	Person* temp;
	for (int i = n - 1; i > 0; i--) {
		for (int j = 0; j < i; j++) {
			if (pData[j]->totlaPay < pData[j+1]->totlaPay) {
				temp = pData[j];
				pData[j] = pData[j + 1];
				pData[j + 1] = temp;
			}
		}
	}
}