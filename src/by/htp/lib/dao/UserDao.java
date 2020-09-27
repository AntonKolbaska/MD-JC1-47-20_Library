package by.htp.lib.dao;

import by.htp.lib.bean.Status;
import by.htp.lib.bean.User;
import by.htp.lib.dao.exception.DAOException;

public interface UserDao {
	public User registerUser(String login, String password) throws DAOException;
	public User logination(String login, String password) throws DAOException;
	public boolean changeUserRole(String login, Status newRole) throws DAOException;
}