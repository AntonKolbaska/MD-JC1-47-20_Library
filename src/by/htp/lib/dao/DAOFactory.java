package by.htp.lib.dao;

import by.htp.lib.dao.impl.BookDaoImpl;
import by.htp.lib.dao.impl.UserDaoImpl;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();	
	private UserDao userDAO = new UserDaoImpl();
	private BookDao bookDAO = new BookDaoImpl();
	
	private DAOFactory(){}
	
	public static DAOFactory getInstance(){
		return instance;
	}
	
	public UserDao getUserDAO(){
		return userDAO;
	}
	
	public BookDao getBookDAO(){
		return bookDAO;
	}

}