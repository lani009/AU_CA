#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
/**
* @brief ����� ���������� ����
*/
void sort(int n);

typedef struct person {
	char name[50];
	char phone[50];
	int wHour;
	int payPHour;
	int totlaPay;
}Person;

Person** pData;	///<Person* pData[N]�������� �����ϱ� ���� ���������� ����

/**
* @brief �޿� ���� ���α׷�
* @details ����� �̸��� �ñ��� ����. input ���� ���� ������������ �����Ѵ�.
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
* @brief �ӱ� �������� ������ ���� �����Ʈ
* @details �������� �����Ѵ�
* @param n input�� ����
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