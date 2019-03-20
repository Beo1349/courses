package org.courses.commands;

import java.text.ParseException;

public interface Command {
    void parse(String[] args);

    void execute() throws ParseException;
}
