#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/list.h>
#include <linux/slab.h>

struct birthday
{
    int month;
    int day;
    int year;
    
    struct list_head list;
};

static LIST_HEAD(birthday_list);

int module_birthday_init(void) {
    struct birthday *person;
    person = kmalloc(sizeof(*person), GFP_KERNEL);
    person->month = 7;
    person->day = 8;
    person->year = 1996;

    INIT_LIST_HEAD(&person->list);
    list_add_tail(&person->list, &birthday_list); //linked list에 추가함
    
    person = kmalloc(sizeof(*person), GFP_KERNEL);
    person->month = 2;
    person->day = 1;
    person->year = 5555;
    list_add_tail(&person->list, &birthday_list); //linked list에 추가함

    person = kmalloc(sizeof(*person), GFP_KERNEL);
    person->month = 2;
    person->day = 1;
    person->year = 2344;
    list_add_tail(&person->list, &birthday_list); //linked list에 추가함

    person = kmalloc(sizeof(*person), GFP_KERNEL);
    person->month = 6;
    person->day = 3;
    person->year = 1234;
    list_add_tail(&person->list, &birthday_list); //linked list에 추가함
                                                    // 4) 연결형 리스트를 순회하여 그 내용을 커널 로그 버퍼로 출력

    struct birthday *ptr = kmalloc(sizeof(*ptr), GFP_KERNEL);
    list_for_each_entry(ptr, &birthday_list, list)
    {
        printk(KERN_INFO "hello %d %d %d\n", ptr->month,
        ptr->day, ptr->year);
    }
    return 0;
}

void module_birthday_exit(void) {
    // 5) 연결형 리스트로부터 요소들을 제거
    struct birthday *ptr, *next;
    list_for_each_entry_safe(ptr, next, &birthday_list, list)
    {
        printk(KERN_INFO "Removing %d %d %d\n", ptr->month,
            ptr->day, ptr->year);
        list_del(&ptr->list);
        kfree(ptr);
    }
}

module_init(module_birthday_init);
module_exit(module_birthday_exit);

MODULE_LICENSE("GPL");      // simple.ko 모듈의 라이선스를 GPL(GNU Public License)로 지정하여 오픈 소스임을 명시한다.
MODULE_DESCRIPTION("Birthday List");      // 모듈의 설명을 Simple Module로 한다.
MODULE_AUTHOR("LANI");       // 모듈 제작자를 SGG로 한다.
