package com.gojek.parking.service;

import com.gojek.parking.domain.Command;

/**
 * Class that will handle the parsing of the command input from the user
 * @author arisculala
 *
 */
public class CommandParserService {

    /**
     * Parse the user input if it matches to corresponding available parking lot command list
     * @param commandInput
     * @return
     */
    public static Command parseCommandInput(String commandInput) {
        Command command = new Command();

        return command;
    }
}
