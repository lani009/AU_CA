/*
* 2019.04.30 �ۼ�
* 201921166 ����ö
* ��������������а�
*/
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>

int main(void) {
	int num;	//������ ����
	double sum = 0;	//��
	double average;	//���
	double v = 0;	//�л�
	int value[5] = { NULL };

	while (true) {
		printf("������ ����(2~5��): ");
		scanf("%d", &num);	//num scaning
		if (num > 5 || num < 2) {	// (2 < num < 5) -> false
			printf("������ �´� ���� �Է��ϼ���.\n\n");
			continue;
		}
		else {
			for (int i = 0; i < num; i++) {
				printf("����%d�� ��: ", i + 1);
				scanf("%d", &value[i]);
				sum += value[i];
			}
			break;
		}
	}
	average = sum / num;
	for (int i = 0; i < num; i++) {
		v += pow(average - value[i], 2);	//������ ����
	}
	v /= num;

	printf("[���]\n");
	printf("��: %lf\n", sum);
	printf("���: %lf\n", average);
	printf("�л�: %lf\n", v);
	printf("ǥ������: %lf", sqrt(v));


}