package by.htp.lib.service.impl;

import java.util.List;

import by.htp.lib.bean.Book;
import by.htp.lib.bean.Status;
import by.htp.lib.dao.DAOFactory;
import by.htp.lib.dao.exception.DAOException;
import by.htp.lib.service.UpdateLibraryService;
import by.htp.lib.service.exception.ServiceException;

public class UpdateLibraryServiceImpl implements UpdateLibraryService{
	@Override
	public void addNewBook(String title, int price, int edition,  Status access) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();

		try {
			daoFactory.getBookDAO().add(new Book(title, price, edition, access));
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Book> showLibrary(Status access) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		List<Book> books;

		try {
			books = daoFactory.getBookDAO().allLibrary(access);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

		return books;
	}
}
