package app;

public class VRTester {

    public static void main(String[] args) {
        VR_Player player = new VR_Player();

        player.putOn();
        player.play();
        player.slow();
        player.stop();
        player.putOff();
    }
}