package MemberShip.vo;

import java.sql.Date;

public class Purchase {
	private int buyno;
	private String id;
	private int membershipno;
	private Date userdate;
	
	public int getBuyno() {
		return buyno;
	}
	public void setBuyno(int buyno) {
		this.buyno = buyno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMembershipno() {
		return membershipno;
	}
	public void setMembershipno(int membershipno) {
		this.membershipno = membershipno;
	}
	public Date getUserdate() {
		return userdate;
	}
	public void setUserdate(Date userdate) {
		this.userdate = userdate;
	}
	
	@Override
	public String toString() {
		return "Purchase [buyno=" + buyno + ", id=" + id + ", membershipno=" + membershipno + ", userdate=" + userdate
				+ "]";
	}
	
}
