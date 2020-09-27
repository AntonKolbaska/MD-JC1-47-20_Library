package by.htp.lib.dao;

import java.util.List;

import by.htp.lib.bean.Book;
import by.htp.lib.bean.Status;
import by.htp.lib.dao.exception.DAOException;

public interface BookDao {	
	public void add(Book book) throws DAOException;
	public List<Book> allLibrary(Status access) throws DAOException;
}
