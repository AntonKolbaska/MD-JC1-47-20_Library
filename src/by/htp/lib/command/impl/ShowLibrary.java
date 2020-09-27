package by.htp.lib.command.impl;

import java.util.List;

import by.htp.lib.bean.Book;
import by.htp.lib.bean.Status;
import by.htp.lib.command.Command;
import by.htp.lib.command.exception.CommandException;
import by.htp.lib.service.ServiceFactory;
import by.htp.lib.service.exception.ServiceException;

public class ShowLibrary implements Command {

	@Override
	public String execute(String request) throws CommandException {
		String[] params = request.split("\\s+");
		Status access = Status.JUNIOR;
		List<Book> books;
		
		try {
			access = Status.valueOf(params[0]);				
				
			books = ServiceFactory.getInstance().getUpdateLibraryService().showLibrary(access);
			
		} catch (IllegalArgumentException e) {
			throw new CommandException("UnrecognizableRole", e);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		StringBuilder response = new StringBuilder();
		for (Book book: books) {
			response.append(book.getTitle() + "(" + book.getPrice() + " BYN) \n");
		}
		
		return params[0] + " " + response;
	}

}
