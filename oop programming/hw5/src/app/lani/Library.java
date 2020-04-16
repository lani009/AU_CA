package app.lani;

import java.util.ArrayList;

import app.lani.exception.BorrowException;
import app.lani.exception.ReturnException;

public class Library {
    private ArrayList<Book> bookList;    //도서관에 있는 책들
    private ArrayList<Student> studentList; //등록된 학생 목록
    private ArrayList<Admin> adminList;   //관리자 목록

    public Library(ArrayList<Book> bookList, ArrayList<Admin> adminList) {
        //ArrayList 객체생성
        this.bookList = bookList;
        studentList = new ArrayList<Student>();
        this.adminList = adminList;
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
            if(student.checkID(id)) {
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
    public Book searchBook(String bookID, String bookTitle) {
        for (Book book : bookList) {
            if(book.equals(bookID, bookTitle)) {
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
            //책을 빌리고, 정상적으로 진행되었는지 확인한다.
            if(student.borrowBook(bookToBorrow)) {
                bookToBorrow.setStatus(false);
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

    /**
     * 학생, 책 번호, 책 이름을 입력받아 책을 반납하는 메소드.
     * 잘못된 반납의 경우 ReturnException을 일으킨다.
     * 
     * @param student   학생
     * @param bookId    책 번호
     * @param bookTitle 책 제목
     * @throws ReturnException
     */
    public void returnBook(Student student, String bookId, String bookTitle) throws ReturnException {
        //사용자는 입력 오류를 일으키지 않는다고 가정하므로, 도서 이름이 잘못되었을 경우는 고려하지 않는다.
        Book bookToReturn = searchBook(bookId, bookTitle);
        if(student.returnBook(bookToReturn)) {
            //정상적인 반납
            bookToReturn.setStatus(true);
        } else {
            throw new ReturnException("해당 도서를 대출한 내역이 없습니다.");
        }
    }

    /**
     * 어드민으로 로그인을 시도한다.
     * @param id
     * @param pw
     * @return Admin
     */
    public Admin adminLogin(String id, String pw) {
        for (Admin admin : adminList) {
            if(admin.checkID(id) && admin.checkPW(pw)) {
                return admin;   //일치하는 id pw가 있다면 해당 Admin리턴
            }
        }
        return null;
    }

    /**
     * 학생으로 로그인을 시도한다.
     * @param id
     * @param pw
     * @return Student
     */
    public Student studentLogin(String id, String pw) {
        for (Student student : studentList) {
            if(student.checkID(id) && student.checkPW(pw)) {
                return student; //일치하는 id pw가 있다면 해당 Student리턴
            }
        }
        return null;
    }
}