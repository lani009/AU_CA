package org.lani.enumeration;

import org.lani.exception.NoSuchTypeException;

public enum TypeEnum {
    //각각의 직급에 해당하는 int와 String의 형태
    OWNER(1, "owner"),
    MANAGER(2, "manager"),
    DESIGNER(3, "designer"),
    ARCHITECT(4, "architect"),
    DEVELOPER(5, "developer");

    private final int typeInt;  //int타입 직급
    private final String typeString;    //String타입 직급

    private TypeEnum(int typeInt, String typeString) {
        //생성자를 통해 int, String Type의 직급을 초기화 시킨다.
        this.typeInt = typeInt;
        this.typeString = typeString;
    }

    /**
     * int형태로 변환
     * @return type
     */
    public int toInt() {
        return typeInt;
    }

    /**
     * String형태로 변환
     */
    public String toString() {
        return typeString;
    }

    /**
     * String으로 입력된 직급을 Enum으로 변환
     * @param type 직급
     * @return  type 직급
     * @throws NoSuchTypeException
     */
    public static TypeEnum getEnum(String type) throws NoSuchTypeException {
        for (TypeEnum i : values()) {
            if(i.typeString.equals(type.toLowerCase())) {
                return i;   //해당하는 직급이 있을 경우 Enum타입으로 리턴
            }
        }
        throw new NoSuchTypeException();    //만약에 해당하는 직급이 없을 경우 예외 발생
    }

    /**
     * int로 입력된 직급을 Enum으로 변환
     * @param type 직급
     * @return type 직급
     * @throws NoSuchTypeException
     */
    public static TypeEnum getEnum(int type) throws NoSuchTypeException {
        for (TypeEnum i : values()) {
            if(i.typeInt == type) {
                return i;   //해당하는 직급이 있을 경우 Enum타입으로 리턴
            }
        }
        throw new NoSuchTypeException();    //만약에 해당하는 직급이 없을 경우 예외 발생
    }
}