package MemberShip.vo;

public class MemberShip {
	private int membershipno;
	private String membershipname;
	private int membershipdate;
	private int membershipprice;
	
	public int getMembershipno() {
		return membershipno;
	}
	public void setMembershipno(int membershipno) {
		this.membershipno = membershipno;
	}
	public String getMembershipname() {
		return membershipname;
	}
	public void setMembershipname(String membershipname) {
		this.membershipname = membershipname;
	}
	public int getMembershipdate() {
		return membershipdate;
	}
	public void setMembershipdate(int membershipdate) {
		this.membershipdate = membershipdate;
	}
	public int getMembershipprice() {
		return membershipprice;
	}
	public void setMembershipprice(int membershipprice) {
		this.membershipprice = membershipprice;
	}
	
	@Override
	public String toString() {
		return "MemberShip [membershipno=" + membershipno + ", membershipname=" + membershipname + ", membershipdate="
				+ membershipdate + ", membershipprice=" + membershipprice + "]";
	}
	
}
