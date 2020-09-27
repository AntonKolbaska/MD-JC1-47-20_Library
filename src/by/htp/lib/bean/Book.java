package by.htp.lib.bean;

import java.io.Serializable;

public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private int price;
	private int edition;
	private Status access;

	public Book() {
		this.title = "DEFAULT TITLE";
		this.price = 666;
		this.edition = 1;
		this.access = Status.JUNIOR;
	}

	public Book(String title, int price) {
		this.title = title;
		this.price = price;
		this.edition = 1;
		this.access = Status.JUNIOR;
	}

	public Book(String title, int price, int edition) {
		this.title = title;
		this.price = price;
		this.edition = edition;
		this.access = Status.JUNIOR;
	}

	public Book(String title, int price, int edition, Status access) {
		this.title = title;
		this.price = price;
		this.edition = edition;
		this.access = access;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Status getAccess() {
		return access;
	}

	public void setAccess(Status access) {
		this.access = access;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((access == null) ? 0 : access.hashCode());
		result = prime * result + price;
		result = prime * result + edition;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (access != other.access)
			return false;
		if (edition != other.edition)
			return false;
		if (price != other.price)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book | title=" + title + ", price=" + price + ", edition:" + edition + ", access=" + access + "|";
	}

}
