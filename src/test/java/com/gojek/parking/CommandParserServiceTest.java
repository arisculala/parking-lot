package com.gojek.parking;

import com.gojek.parking.domain.Command;
import com.gojek.parking.service.CommandParserService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Command Parser service class
 * @author arisculala
 *
 */
public class CommandParserServiceTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CommandParserServiceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( CommandParserServiceTest.class );
    }

    /**
     * Test method inside CommandParserService class
     */
    public void testCommandParserServiceMethod() {
        String userInput = "create_parking_lot 6";
        Command command = CommandParserService.parseCommandInput(userInput);
        assertEquals("create_parking_lot", command.getCommandName());
        assertEquals(6, command.getOptions().get("totalParkingSlots"));

        userInput = "park KA-01-P-333 White";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("park", command.getCommandName());
        assertEquals("KA-01-P-333", command.getOptions().get("registrationNo"));
        assertEquals("White", command.getOptions().get("colour"));

        userInput = "leave 10";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("leave", command.getCommandName());
        assertEquals(10, command.getOptions().get("slotNo"));

        userInput = "status";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("status", command.getCommandName());

        userInput = "registration_numbers_for_cars_with_colour White";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("registration_numbers_for_cars_with_colour", command.getCommandName());
        assertEquals("White", command.getOptions().get("colour"));

        userInput = "slot_numbers_for_cars_with_colour White";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("slot_numbers_for_cars_with_colour", command.getCommandName());
        assertEquals("White", command.getOptions().get("colour"));

        userInput = "slot_number_for_registration_number KA-01-P-333";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("slot_number_for_registration_number", command.getCommandName());
        assertEquals("KA-01-P-333", command.getOptions().get("registrationNo"));

        userInput = "parking_lot_help";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("parking_lot_help", command.getCommandName());

        userInput = "exit";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("exit", command.getCommandName());

        userInput = "unknown command park";
        command = CommandParserService.parseCommandInput(userInput);
        assertEquals("notFound", command.getCommandName());
    }
}
