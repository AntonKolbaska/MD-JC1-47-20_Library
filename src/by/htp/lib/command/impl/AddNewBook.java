package by.htp.lib.command.impl;

import by.htp.lib.bean.Status;
import by.htp.lib.command.Command;
import by.htp.lib.command.exception.CommandException;
import by.htp.lib.service.ServiceFactory;
import by.htp.lib.service.exception.ServiceException;

public class AddNewBook implements Command{

	@Override
	public String execute(String request) throws CommandException {
		String[] params = request.split("\\s+");
		/*System.out.println("request: " + request);
		for(int i=0; i<params.length; ++i) {
			
			System.out.println(i + "-arg: " + params[i]);
		}*/
		
		String title = "Unknown";
		int price = 0;
		int edition =1;
		Status access = Status.JUNIOR;
		
		try {
			if (params.length > 2) {
				title = params[2];
				//System.out.println(title);
			}
			if (params.length > 3) {			
				price = Integer.parseInt(params[3]);	
				//System.out.println(price);
			}
			if (params.length > 4) {			
				edition = Integer.parseInt(params[4]);
				//System.out.println(edition);
			}
			if(params.length > 5) {
				access = Status.valueOf(params[5]);
				//System.out.println(access);
			}
		
			ServiceFactory.getInstance().getUpdateLibraryService().addNewBook(title, price, edition, access);
		
		} catch (NumberFormatException e) {
			throw new CommandException("Incorrect book price!", e);
		} catch (IllegalArgumentException e) {
			throw new CommandException("UnrecognizableRole", e);		
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return params[0] + " New book was succesfully added!";
	}

}
