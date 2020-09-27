package by.htp.lib.command;

import by.htp.lib.command.exception.CommandException;

public interface Command {
	String execute(String request) throws CommandException;
}
