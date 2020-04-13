package app;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> account; //계좌들을 담는 Array

    public Bank() {
        account = new ArrayList<>();    //array 초기화
    }

    /**
     * 계좌를 생성하는 메소드
     * 초기입금액이 0원 이하일 시 -1을 리턴한다.
     * @param accountNumber 계좌번호
     * @param owner 계좌주
     * @param initMoney 초기 입금액
     * @return 초기입금액이 0원 이하일 시 -1, 오류 없으면 0
     */
    public int creatAccount(String accountNumber, String owner, int initMoney) {
        if(initMoney <= 0) { return -1; }   //초기 입금액이 0원 이하일 경우
        //계좌를 생성하여 Array에 할당한다
        account.add(new Account()
                        .setAccountNumber(accountNumber)    //계좌번호 setter호출
                        .setBalance(initMoney)  //잔고(=초기입금액) setter호출
                        .setOwner(owner)    //계좌주 setter호출
        );
        return 0;   //정상 리턴코드
    }


    /**
     * 계좌 목록을 출력해서 보여준다.
     */
    public void getAccountList() {
        int totalBalance = 0;   //총 잔고
        for (Account i : account) {
            totalBalance += i.getBalance(); //for 총 잔고 += 각 계좌의 잔고
            System.out.println(i);
        }
        System.out.println(String.format("총 잔고: %d", totalBalance));
    }

    /**
     * 주어진 계좌에 주어진 금액을 입금한다.
     * 만약 계좌가 없을 경우에 -1을 리턴하며,
     * 금액이 0 이하일 경우 1을 리턴한다.
     * @param accountNumber 계좌번호
     * @param money 금액
     * @return returnCode
     */
    public int deposit(String accountNumber, int money) {
        if(money <= 0) { return 1; }    //금액이 0 이하일 경우
        for (Account i : account) {
            if(i.getAccountNumber().equals(accountNumber)) {
                i.depositMoney(money);  //해당 계좌에 money만큼 입금한다.
                return 0;   //정상 리턴 코드
            }
        }
        return -1;  //계좌가 존재하지 않을 경우
    }

    /**
     * 주어진 계좌에 주어진 금액만큼 출금한다.
     * 계좌가 없을 경우 -1을 리턴
     * 출금액이 보유 잔액을 초과할 경우 1을 리턴
     * @param accountNumber
     * @param money
     * @return returnCode
     */
    public int withdraw(String accountNumber, int money) {
        for (Account i : account) {
            if(i.getAccountNumber().equals(accountNumber)) {
                if(money > i.getBalance()) {
                    return 1;   //출금할 금액이 잔고보다 많을 때
                }
                i.withdrawMoney(money);
                return 0;   //정상 리턴 코드
            }
        }
        return -1;  //계좌가 존재하지 않을 때
    }

}