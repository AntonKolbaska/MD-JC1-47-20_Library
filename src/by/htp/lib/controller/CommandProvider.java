package by.htp.lib.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.lib.command.Command;
import by.htp.lib.command.exception.CommandException;
import by.htp.lib.command.impl.AddNewBook;
import by.htp.lib.command.impl.ChangeUserStatus;
import by.htp.lib.command.impl.Login;
import by.htp.lib.command.impl.Registration;
import by.htp.lib.command.impl.ShowLibrary;

final class CommandProvider {
	final private Map<String, Command> commands = new HashMap<>();

	CommandProvider() {
		commands.put("LOGIN", new Login());
		commands.put("SIGNUP", new Registration());
		commands.put("ADDBOOK", new AddNewBook());
		commands.put("CHANGESTATUS", new ChangeUserStatus());
		commands.put("SHOWLIB", new ShowLibrary());
	}

	Command getCommand(String commandName) throws CommandException {
		Command command = commands.get(commandName);
		if (command == null) {
			throw new CommandException("Incorrect command name!");
		}
		return command;
	}

}
