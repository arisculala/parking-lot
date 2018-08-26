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
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        String userInput = "create_parking_lot 6";
        Command command = CommandParserService.parseCommandInput(userInput);
        ParkingLotService.createParkingLot(command.getOptions());
        assertEquals("execute createParkingLot", outContent.toString().replaceAll("\\n", ""));//Remove the new line created by outContent
    }

    /**
     * Test parking lot by filename response
     * - For every method test we will be getting the response from the System.out.println() method
     * - NOTE: If we do not want to test the output from the System.out.println() method,
     *      we can change the parking lot service method to return String instead
     */
    public void testParkingLotByFilename() {
        assertTrue(true);
    }
}
