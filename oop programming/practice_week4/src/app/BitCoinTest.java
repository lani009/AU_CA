package app;

public class BitCoinTest {
    public static void main(String[] args) {
        BitCoin[] myWallet = new BitCoin[4];

        myWallet[0] = new BitCoin("BTC", 9543.66, 10);
        myWallet[1] = new BitCoin("ETH", 3240.17, 20);
        myWallet[2] = new BitCoin("XRP", 910.95, 10);
        myWallet[3] = new BitCoin("LTC", 1184.12, 30);

        for (BitCoin bitCoin : myWallet) {
            System.out.println(bitCoin);
        }
    }
}