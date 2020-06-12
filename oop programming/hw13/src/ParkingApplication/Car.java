package ParkingApplication;

public class Car {
    private String carNumber;
    private String phoneNumber;
    private int parkingTime;
    private boolean parkingTicket;

    public Car(String carNumber, String phoneNumber, int parkingTime, boolean parkingTicket) {
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
        this.parkingTime = parkingTime;
        this.parkingTicket = parkingTicket;
    }

    public Car(String carNumber, String phoneNumber) {
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
        this.parkingTime = 3;
        this.parkingTicket = false;
    }

    public Car(String carNumber, String phoneNumber, int parkintTime) {
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
        this.parkingTime = parkintTime;
        this.parkingTicket = false;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getParkingTime() {
        return this.parkingTime;
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public boolean isParkingTicket() {
        return this.parkingTicket;
    }

    public boolean getParkingTicket() {
        return this.parkingTicket;
    }

    public void setParkingTicket(boolean parkingTicket) {
        this.parkingTicket = parkingTicket;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t%d\t%s\t%b", this.getCarNumber(), this.getParkingTime(), this.getPhoneNumber(), this.getParkingTicket());
    }

}