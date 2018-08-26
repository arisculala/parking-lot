package com.gojek.parking.domain;

/**
 * Model class for parking slot object
 * 
 * @author arisculala
 *
 */
public class ParkingSlot {
    private int slotNo;
    private boolean available;
    private Car car;

    public ParkingSlot() {
    }

    public ParkingSlot(int slotNo, boolean available, Car car) {
        super();
        this.slotNo = slotNo;
        this.available = available;
        this.car = car;
    }

    /**
     * @return the slotNo
     */
    public int getSlotNo() {
        return slotNo;
    }
    /**
     * @param slotNo the slotNo to set
     */
    public void setSlotNo(int slotNo) {
        this.slotNo = slotNo;
    }
    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }
    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }
    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }
    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
