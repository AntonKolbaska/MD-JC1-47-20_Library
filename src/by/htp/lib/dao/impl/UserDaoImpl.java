package by.htp.lib.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import by.htp.lib.bean.Status;
import by.htp.lib.bean.User;
import by.htp.lib.dao.UserDao;
import by.htp.lib.dao.exception.DAOException;

public class UserDaoImpl implements UserDao {

	private static final String USER_DATABASE_FILE = "users.txt";

	@Override
	public User registerUser(String login, String password) throws DAOException {
		BufferedReader buffInStream = null;
		PrintWriter printOutStream = null;

		try {
			FileReader fileInStream = new FileReader(USER_DATABASE_FILE);

			buffInStream = new BufferedReader(fileInStream);
			String dbLine;
			dbLine = buffInStream.readLine();
			String userLogin = null;
			while (dbLine != null) {
				userLogin = dbLine.split("\\s+")[0];
				if (userLogin.equals(login)) {
					throw new DAOException("SuchUserAlreadyExists");
				}
				dbLine = buffInStream.readLine();
			}
			FileWriter fileOutStream = new FileWriter(USER_DATABASE_FILE, true);
			BufferedWriter buffOutStream = new BufferedWriter(fileOutStream);
			printOutStream = new PrintWriter(buffOutStream);

			printOutStream.println("\n" + login + " " + password + " " + "JUNIOR");

		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} finally {
			if (printOutStream != null) {
				printOutStream.close();
			}

			try {
				if (buffInStream != null) {
					buffInStream.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}
		}

		return new User(login, password);
	}

	@Override
	public User logination(String login, String password) throws DAOException {
		BufferedReader buffInStream = null;
		String[] userArgs;

		try {
			FileReader fileInStream = new FileReader(USER_DATABASE_FILE);
			buffInStream = new BufferedReader(fileInStream);

			String dbLine;
			dbLine = buffInStream.readLine();
			userArgs = null;
			while (dbLine != null) {
				userArgs = dbLine.split("\\s+");
				if (userArgs[0].equals(login) && userArgs[1].equals(password)) {
					return new User(login, password, Status.valueOf(userArgs[2]));
				}

				dbLine = buffInStream.readLine();
			}
			throw new DAOException("UserNotFound");
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} finally {
			try {
				if (buffInStream != null) {
					buffInStream.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}
		}

	}

	@Override
	public boolean changeUserRole(String login, Status newStatus) throws DAOException {
		RandomAccessFile randAFile = null;
		
		try {
			randAFile = new RandomAccessFile(USER_DATABASE_FILE, "rw");
			String dbLine;
			dbLine = randAFile.readLine();
			String[] userArgs = null;
			while (dbLine != null) {
				userArgs = dbLine.split("\\s+");
				if (userArgs[0].equals(login)) {
					Status currentStatus;
					currentStatus = Status.valueOf(userArgs[2]);

					if (!currentStatus.equals(newStatus)) {
						randAFile.seek(randAFile.getFilePointer() - roleMaxLength() - 2);
						for (int i = 0; i < roleMaxLength(); i++) {
							randAFile.writeByte(' ');
						}
						randAFile.seek(randAFile.getFilePointer() - roleMaxLength());
						randAFile.writeBytes(newStatus.toString());
					}

					return true;
				}

				dbLine = randAFile.readLine();
			}
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} catch (IllegalArgumentException e) {
			throw new DAOException("UnrecognizableRole", e);
		} finally {
			try {
				if (randAFile != null) {
					randAFile.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}
		}

		return false;
	}
	private static byte roleMaxLength() {
		byte maxLength = 0;
		for (Status role : Status.values()) {
			if (role.toString().length() > maxLength) {
				maxLength = (byte) role.toString().length();
			}
		}
		return maxLength;
	}
}
