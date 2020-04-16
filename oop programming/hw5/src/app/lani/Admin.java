package app.lani;

public class Admin {
    private String id;  //관리자 사번
    private String pw; //관리자 비밀번호

    public Admin(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public boolean checkID(String id) {
        return this.id.equals(id);
    }

    public boolean checkPW(String pw) {
        return this.pw.equals(pw);
    }
}