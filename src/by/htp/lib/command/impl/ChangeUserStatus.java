package by.htp.lib.command.impl;
import by.htp.lib.bean.Status;
import by.htp.lib.command.Command;
import by.htp.lib.command.exception.CommandException;
import by.htp.lib.service.ServiceFactory;
import by.htp.lib.service.exception.ServiceException;

public class ChangeUserStatus implements Command {

	@Override
	public String execute(String request) throws CommandException {
		String[] params = request.split("\\s+");
		Status accessRole = Status.JUNIOR;
		Status targetRole = Status.SENIOR;
		String targetUserLogin;
		boolean success;
		
		try {
			accessRole = Status.valueOf(params[0]);
				
			if (!accessRole.equals(Status.SENIOR)) {
				return params[0] + " This command can't be executed with your access!";
			}
					
			if (params.length > 2) {
				targetUserLogin = params[2];
			} else {
				throw new CommandException("There is no target user login!");
			}
		
			if (params.length > 3) {	
				targetRole = Status.valueOf(params[3]);
			}
				
			success = ServiceFactory.getInstance().getUserService().changeUserRole(targetUserLogin, targetRole);
			
		} catch (IllegalArgumentException e) {
			throw new CommandException("UnrecognizableRole", e);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		if (success) {
			return params[0] + " The " + targetUserLogin + "'s role is set to the " + targetRole.toString();
		} else {
			return params[0] + " Such user doesn't exist!";
		}
	}

}
