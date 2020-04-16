package app.lani;

import java.util.ArrayList;

public class Student {
    private String id; //학번
    private String pw;  //비밀번호
    private ArrayList<Book> borrowList;  //빌려간 책

    public Student(String id, String pw) {
        //변수 초기화
        this.id = id;
        this.pw = pw;
        borrowList = new ArrayList<Book>();
    }

    /**
     * 책을 빌린다.
     * @param book 책
     * @return true 정상적인 대출, false 2권 이상 빌렸을 때
     */
    public boolean borrowBook(Book book) {
        if(borrowList.size() < 2 && book.getStatus()) {
            borrowList.add(book);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 책을 반납하고 결과를 리턴한다.
     * @param book
     * @return true 정상적인 반납, false 해당하는 책이 없음. <- 사용자는 정상적인 입력만 하므로 해당 에러는 일어날 일이 없음.
     */
    public boolean returnBook(Book book) {
        return borrowList.remove(book);
    }

    /**
     * ID가 같은지 확인하는 메소드
     * @param id
     * @return true id가 일치할 경우
     */
    public boolean checkID(String id) {
        return this.id.equals(id);
    }

    /**
     * PW가 같은지 확인하는 메소드
     * @param pw
     * @return true pw가 일치할 경우
     */
    public boolean checkPW(String pw) {
        return this.pw.equals(pw);
    }

    /**
     * 빌린 책이 있는지 없는지 확인
     * @return true 빌린 책이 있을 때, false 빌린 책이 없을 때
     */
    public boolean hasBook() {
        return !borrowList.isEmpty();
    }
}