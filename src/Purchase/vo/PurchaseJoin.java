package Purchase.vo;

import java.sql.Date;

public class PurchaseJoin {
	private int buyno;
	private String id;
	private int membershipno;
	private Date beforedate;
	private Date afterdate;
	private String membershipname;
	
	
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
	public Date getBeforedate() {
		return beforedate;
	}
	public void setBeforedate(Date beforedate) {
		this.beforedate = beforedate;
	}
	public Date getAfterdate() {
		return afterdate;
	}
	public void setAfterdate(Date afterdate) {
		this.afterdate = afterdate;
	}
	public String getMembershipname() {
		return membershipname;
	}
	public void setMembershipname(String membershipname) {
		this.membershipname = membershipname;
	}
	
	
}

