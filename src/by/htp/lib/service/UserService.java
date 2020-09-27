package by.htp.lib.service;

import by.htp.lib.bean.Status;
import by.htp.lib.bean.User;
import by.htp.lib.service.exception.ServiceException;

public interface UserService {
	User logination(String login, String password) throws ServiceException;
	User registerUser(String login, String password) throws ServiceException;
	boolean changeUserRole(String login, Status newRole) throws ServiceException;
}
