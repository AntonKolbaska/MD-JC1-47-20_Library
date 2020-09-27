package by.htp.lib.service.impl;

import by.htp.lib.bean.Status;
import by.htp.lib.bean.User;
import by.htp.lib.dao.DAOFactory;
import by.htp.lib.dao.UserDao;
import by.htp.lib.dao.exception.DAOException;
import by.htp.lib.service.UserService;
import by.htp.lib.service.exception.ServiceException;

public class UserServiceImpl implements UserService{

	@Override
	public User logination(String login, String password) throws ServiceException {
		User loggedUser;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDao userDAO = daoFactory.getUserDAO();
		
		try {
			loggedUser = userDAO.logination(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return loggedUser;
	}

	@Override
	public User registerUser(String login, String password) throws ServiceException {
		User signedUser;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDao userDAO = daoFactory.getUserDAO();
		
		try {
			signedUser = userDAO.registerUser(login, password);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return signedUser;
	}

	@Override
	public boolean changeUserRole(String login, Status newRole) throws ServiceException {
		boolean success;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDao userDAO = daoFactory.getUserDAO();
		
		try {
			success = userDAO.changeUserRole(login, newRole);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return success;
	}

}
