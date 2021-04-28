package Book.vo;

import java.sql.Date;

public class Book {
	private int bookno;
	private String title;
	private String author;
	private String publisher;
	private Date pubDate;
	private String description;
	private int isbn;
	private int pricesales;
	private String cover;
	private int ranks;
	private char adult;
	private String CategoryName;
	
	
	public int getBookno() {
		return bookno;
	}
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public int getPricesales() {
		return pricesales;
	}
	public void setPricesales(int pricesales) {
		this.pricesales = pricesales;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getRanks() {
		return ranks;
	}
	public void setRanks(int ranks) {
		this.ranks = ranks;
	}
	public char getAdult() {
		return adult;
	}
	public void setAdult(char adult) {
		this.adult = adult;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return "Book [bookno=" + bookno + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", pubDate=" + pubDate + ", description=" + description + ", isbn=" + isbn + ", pricesales="
				+ pricesales + ", cover=" + cover + ", ranks=" + ranks + ", adult=" + adult + ", CategoryName="
				+ CategoryName + "]";
	}
}
