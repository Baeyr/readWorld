package Board.vo;

public class Comment {
	private int commentno;
	private int boardno;
	private String id;
	private String cmtcontent;
	private int cmtrootno;
	private int cmtstep;
	private int cmtlevel;

	@Override
	public String toString() {
		return "Comment [commentno=" + commentno + ", boardno=" + boardno + ", id=" + id + ", cmtcontent=" + cmtcontent
				+ ", cmtrootno=" + cmtrootno + ", cmtstep=" + cmtstep + ", cmtlevel=" + cmtlevel + "]";
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCmtcontent() {
		return cmtcontent;
	}

	public void setCmtcontent(String cmtcontent) {
		this.cmtcontent = cmtcontent;
	}

	public int getCmtrootno() {
		return cmtrootno;
	}

	public void setCmtrootno(int cmtrootno) {
		this.cmtrootno = cmtrootno;
	}

	public int getCmtstep() {
		return cmtstep;
	}

	public void setCmtstep(int cmtstep) {
		this.cmtstep = cmtstep;
	}

	public int getCmtlevel() {
		return cmtlevel;
	}

	public void setCmtlevel(int cmtlevel) {
		this.cmtlevel = cmtlevel;
	}

	public Comment() {
		super();
	}
	
}
