package app.lani.exception;

/**
 * 다른 사람이 이미 책을 대출하였기에 대출이 불가능한 경우에 throw 된다.
 */
public class BorrowException extends Exception {
    public BorrowException(String msg) {
        super(msg);
    }

    public BorrowException() {
        this("책의 대출이 불가능합니다.");
    }
}