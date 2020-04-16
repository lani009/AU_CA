package app.lani.exception;

/**
 * 반납 단계에서 예외가 발생하였을 때 일으키는 Exception
 */
@SuppressWarnings("serial")
public class ReturnException extends Exception {
    public ReturnException(String msg) {
        super(msg);
    }
}