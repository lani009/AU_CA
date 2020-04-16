package app.lani;

public class Book {
    private String id;
    private boolean status;
    private String title;

    public Book(String id, String title) {
        //변수 초기화
        this.id = id;
        this.title = title;
        status = true;
    }

    /**
     * 같은 책인지 비교하는 메소드
     * @param bookCompare
     * @return true 같은 책일 경우
     */
    public boolean equals(Book bookCompare) {
        if(this.id.equals(bookCompare.id) && this.title.equals(bookCompare.title)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 같은 책인지 비교하는 메소드
     * @param idCompare 비교할 책의 번호
     * @param titleCompare 비교할 책의 제목
     * @return
     */
    public boolean equals(String idCompare, String titleCompare) {
        return equals(new Book(idCompare, titleCompare));
    }

    /**
     * 책의 대출 가능 상태를 리턴한다.
     * @return status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * 책의 대출 가능 상태를 설정한다.
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}