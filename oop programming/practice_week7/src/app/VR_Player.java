package app;

public class VR_Player implements ExPlayer, Wearable {

    @Override
    public void play() {
        System.out.println("영상을 재생합니다.");
    }

    @Override
    public void stop() {
        System.out.println("영상을 멈춥니다.");
    }

    @Override
    public void putOn() {
        System.out.println("기기를 착용합니다.");
    }

    @Override
    public void putOff() {
        System.out.println("기기를 벗습니다.");
    }

    @Override
    public void slow() {
        System.out.println("영상을 느리게 재생합니다.");
    }


}