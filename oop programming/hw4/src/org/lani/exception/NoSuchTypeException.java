package org.lani.exception;

/**
 * 해당하는 직급이 없을 때 발생시키는 Exception
 */
public class NoSuchTypeException extends Exception{
    public NoSuchTypeException(String msg) {
        super(msg);
    }
    public NoSuchTypeException() {
        
    }
}