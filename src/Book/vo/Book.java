package Book.vo;

import java.sql.Date;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private Date pubDate;
	private String description;
	private int pricesales;
	private String cover;
	private int ranks;
	private String adult;
	private String CategoryName;
	private int siteRanks;
	private int count;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
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
	public String getAdult() {
		return adult;
	}
	public void setAdult(String adult) {
		this.adult = adult;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	
	public int getSiteRanks() {
		return siteRanks;
	}
	public void setSiteRanks(int siteRanks) {
		this.siteRanks = siteRanks;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", pubDate=" + pubDate + ", description=" + description + ", pricesales=" + pricesales + ", cover="
				+ cover + ", ranks=" + ranks + ", adult=" + adult + ", CategoryName=" + CategoryName + ", siteRanks="
				+ siteRanks + ", count=" + count + "]";
	}
	
	
}
