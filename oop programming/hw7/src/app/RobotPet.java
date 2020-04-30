package app;

public class RobotPet extends Pet {

    public RobotPet(String name, String masterName) {
        super(name, masterName);    //super 생성자로 매개변수 전달
    }

    @Override
    public void introduce() {
        System.out.printf("** 나는 로봇! 이름은 %s 입니다.\n"
                        + "** 주인님의 이름은 %s 입니다.\n", getName(), getMasterName());
    }

    public void work(int num) {
        switch (num) {
            case 1:
                System.out.println("청소를 합니다");
                break;
        
            case 2:
                System.out.println("세탁을 합니다.");
                break;

            case 3:
                System.out.println("불을 끕니다.");
                break;
        }
    }

}