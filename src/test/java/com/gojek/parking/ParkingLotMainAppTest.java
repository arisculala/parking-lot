package com.gojek.parking;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

import com.gojek.parking.domain.Command;
import com.gojek.parking.service.CommandParserService;
import com.gojek.parking.service.ParkingLotService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for parking lot main application
 * @author arisculala
 *
 */
public class ParkingLotMainAppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ParkingLotMainAppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ParkingLotMainAppTest.class );
    }

    /**
     * Test parking lot interactive response
     * - For every method test we will be getting the response from the System.out.println() method
     * - NOTE: If we do not want to test the output from the System.out.println() method,
     *      we can change the parking lot service method to return String instead
     */
    public void testParkingLot() {
        ParkingLotService.reInitializedParkingSlots();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String userInput = "create_parking_lot 6";
        Command command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.createParkingLot(command.getOptions());
        assertEquals("Created a parking lot with 6 slots", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "create_parking_lot 10";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.createParkingLot(command.getOptions());
        assertEquals("You already created a parking lot with total of 6 slots", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-1234 White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 1", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-1234 White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Sorry, a registration number already exists, please double check your registration number before parking.", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-9999 White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 2", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-BB-0001 Black";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 3", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-7777 Red";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 4", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-2701 Blue";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 5", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-3141 Black";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 6", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "leave 4";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.leaveCarpark(command.getOptions());
        assertEquals("Slot number 4 is free", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "status";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getParkingLotStatus();
        StringBuilder statusOutput = new StringBuilder();
        statusOutput.append("Slot No.        Registration No        Colour");
        statusOutput.append("1        KA-01-HH-1234        White");
        statusOutput.append("2        KA-01-HH-9999        White");
        statusOutput.append("3        KA-01-BB-0001        Black");
        statusOutput.append("5        KA-01-HH-2701        Blue");
        statusOutput.append("6        KA-01-HH-3141        Black");
        assertEquals(statusOutput.toString(), outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-P-333 White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 4", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park DL-12-AA-9999 White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Sorry, parking lot is full", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "registration_numbers_for_cars_with_colour White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getRegistrationNumbersForCarsWithColour(command.getOptions());
        assertEquals("KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "registration_numbers_for_cars_with_colour Purple";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getRegistrationNumbersForCarsWithColour(command.getOptions());
        assertEquals("Not found", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "slot_numbers_for_cars_with_colour White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getSlotNumbersForCarsWithColour(command.getOptions());
        assertEquals("1, 2, 4", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "slot_numbers_for_cars_with_colour Purple";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getSlotNumbersForCarsWithColour(command.getOptions());
        assertEquals("Not found", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "slot_number_for_registration_number KA-01-HH-3141";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getSlotNumberForRegistrationNumber(command.getOptions());
        assertEquals("6", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "slot_number_for_registration_number MH-04-AY-1111";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getSlotNumberForRegistrationNumber(command.getOptions());
        assertEquals("Not found", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        userInput = "leave 2";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.leaveCarpark(command.getOptions());
        assertEquals(2, ParkingLotService.getNearestAvailableParkingSlotNo());

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "leave 10";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.leaveCarpark(command.getOptions());
        assertEquals("Slot number 10 doesn't exist!", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "create";
        ParkingLotService.executeCommand(userInput);
        assertEquals("create:is not a valid command. See 'parking_lot_help'", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        ParkingLotService.displayCommandListHelp();
        StringBuffer helpList = new StringBuffer();
        helpList.append("These are common Parking Lot System commands used in various situations:\n");
        helpList.append("    create_parking_lot <total number of parking slots to be created>\n");
        helpList.append("      * (This will initialize/reinitialize the total number of parking slot spaces available)\n");
        helpList.append("    park <registration number> <car colour>\n");
        helpList.append("      * (Assigned the specified car(registration number, colour) to the nearest available slot near the entry point)\n");
        helpList.append("    leave <parking slot number>\n");
        helpList.append("      * (Remove the car details from the specified parking slot number)\n");
        helpList.append("    status\n");
        helpList.append("      * (Display the status of all parking slot)\n");
        helpList.append("    registration_numbers_for_cars_with_colour <car colour>\n");
        helpList.append("      * (Get list of car's registration number matching the parameter car colour)\n");
        helpList.append("    slot_numbers_for_cars_with_colour <car colour>\n");
        helpList.append("      * (Get list of slot numbers matching the parameter car colour)\n");
        helpList.append("    slot_number_for_registration_number <registration number>\n");
        helpList.append("      * (Get the slot number for the given parameter car registration number)\n");
        helpList.append("    parking_lot_help\n");
        helpList.append("      * (Show list of available parking lot command)\n");
        helpList.append("    exit\n");
        helpList.append("      * (Exit the application)\n");
        helpList.append("\n");
        assertEquals(helpList.toString(), outContent.toString());
    }
}
