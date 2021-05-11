package Purchase.vo;

import java.sql.Date;

public class Purchase {
	private int buyno;
	private String id;
	private int membershipno;
	private Date beforedate;
	private Date afterdate;
	
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
	
	@Override
	public String toString() {
		return "Purchase [buyno=" + buyno + ", id=" + id + ", membershipno=" + membershipno + ", beforedate="
				+ beforedate + ", afterdate=" + afterdate + "]";
	}
	
}
