package Member.vo;

public class Member {
	String id;
	String pwd;
	String name;
	String email;
	int genre;
	int phone;
	int membership;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getGenre() {
		return genre;
	}
	public void setGenre(int genre) {
		this.genre = genre;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getMembership() {
		return membership;
	}
	public void setMembership(int membership) {
		this.membership = membership;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", genre=" + genre
				+ ", phone=" + phone + ", membership=" + membership + "]";
	}
}
