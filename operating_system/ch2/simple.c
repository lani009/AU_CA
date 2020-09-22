/**
 * simple.c
 *
 * A simple kernel module. 
 * 
 * To compile, run makefile by entering "make"
 *
 * Operating System Concepts - 10th Edition
 * Copyright John Wiley & Sons - 2018
 */

#include <linux/init.h>     // init.h 헤더파일 추가
#include <linux/module.h>   // module.h 헤더파일 추가
#include <linux/kernel.h>   // kernal.h 헤더파일 추가

/* This function is called when the module is loaded. */
int simple_init(void)
{
       printk(KERN_INFO "Loading Module\n");     // 6번 - KERN_INFO 레벨에서 "loading Module" 출력

       return 0;     // 정상 종료
}

/* This function is called when the module is removed. */
void simple_exit(void) {
	printk(KERN_INFO "Removing Module\n");    // 6번 - KERN_INFO 레벨에서 "Removing Module" 출력
}

/* Macros for registering module entry and exit points. */
module_init( simple_init ); // 유저레벨에서 insmod를 통해 simple.ko를 적재할 경우, simple_init 함수가 실행되도록 한다.
module_exit( simple_exit ); // 유저레벨에서 rmmod를 통해 simple 모듈을 해제할 경우, simple_exit 함수가 실행되도록 한다.

MODULE_LICENSE("GPL");      // simple.ko 모듈의 라이선스를 GPL(GNU Public License)로 지정하여 오픈 소스임을 명시한다.
MODULE_DESCRIPTION("Simple Module");      // 모듈의 설명을 Simple Module로 한다.
MODULE_AUTHOR("SGG");       // 모듈 제작자를 SGG로 한다.
