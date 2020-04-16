package app.lani;

import java.util.ArrayList;

import app.lani.exception.AlreadyBorrowedBook;
import app.lani.exception.BorrowException;

public class Library {
    private ArrayList<Book> bookList;    //도서관에 있는 책들
    private ArrayList<Student> studentList; //등록된 학생 목록
    private ArrayList<Admin> adminList;   //관리자 목록

    public Library() {
        //ArrayList 객체생성
        bookList = new ArrayList<Book>();
        studentList = new ArrayList<Student>();
        adminList = new ArrayList<Admin>();
    }

    /**
     * ID, PW를 입력받고 이에 대한 학생을 추가한다.
     * @param id
     * @param pw
     */
    public void insertStudent(String id, String pw) {
        studentList.add(new Student(id, pw));
    }

    /**
     * ID를 입력받고 이에 해당하는 학생을 삭제한다.
     * @param id
     */
    public boolean deleteStudent(String id) {
        for (Student student : studentList) {
            if(student.getId().equals(id)) {
                if(student.hasBook()) {
                    //대출한 책이 있을 경우
                    return false;
                }
                else {
                    studentList.remove(student);
                    return true;
                }
            }
        }
        return false;   //등록된 학생이 없을 경우 false리턴. -> 사용자의 입력오류는 없다고 가정하므로, 실행될 일이 없다.
                        //따라서 메인 메소드에서는 "대출한 책이 있을 경우"로 상정한다.
    }

    /**
     * 책의 등록번호와 제목을 입력 받고 이에 해당하는 책을 리턴한다.
     * @return book 책
     */
    public Book searchBook(String bookId, String bookTitle) {
        for (Book book : bookList) {
            if(book.equals(bookId, bookTitle)) {
                return book;    //등록번호와 제목이 일치하는 책을 리턴
            }
        }
        return null;
    }

    /**
     * 학생이 책을 빌리는 행위를 의미하는 메소드
     * 책을 2권 이상 대출하려는 경우, 책의 대출이 불가능한 경우에 BorrowException을 일으킨다.
     * @param student   학생
     * @param bookId    책 등록번호
     * @param bookTitle 책 제목
     * @throws BorrowException
     */
    public void borrowBook(Student student, String bookId, String bookTitle) throws BorrowException {
        Book bookToBorrow = searchBook(bookId, bookTitle);
        if(bookToBorrow.getStatus()) {
            if(student.borrowBook(bookToBorrow)) {
                return;
            } else {
                //2권 이상 대출하려는 경우
                throw new BorrowException("2권 이상 대출할 수 없습니다.");
            }
        } else {
            //책의 대출이 불가능 한 경우(다른 사람이 이미 빌려간 경우)
            throw new BorrowException("책의 대출이 불가능합니다.");
        }
        
    }

    public void returnBook(Student student, String BookId, String Book) {
        
    }

    public Admin adminLogin(String id, String pw) {
        for (Admin admin : adminList) {
            if(admin.checkID(id)) {
                if(admin.checkPW(pw)) {
                    return admin;
                }
                else {
                    //TODO
                }
            }
            else {
                //TODO
            }
        }
        return null;
    }

    public Student studentLogin(String id, String pw) {
        for (Student student : studentList) {
            if(student.checkID(id)) {
                if(student.checkPW(pw)) {
                    return student;
                }
                else {
                    //TODO
                }
            }
            else {
                //TODO
            }
        }
        return null;
    }
}