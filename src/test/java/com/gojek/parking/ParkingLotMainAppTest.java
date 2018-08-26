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
    public void testParkingLotInteractive() {
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
    }

    /**
     * Test parking lot by filename response
     * - For every method test we will be getting the response from the System.out.println() method
     * - NOTE: If we do not want to test the output from the System.out.println() method,
     *      we can change the parking lot service method to return String instead
     */
    public void testParkingLotByFilename() {
        ParkingLotService.reInitializedParkingSlots();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        URL fileUrl = getClass().getResource("file_input.txt");
        File file = new File(fileUrl.getFile());

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                ParkingLotService.executeCommand(sCurrentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Created a parking lot with 6 slots");
        expectedOutput.append("Allocated slot number: 1");
        expectedOutput.append("Allocated slot number: 2");
        expectedOutput.append("Allocated slot number: 3");
        expectedOutput.append("Allocated slot number: 4");
        expectedOutput.append("Allocated slot number: 5");
        expectedOutput.append("Allocated slot number: 6");
        expectedOutput.append("Slot number 4 is free");
        expectedOutput.append("Slot No.        Registration No        Colour");
        expectedOutput.append("1        KA-01-HH-1234        White");
        expectedOutput.append("2        KA-01-HH-9999        White");
        expectedOutput.append("3        KA-01-BB-0001        Black");
        expectedOutput.append("5        KA-01-HH-2701        Blue");
        expectedOutput.append("6        KA-01-HH-3141        Black");
        expectedOutput.append("Allocated slot number: 4");
        expectedOutput.append("Sorry, parking lot is full");
        expectedOutput.append("KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333");
        expectedOutput.append("1, 2, 4");
        expectedOutput.append("6");
        expectedOutput.append("Not found");
        assertEquals(expectedOutput.toString(), outContent.toString().replaceAll("\\n", ""));
    }
}
