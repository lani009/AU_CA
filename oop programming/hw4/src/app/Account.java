package app;

public class Account {
    private String accountNumber;   //계좌번호
    private String owner;   //계좌주
    private int balance;    //계좌 잔액

    /**
     * 계좌번호를 set
     * @param accountNumber 계좌번호
     */
    public Account setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    /**
     * 계좌주를 set
     * @param owner 계좌주
     */
    public Account setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    /**
     * 잔고를 set
     * @param balance 잔고
     */
    public Account setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    /**
     * @return 계좌번호
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @return 계좌주
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @return 잔고
     */
    public int getBalance() {
        return balance;
    }

    /**
     * 계좌에 money만큼 입금한다
     * @param money 금액
     */
    public void depositMoney(int money) {
        balance += money;
    }

    /**
     * 계좌에 money만큼 출금한다
     * @param money 금액
     */
    public void widthdrawMoney(int money) {
        balance -= money;
    }
    
    @Override
    public String toString() {
    	return String.format("%s %3s     %d", getAccountNumber(), getOwner(), getBalance());
    }
}