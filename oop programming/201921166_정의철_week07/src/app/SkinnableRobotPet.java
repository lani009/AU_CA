package app;

public class SkinnableRobotPet extends RobotPet implements Skinnable {

    private int skin;

    public SkinnableRobotPet(String name, String masterName, int skin) {
        super(name, masterName);
    }
    
    @Override
    public void changeSkin(int skin) {
        if(skin >= 0 && skin <= 4)
            setSkin(skin);
        else
            System.out.println("스킨 색이 올바르지 않습니다.");
    }

    @Override
    public void printSkin() {
        String skinColor = null;
        switch (getSkin()) {
            case Skinnable.BLACK:
                skinColor = "검정";
                break;

            case Skinnable.BLUE:
                skinColor = "파랑";
                break;

            case Skinnable.GREEN:
                skinColor = "초록";
                break;

            case Skinnable.RED:
                skinColor = "빨강";
                break;

            case Skinnable.YELLOW:
                skinColor = "노랑";
                break;
        }

        System.out.printf("스킨 색은 %s입니다.\n", skinColor);
    }

    /**
     * robot pet의 이름 리턴
     * @return name of robot pet
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * robot pet 주인의 이름 리턴
     * @return name of robot pet's master
     */
    @Override
    public String getMasterName() {
        return super.getMasterName();
    }

    /**
     * robot pet의 피부색 리턴
     * @return skin color of robot pet
     */
    public int getSkin() {
        return skin;
    }

    /**
     * name setter
     */
    @Override
    public void setName(String name) {
        super.setName(name);    //super 메소드 호출
    }

    /**
     * master name setter
     */
    @Override
    public void setMasterName(String masterName) {
        super.setMasterName(masterName);    //super 메소드 호출
    }

    /**
     * skin setter
     * @param skin
     */
    public void setSkin(int skin) {
        this.skin = skin;
    }
}