package app.lani;

import java.util.ArrayList;

public class Student {
    private String id; //학번
    private String pw;  //비밀번호
    private ArrayList<Book> borrowList;  //빌려간 책

    public Student(String id, String pw) {
        this.id = id;
        this.pw = pw;
        borrowList = new ArrayList<Book>();
    }

    public boolean equals(String id) {
        if(this.id.equals(id)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean borrowBook(Book book) {
        if(borrowList.size() < 2 && book.getStatus()) {
            borrowList.add(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean returnBook(Book book) {
        return borrowList.remove(book);
    }

    public boolean checkID(String id) {
        return this.id.equals(id);
    }

    public boolean checkPW(String pw) {
        return this.pw.equals(pw);
    }

    //Getter

    public String getId() {
        return id;
    }

    /**
     * 빌린 책이 있는지 없는지 확인
     * @return true 빌린 책이 있을 때, false 빌린 책이 없을 때
     */
    public boolean hasBook() {
        return !borrowList.isEmpty();
    }
}