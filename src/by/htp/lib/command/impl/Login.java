package by.htp.lib.command.impl;

import by.htp.lib.bean.User;
import by.htp.lib.command.Command;
import by.htp.lib.command.exception.CommandException;
import by.htp.lib.service.ServiceFactory;
import by.htp.lib.service.exception.ServiceException;

public class Login implements Command{

	@Override
	public String execute(String request) throws CommandException {
		String[] params = request.split("\\s+");
		String login;
		String password;
		
		if (params.length > 2) {
			login = params[2];
		} else {
			throw new CommandException("There is no login!");
		}
		if (params.length > 3) {
			password = params[3];
		} else {
			throw new CommandException("There is no password!");
		}
		
		User loggedUser;
		try {
			loggedUser = ServiceFactory.getInstance().getUserService().logination(login, password);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return loggedUser.getRole().toString() + " Hello, " + loggedUser.getLogin();
	}

}
