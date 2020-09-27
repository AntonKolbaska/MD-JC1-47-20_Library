package by.htp.lib.service;

import java.util.List;

import by.htp.lib.bean.Book;
import by.htp.lib.bean.Status;
import by.htp.lib.service.exception.ServiceException;

public interface UpdateLibraryService {
	void addNewBook(String title, int price, int edition, Status access) throws ServiceException;
	List<Book> showLibrary(Status access) throws ServiceException;
}