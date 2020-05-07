package app;

public class Pet {
    private String name;
    private String masterName;

    public Pet(String name, String masterName) {
        this.name = name;
        this.masterName = masterName;
    }

    public void introduce() {
        System.out.printf("* 내 이름은 %s 입니다.\n* 주인님의 이름은 %s 입니다.\n", name, masterName);
    }

    /**
     * name getter
     * @return name
     */
	public String getName() {
		return name;
    }
    
    /**
     * master name getter
     * @return masterName
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * name setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * master name setter
     * @param masterName
     */
    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }
}