/*
* 2019.04.30 작성
* 201921166 정의철
* 국방디지털융합학과
*/
#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>

int main(void) {
	int num;	//샘플의 개수
	double sum = 0;	//합
	double average;	//평균
	double v = 0;	//분산
	int value[5] = { NULL };

	while (true) {
		printf("샘플의 개수(2~5개): ");
		scanf("%d", &num);	//num scaning
		if (num > 5 || num < 2) {	// (2 < num < 5) -> false
			printf("범위에 맞는 값을 입력하세요.\n\n");
			continue;
		}
		else {
			for (int i = 0; i < num; i++) {
				printf("샘플%d의 값: ", i + 1);
				scanf("%d", &value[i]);
				sum += value[i];
			}
			break;
		}
	}
	average = sum / num;
	for (int i = 0; i < num; i++) {
		v += pow(average - value[i], 2);	//편차의 제곱
	}
	v /= num;

	printf("[결과]\n");
	printf("합: %lf\n", sum);
	printf("평균: %lf\n", average);
	printf("분산: %lf\n", v);
	printf("표준편차: %lf", sqrt(v));


}