package by.htp.lib.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import by.htp.lib.bean.Book;
import by.htp.lib.bean.Status;
import by.htp.lib.dao.BookDao;
import by.htp.lib.dao.exception.DAOException;

public class BookDaoImpl implements BookDao {

	private static final String BOOK_DATABASE_FILE = "books.txt";

	@Override
	public void add(Book book) throws DAOException {
		FileWriter fileOutStream;
		BufferedWriter buffOutStream = null;
		PrintWriter printOutStream = null;

		try {

			fileOutStream = new FileWriter(BOOK_DATABASE_FILE, true);
			buffOutStream = new BufferedWriter(fileOutStream);
			printOutStream = new PrintWriter(buffOutStream);
			printOutStream.println(book.toString());

		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} finally {
			if (printOutStream != null) {
				printOutStream.close();
			}

		}
	}

	@Override
	public List<Book> allLibrary(Status access) throws DAOException {
		List<Book> books = new ArrayList<Book>();
		FileReader fileInStream = null;
		BufferedReader buffInStream = null;

		try {
			fileInStream = new FileReader(BOOK_DATABASE_FILE);
			buffInStream = new BufferedReader(fileInStream);
			StringBuilder dbLine = new StringBuilder();;
			dbLine.append(buffInStream.readLine());
			System.out.println(dbLine);
			String[] bookArgs;
			if (access.equals(Status.SENIOR)) {
				while (dbLine != null) {
					String temp = dbLine.toString();
					bookArgs = temp.split("\\s+");
					String title = bookArgs[0];
					int price = Integer.parseInt(bookArgs[1]);
					int edition = Integer.parseInt(bookArgs[2]);
					books.add(new Book(title, price, edition));
					dbLine.setLength(0);
					dbLine.append(buffInStream.readLine());
				}
			} else {
				while (dbLine != null) {
					String temp = dbLine.toString();
					bookArgs = temp.split("\\s+");
					String title = bookArgs[0];
					int price = Integer.parseInt(bookArgs[1]);
					int edition = Integer.parseInt(bookArgs[2]);
					String bookAccess = bookArgs[3];
					if (bookAccess.equals(Status.JUNIOR.toString())) {
						books.add(new Book(title, price, edition));
					}
					dbLine.setLength(0);
					dbLine.append(buffInStream.readLine());
				}
			}
		} catch (FileNotFoundException e) {
			throw new DAOException("FileNotFound", e);
		} catch (IOException e) {
			throw new DAOException("IOError", e);
		} catch (NumberFormatException e) {
			throw new DAOException("ParseIntError", e);
		} finally {
			try {
				if (buffInStream != null) {
					buffInStream.close();
				}
			} catch (IOException e1) {
				throw new DAOException("IOClosingError", e1);
			}
		}

		return books;
	}

}
