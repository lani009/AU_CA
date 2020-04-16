package app.lani;

import java.util.Scanner;

public class LibraryTest {
    static Scanner key = new Scanner(System.in);
    static Library library = new Library();
    public static void main(String[] args) {

        while(true) {
            System.out.println("---------------");
            System.out.println("1. 학생 로그인");
            System.out.println("2. 관리자 로그인");
            System.out.println("3. 프로그램 종료");
            System.out.println("---------------");
            switch(key.nextInt()) {
                case 1:
                    studentLogin();
                    break;

                case 2:
                    adminLogin();
                    break;

                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }

    public static void studentLogin() {
        System.out.print("ID를 입력하세요 ");
        String id = key.next();

        System.out.print("PW를 입력하세요 ");
        String pw = key.next();

        Student student = library.studentLogin(id, pw);
        if(student == null) {
            System.out.println("존재하지 않는 ID이거나, PW가 잘못되었습니다.");
            return;
        }
        while(true) {
            System.out.println("---------------");
            System.out.println("1. 도서 대출");
            System.out.println("2. 도서 반납");
            System.out.println("3. 로그 아웃");
            System.out.println("---------------");
            String bookID;
            String bookTitle;
            switch (key.nextInt()) {
                case 1:
                    System.out.print("대출하려는 도서의 등록번호를 입력하세요 ");
                    bookID = key.next();
                    System.out.print("대출하려는 도서명을 입력하세요 ");
                    bookTitle = key.next();
                    library.borrowBook(student, bookID, bookTitle);
                    System.out.println("정상적으로 대출되었습니다.");
                    break;
            
                case 2:
                    System.out.print("반납하려는 도서의 등록번호를 입력하세요 ");
                    bookID = key.next();
                    System.out.print("반납하려는 도서명을 입력하세요 ");
                    bookTitle = key.next();
                    library.returnBook(student, bookID, bookTitle);
                    System.out.println("정상적으로 반납되었습니다.");
                    break;

                case 3:
                    System.out.println("정상적으로 로그아웃 되었습니다.");
                    return;
            }
        }
    }

    public static void adminLogin() {
        System.out.print("ID를 입력하세요 ");
        String id = key.next();

        System.out.print("PW를 입력하세요 ");
        String pw = key.next();

        Admin admin = library.adminLogin(id, pw);
        if(admin == null) {
            System.out.println("존재하지 않는 ID이거나, PW가 잘못되었습니다.");
            return;
        }

        while(true) {
            System.out.println("---------------");
            System.out.println("1. 학생 등록");
            System.out.println("2. 학생 삭제");
            System.out.println("3. 로그 아웃");
            System.out.println("---------------");
            String studentID;
            String studentPW;
            switch (key.nextInt()) {
                case 1:
                    System.out.print("등록하려는 학생의 학번을 입력하세요 ");
                    studentID = key.next();
                    System.out.print("등록하려는 학생의 pw를 입력하세요");
                    studentPW = key.next();
    
                    library.insertStudent(studentID, studentPW);    //id와 pw를 전달하여 student를 등록시킨다.
                    break;
    
                case 2:
                    System.out.print("삭제하려는 학생의 학번을 입력하세요 ");
                    studentID = key.next();
                    library.deleteStudent(studentID);
                    System.out.println("정상적으로 삭제하였습니다.");
                    break;
                
                case 3:
                    System.out.println("정상적으로 로그아웃 되었습니다.");
                    return;
            }
        }
    }
}