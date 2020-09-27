package by.htp.lib.controller;

import by.htp.lib.command.Command;
import by.htp.lib.command.exception.CommandException;

public class Controller {
	private final CommandProvider provider = new CommandProvider();

	public String doAction(String request) {
		String commandName = request.split("\\s+")[1];
		String response = request.split("\\s+")[0];

		try {
			Command command = provider.getCommand(commandName);
			response = command.execute(request);
		} catch (CommandException e) {
			System.out.println(e.getMessage());
			response += " Error. Try again...";
		}

		return response;
	}
}
