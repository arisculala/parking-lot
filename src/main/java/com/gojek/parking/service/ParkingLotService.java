package com.gojek.parking.service;

import java.util.Map;

import com.gojek.parking.domain.Command;
import com.gojek.parking.domain.ParkingSlot;
import com.gojek.parking.utils.Constants;

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
    public static void executeCommand(String userInput) {
        // Convert userInput to command object using CommandParserService class
        Command commandInput = CommandParserService.parseCommandInput(userInput);

        // Check the user command input and display the response
        switch(commandInput.getCommandName()) {
            case Constants.COMMAND_CREATE_PARKING_LOT:
                createParkingLot(commandInput.getOptions());
                break;
            case Constants.COMMAND_PARK:
                parkCar(commandInput.getOptions());
                break;
            case Constants.COMMAND_LEAVE:
                leaveCarpark(commandInput.getOptions());
                break;
            case Constants.COMMAND_STATUS:
                getParkingLotStatus();
                break;
            case Constants.COMMAND_REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR:
                getRegistrationNumbersForCarsWithColour(commandInput.getOptions());
                break;
            case Constants.COMMAND_SLOT_NUMBERS_FOR_CARS_WITH_COLOUR:
                getSlotNumbersForCarsWithColour(commandInput.getOptions());
                break;
            case Constants.COMMAND_SLOT_NUMBER_FOR_REGISTRATION_NUMBER:
                getSlotNumberForRegistrationNumber(commandInput.getOptions());
                break;
            case Constants.COMMAND_PARKING_LOT_HELP://Not part of the spec's given but this may help as additional functionality which help user to check available list of commands
                displayCommandListHelp();
                break;
            case Constants.COMMAND_EXIT:
                System.exit(0);
            default:
                // Inform user in case of invalid command
                System.out.println(userInput + ":" + "is not a valid command. See 'parking_lot_help'");
        }
    }

    /**
     * Initialize parking slots map in context
     */
    public static void createParkingLot(Map<String, Object> options) {
        System.out.println("execute createParkingLot");
    }

    /**
     * Update parkingSlots map with the nearest ParkingSlot available
     * @param options
     */
    public static void parkCar(Map<String, Object> options) {
        System.out.println("execute parkCar");
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
    public static void leaveCarpark(Map<String, Object> options) {
        System.out.println("execute leaveCarpark");
    }

    /**
     * Display status of parking lot
     */
    public static void getParkingLotStatus() {
        System.out.println("execute getParkingLotStatus");
    }

    /**
     * Get list of registration numbers for cars with matching colour on the options parameter colour
     * @param options
     */
    public static void getRegistrationNumbersForCarsWithColour(Map<String, Object> options) {
        System.out.println("execute getRegistrationNumbersForCarsWithColour");
    }

    /**
     * Get list of slot Numbers For Cars with matching colour on the options parameter colour
     * @param options
     */
    public static void getSlotNumbersForCarsWithColour(Map<String, Object> options) {
        System.out.println("execute getSlotNumbersForCarsWithColour");
    }

    /**
     * Slot number for a given car registration number
     * @param options
     */
    public static void getSlotNumberForRegistrationNumber(Map<String, Object> options) {
        System.out.println("execute getSlotNumberForRegistrationNumber");
    }

    /**
     * Display list of available command when user execute
     */
    public static void displayCommandListHelp() {
        System.out.println("execute displayCommandListHelp");
    }
}
