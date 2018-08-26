package com.gojek.parking;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import com.gojek.parking.service.ParkingLotService;

/**
 * Main controller class for parking lot application
 * @author arisculala
 */
public class ParkingLotMainApp
{
    // Flag for application welcome message
    private static boolean isWelcomeMessageDisplayed = false;

    public static void main( String[] args )
    {
        // Declare a scanner object to read the command line input by user
        Scanner scanner;

        if((args != null) && (args.length > 0)) {
            // Check if input is a valid filename
            String filename = args[0];
            if(filename.contains(".txt")) {
                System.out.println(args[0]);
                // Process file input check contents
                ByteArrayOutputStream outContent = new ByteArrayOutputStream();
                System.setOut(new PrintStream(outContent));

                File file = new File("/parking_lot/functional_spec/fixtures/"+args[0]);
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        ParkingLotService.executeCommand(sCurrentLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid file format!");
            }
        } else {
            //  Note: Need to add delimiter in order for the scanner to read the whole input text
            //  (by default scanner is using space as delimiter)
            scanner = new Scanner(System.in).useDelimiter("\\A");

            // Declare variable that will store the user input
            String userInput;

            //Loop on available command and execute the corresponding user command input
            while(true) {
                if(!isWelcomeMessageDisplayed) {
                    isWelcomeMessageDisplayed = true;
                    // Print parking lot system welcome message for interactive command line
                    System.out.println("* * * * * Welcome to Parking Lot Management System * * * * *");
                }

                // Capture the user input in scanner object and store it in a pre declared variable
                userInput = scanner.nextLine();

                // Call parking lot service class executeCommand to process user input and create the corresponding response
                ParkingLotService.executeCommand(userInput);
            }
        }
    }
}
