package com.gojek.parking.service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.gojek.parking.domain.Command;

/**
 * Class that will handle the parsing of the command input from the user
 * @author arisculala
 *
 */
public class CommandParserService {

    /**
     * Check and convert user input to command object using java Pattern and regular expression
     * Parse the user input if it matches to corresponding available parking lot command list
     * @param commandInput
     * @return
     */
    public static Command parseCommandInput(String userInput) {

        // Check create_parking_lot command
        if(Pattern.matches("create_parking_lot \\d+", userInput)) {
            String[] splitUserInput = userInput.split(" ");

            int totalParkingSlots = 0;
            try {
                totalParkingSlots = Integer.parseInt(splitUserInput[1]);
            } catch (Exception e) {}

            if(totalParkingSlots > 0) {
                Map<String, Object> options = new HashMap<String, Object>();
                options.put("totalParkingSlots", totalParkingSlots);
                return new Command(splitUserInput[0], options);
            }
        }

        // Check park command
        if(Pattern.matches("park [a-zA-Z]++-\\d++-[a-zA-Z]++-\\d+ [a-zA-Z]+", userInput)) {
            String[] splitUserInput = userInput.split(" ");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("registrationNo", splitUserInput[1]);
            options.put("colour", splitUserInput[2]);

            return new Command(splitUserInput[0], options);
        }

        // Check leave command
        if(Pattern.matches("leave \\d+", userInput)) {
            String[] splitUserInput = userInput.split(" ");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("slotNo", Integer.parseInt(splitUserInput[1]));
            return new Command(splitUserInput[0], options);
        }

        // Check status command
        if(Pattern.matches("status", userInput)) {
            return new Command(userInput, null);
        }

        // Check registration_numbers_for_cars_with_colour command
        if(Pattern.matches("registration_numbers_for_cars_with_colour [a-zA-Z]+", userInput)) {
            String[] splitUserInput = userInput.split(" ");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("colour", splitUserInput[1]);
            return new Command(splitUserInput[0], options);
        }

        // Check slot_numbers_for_cars_with_colour command
        if(Pattern.matches("slot_numbers_for_cars_with_colour [a-zA-Z]+", userInput)) {
            String[] splitUserInput = userInput.split(" ");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("colour", splitUserInput[1]);
            return new Command(splitUserInput[0], options);
        }

        // Check slot_number_for_registration_number command
        if(Pattern.matches("slot_number_for_registration_number [a-zA-Z]++-\\d++-[a-zA-Z]++-\\d+", userInput)) {
            String[] splitUserInput = userInput.split(" ");
            Map<String, Object> options = new HashMap<String, Object>();
            options.put("registrationNo", splitUserInput[1]);
            return new Command(splitUserInput[0], options);
        }

        // Check parking_lot_help command
        if(Pattern.matches("parking_lot_help", userInput)) {
            return new Command(userInput, null);
        }

        // Check exit command
        if(Pattern.matches("exit", userInput)) {
            return new Command(userInput, null);
        }

        return new Command("notFound", null);
    }
}
