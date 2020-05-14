package three;

public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();

        account.deposit(10000);

        try {
            account.withdraw(30000);
        } catch(BalanceException e) {
            e.printStackTrace();
        }
    }
}