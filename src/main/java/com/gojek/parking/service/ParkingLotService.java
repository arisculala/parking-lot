package com.gojek.parking.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.gojek.parking.domain.Car;
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
        if(parkingSlots != null && parkingSlots.size() > 0) {
            System.out.println("You already created a parking lot with total of "+parkingSlots.size()+" slots");
        } else {
            int totalParkingSlots = (Integer) options.get("totalParkingSlots");
            parkingSlots = new LinkedHashMap<Integer, ParkingSlot>();//LinkedHashmap to keeps the keys in the order they were inserted
            for(int i = 1; i <= totalParkingSlots; i++) {
                parkingSlots.put(i, new ParkingSlot(i, true, null));
            }
            System.out.println("Created a parking lot with "+totalParkingSlots+" slots");
        }
    }

    /**
     * Check if the create parking lot command is executed already to create Map object of parking slots
     * @return
     */
    private static boolean isParkingLotCreated() {
        if(parkingSlots == null) {
            System.out.println("Sorry, please create parking lot first!");
            return false;
        }
        return true;
    }

    /**
     * Update parkingSlots map with the nearest ParkingSlot available
     * @param options
     */
    public static void parkCar(Map<String, Object> options) {
        if(isParkingLotCreated()) {
            int slotNo = getNearestAvailableParkingSlotNo();
            if(slotNo == -1) {
                System.out.println("Sorry, parking lot is full");
            } else {
                String registrationNo = (String) options.get("registrationNo");
                if(!isCarParkedAlready(registrationNo)) {
                    //Using the same key (slotNo) we will update the value of the Car object using the new car details in parkingSlots Map
                    parkingSlots.put(slotNo, new ParkingSlot(slotNo, false, new Car((String) options.get("colour"), registrationNo)));
                    System.out.println("Allocated slot number: "+slotNo);
                } else {
                    System.out.println("Sorry, a registration number already exists, please double check your registration number before parking.");
                }
            }
        }
    }

    /**
     * Check if car is already parked in the parking lot using registration number
     * @return
     */
    private static boolean isCarParkedAlready(String registrationNo) {
        for (Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
            ParkingSlot parkingSlot = entry.getValue();
            if(!parkingSlot.isAvailable()) {
                if(parkingSlot.getCar().getRegistrationNo().equalsIgnoreCase(registrationNo)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the nearest parking slot
     * @return
     */
    public static int getNearestAvailableParkingSlotNo() {
        int slotNo = -1;
        for (Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
            ParkingSlot parkingSlot = entry.getValue();
            if(parkingSlot.isAvailable()) {
                if(slotNo == -1) {
                    slotNo = entry.getKey();
                }
                if(entry.getKey() < slotNo) {
                    slotNo = entry.getKey();
                }
            }
        }
        return slotNo;
    }

    /**
     * Update the parking slot as available alloted to the car leaving
     * @param options
     */
    public static void leaveCarpark(Map<String, Object> options) {
        if(isParkingLotCreated()) {
            int slotNo = (Integer) options.get("slotNo");
            //Check first if slot number exists
            if(parkingSlots.containsKey(slotNo)) {
                parkingSlots.put(slotNo, new ParkingSlot(slotNo, true, null));
                System.out.println("Slot number "+ slotNo +" is free");
            } else {
                System.out.println("Slot number "+ slotNo +" doesn't exist!");
            }
        }
    }

    /**
     * Display status of parking lot
     */
    public static void getParkingLotStatus() {
        if(isParkingLotCreated()) {
            StringBuilder parkingLotStatus = new StringBuilder();
            parkingLotStatus.append("Slot No.        Registration No        Colour\n");
            for (Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
                ParkingSlot parkingSlot = entry.getValue();
                if(!parkingSlot.isAvailable()) {
                    parkingLotStatus.append(parkingSlot.getSlotNo()+"        "+parkingSlot.getCar().getRegistrationNo()+"        "+parkingSlot.getCar().getColour()+"\n");
                }
            }
            String output = parkingLotStatus.toString();
            if(output.endsWith("\n")) {
                output = output.substring(0, output.lastIndexOf("\n"));
            }
            System.out.println(output);
        }
    }

    /**
     * Get list of registration numbers for cars with matching colour on the options parameter colour
     * @param options
     */
    public static void getRegistrationNumbersForCarsWithColour(Map<String, Object> options) {
        if(isParkingLotCreated()) {
            int cnt = 0;
            StringBuffer registrationNumbersForCarsWithColour = new StringBuffer();
            for (Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
                ParkingSlot parkingSlot = entry.getValue();
                if(!parkingSlot.isAvailable()) {
                    if(parkingSlot.getCar().getColour().equalsIgnoreCase((String) options.get("colour"))) {
                        registrationNumbersForCarsWithColour.append(parkingSlot.getCar().getRegistrationNo() + ", ");
                        cnt++;
                    }
                }
            }
            if(cnt == 0) {
                System.out.println("Not found");
            } else {
                String output = registrationNumbersForCarsWithColour.toString();
                if(output.endsWith(", ")) {
                    output = output.substring(0, output.lastIndexOf(", "));
                }
                System.out.println(output);
            }
        }
    }

    /**
     * Get list of slot Numbers For Cars with matching colour on the options parameter colour
     * @param options
     */
    public static void getSlotNumbersForCarsWithColour(Map<String, Object> options) {
        if(isParkingLotCreated()) {
            int cnt = 0;
            StringBuffer slotNumbersForCarsWithColour = new StringBuffer();
            for (Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
                ParkingSlot parkingSlot = entry.getValue();
                if(!parkingSlot.isAvailable()) {
                    if(parkingSlot.getCar().getColour().equalsIgnoreCase((String) options.get("colour"))) {
                        slotNumbersForCarsWithColour.append(parkingSlot.getSlotNo() + ", ");
                        cnt++;
                    }
                }
            }if(cnt == 0) {
                System.out.println("Not found");
            } else {
                String output = slotNumbersForCarsWithColour.toString();
                if(output.endsWith(", ")) {
                    output = output.substring(0, output.lastIndexOf(", "));
                }
                System.out.println(output);
            }
        }
    }

    /**
     * Slot number for a given car registration number
     * @param options
     */
    public static void getSlotNumberForRegistrationNumber(Map<String, Object> options) {
        if(isParkingLotCreated()) {
            String output = "";
            for (Entry<Integer, ParkingSlot> entry : parkingSlots.entrySet()) {
                ParkingSlot parkingSlot = entry.getValue();
                if(!parkingSlot.isAvailable()) {
                    if(parkingSlot.getCar().getRegistrationNo().equalsIgnoreCase((String) options.get("registrationNo"))) {
                        output = String.valueOf(parkingSlot.getSlotNo());
                    }
                }
            }
            if(output.equals("")) {
                output = "Not found";
            }
            System.out.println(output);
        }
    }

    /**
     * Display list of available command when user execute
     */
    public static void displayCommandListHelp() {
        StringBuffer helpList = new StringBuffer();
        helpList.append("\nThese are common Parking Lot System commands used in various situations:\n");
        helpList.append("   create_parking_lot <total number of parking slots to be created>\n");
        helpList.append("       * (This will initialize/reinitialize the total number of parking slot spaces available)\n");
        helpList.append("   park <registration number> <car colour>\n");
        helpList.append("       * (Assigned the specified car(registration number, colour) to the nearest available slot near the entry point)\n");
        helpList.append("   leave <parking slot number>\n");
        helpList.append("       * (Remove the car details from the specified parking slot number)\n");
        helpList.append("   status\n");
        helpList.append("       * (Display the status of all parking slot)\n");
        helpList.append("   registration_numbers_for_cars_with_colour <car colour>\n");
        helpList.append("       * (Get list of car's registration number matching the parameter car colour)\n");
        helpList.append("   slot_numbers_for_cars_with_colour <car colour>\n");
        helpList.append("       * (Get list of slot numbers matching the parameter car colour)\n");
        helpList.append("   slot_number_for_registration_number <registration number>\n");
        helpList.append("       * (Get the slot number for the given parameter car registration number)\n");
        helpList.append("   parking_lot_help\n");
        helpList.append("       * (Show list of available parking lot command)\n");
        helpList.append("   exit\n");
        helpList.append("       * (Exit the application)\n");
        System.out.println(helpList.toString());
    }

    /**
     * Method to empty map list of parking slots
     * Note: This method is called only for junit testing
     */
    public static void reInitializedParkingSlots() {
        parkingSlots = null;
    }
}
