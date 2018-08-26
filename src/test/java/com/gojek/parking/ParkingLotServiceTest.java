package com.gojek.parking;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.gojek.parking.domain.Command;
import com.gojek.parking.service.CommandParserService;
import com.gojek.parking.service.ParkingLotService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for parking lot service class
 * @author arisculala
 *
 */
public class ParkingLotServiceTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ParkingLotServiceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ParkingLotServiceTest.class );
    }

    /**
     * - For every method test we will be getting the response from the System.out.println() method
     * - NOTE: If we do not want to test the output from the System.out.println() method,
     *      we can change the parking lot service method to return String instead
     */
    public void testParkingLotServiceMethod() {
        ParkingLotService.reInitializedParkingSlots();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String userInput = "create_parking_lot 6";
        Command command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.createParkingLot(command.getOptions());
        assertEquals("Created a parking lot with 6 slots", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "park KA-01-HH-1234 White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.parkCar(command.getOptions());
        assertEquals("Allocated slot number: 1", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

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
        assertEquals(statusOutput.toString(), outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "registration_numbers_for_cars_with_colour White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getRegistrationNumbersForCarsWithColour(command.getOptions());
        assertEquals("KA-01-HH-1234", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "slot_numbers_for_cars_with_colour White";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getSlotNumbersForCarsWithColour(command.getOptions());
        assertEquals("1", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        userInput = "slot_number_for_registration_number KA-01-HH-1234";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.getSlotNumberForRegistrationNumber(command.getOptions());
        assertEquals("1", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent

        userInput = "leave 1";
        command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.leaveCarpark(command.getOptions());
        assertEquals(1, ParkingLotService.getNearestAvailableParkingSlotNo());
    }
}
