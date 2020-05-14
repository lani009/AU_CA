package three;

public class Account {
    private double balance;

    public void deposit(int money) {
        balance += money;
    }

    public void withdraw(int money) throws BalanceException {
        if(balance < money) {
            throw new BalanceException("잔고부족: " + (money-balance) + "원 부족");
        }

        balance -= money;
    }
}