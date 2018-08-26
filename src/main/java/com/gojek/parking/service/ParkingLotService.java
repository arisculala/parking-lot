package com.gojek.parking.service;

import java.util.Map;

import com.gojek.parking.domain.ParkingSlot;

/**
 * Class that will handle all available parking lot service method
 * @author arisculala
 *
 */
public class ParkingLotService {

    // Map that will store the created parking slots object
    private static Map<Integer, ParkingSlot> parkingSlots;

    /**
     * Main method for parking lot service to validate and process user input
     * @param userInput
     */
    public static void executeUserCommandInput(String userInput) {
        
    }

    /**
     * Initialize parking slots map in context
     */
    private void createParkingLot(Map<String, Object> options) {
        
    }

    /**
     * Update parkingSlots map with the nearest ParkingSlot available
     * @param options
     */
    private void parkCar(Map<String, Object> options) {
        
    }

    /**
     * Get the nearest parking slot
     * @return
     */
    private int getNearestAvailableParkingSlotNo() {
        return -1;
    }

    /**
     * Update the parking slot as available alloted to the car leaving
     * @param options
     */
    private void leaveCarpark(Map<String, Object> options) {
        
    }

    /**
     * Display status of parking lot
     */
    private void getParkingLotStatus() {
        
    }

    /**
     * Get list of registration numbers for cars with matching colour on the options parameter colour
     * @param options
     */
    private void getRegistrationNumbersForCarsWithColour(Map<String, Object> options) {
        
    }

    /**
     * Get list of slot Numbers For Cars with matching colour on the options parameter colour
     * @param options
     */
    private void getSlotNumbersForCarsWithColour(Map<String, Object> options) {
        
    }

    /**
     * Slot number for a given car registration number
     * @param options
     */
    private void getSlotNumberForRegistrationNumber(Map<String, Object> options) {
        
    }
}
