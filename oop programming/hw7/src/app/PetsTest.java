package app;

public class PetsTest {
    public static void main(String[] args) {
        Pet petArray[] = new Pet[3];
        petArray[0] = new Pet("동근이", "장근");
        petArray[1] = new RobotPet("동현이", "우현");
        petArray[2] = new SkinnableRobotPet("상범이", "효범", Skinnable.GREEN);

        for (int i = 0; i < 3; i++) {
            for (Pet pet : petArray) {
                pet.introduce();
                if(pet instanceof RobotPet) {
                    RobotPet temp = (RobotPet) pet;
                    temp.work((int) (Math.random() * 3) + 1);   // 1 ~ 3의 값 랜덤
                }
                if(pet instanceof SkinnableRobotPet) {
                    SkinnableRobotPet temp = (SkinnableRobotPet) pet;
                    temp.printSkin();
                    temp.changeSkin((int) (Math.random() * 5)); // 스킨 색 무작위 변경
                }
            }
            System.out.println();   
        }
    }
}