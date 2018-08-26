package com.gojek.parking.domain;

import java.util.Map;

/**
 * Model class for command object
 * 
 * @author arisculala
 *
 */
public class Command {
    private String commandName;
    private Map<String, Object> options;

    public Command() {
    }

    public Command(String commandName, Map<String, Object> options) {
        super();
        this.commandName = commandName;
        this.options = options;
    }

    /**
     * @return the commandName
     */
    public String getCommandName() {
        return commandName;
    }
    /**
     * @param commandName the commandName to set
     */
    public void setCommandName(String command) {
        this.commandName = command;
    }
    /**
     * @return the options
     */
    public Map<String, Object> getOptions() {
        return options;
    }
    /**
     * @param options the options to set
     */
    public void setOptions(Map<String, Object> options) {
        this.options = options;
    }
}
