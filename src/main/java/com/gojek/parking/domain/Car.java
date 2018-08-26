package com.gojek.parking.domain;

/**
 * Model class for car object
 * 
 * @author arisculala
 *
 */
public class Car {
    private String colour = "";
    private String registrationNo = "";

    public Car() {
    }

    public Car(String colour, String registrationNo) {
        super();
        this.colour = colour;
        this.registrationNo = registrationNo;
    }

    /**
     * @return the colour
     */
    public String getColour() {
        return colour;
    }
    /**
     * @param colour the colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
    }
    /**
     * @return the registrationNo
     */
    public String getRegistrationNo() {
        return registrationNo;
    }
    /**
     * @param registrationNo the registrationNo to set
     */
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
}
