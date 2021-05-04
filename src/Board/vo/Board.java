package Board.vo;

import java.sql.Date;

public class Board {
	private int boardno;
	private String id;
	private Date boarddate;
	private String boardcontent;
	private String boardtitle;
	private int boardplay;
	private int boardcount;
	private String boardfile;
	
	public String getBoardfile() {
		return boardfile;
	}
	public void setBoardfile(String boardfile) {
		this.boardfile = boardfile;
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
	public Date getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(Date boarddate) {
		this.boarddate = boarddate;
	}
	public String getBoardcontent() {
		return boardcontent;
	}
	public void setBoardcontent(String boardcontent) {
		this.boardcontent = boardcontent;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public int getBoardplay() {
		return boardplay;
	}
	public void setBoardplay(int boardplay) {
		this.boardplay = boardplay;
	}
	public int getBoardcount() {
		return boardcount;
	}
	public void setBoardcount(int boardcount) {
		this.boardcount = boardcount;
	}
	
	@Override
	public String toString() {
		return "Board [boardno=" + boardno + ", id=" + id + ", boarddate=" + boarddate + ", boardcontent="
				+ boardcontent + ", boardtitle=" + boardtitle + ", boardplay=" + boardplay + ", boardcount="
				+ boardcount + "]";
	}
}
