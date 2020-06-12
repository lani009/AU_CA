package lani;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CarData implements Serializable {
    private int carID;
    private String parkingLotID;
    private int charge;
    private boolean isMember;
    private boolean isPaid;

    public CarData(int carID, String parkingLotID, int charge, boolean isMember, boolean isPaid) {
        this.carID = carID;
        this.parkingLotID = parkingLotID;
        this.charge = charge;
        this.isMember = isMember;
        this.isPaid = isPaid;
    }

    public int getCarID() {
        return this.carID;
    }

    public String getParkingLotID() {
        return this.parkingLotID;
    }

    public int getCharge() {
        return this.charge;
    }

    public boolean getMember() {
        return this.isMember;
    }

    public boolean getPaid() {
        return this.isPaid;
    }

}