package Qna.vo;

import java.sql.Date;

public class Qna {
	private int qnano;
	private String qnatitle;
	private String qnacontent;
	private Date qnadate;
	private String id;
	private int qnaref;
	private int qna_step;
	private int qna_level;
	
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public String getQnatitle() {
		return qnatitle;
	}
	public void setQnatitle(String qnatitle) {
		this.qnatitle = qnatitle;
	}
	public String getQnacontent() {
		return qnacontent;
	}
	public void setQnacontent(String qnacontent) {
		this.qnacontent = qnacontent;
	}
	public Date getQnadate() {
		return qnadate;
	}
	public void setQnadate(Date qnadate) {
		this.qnadate = qnadate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getQnaref() {
		return qnaref;
	}
	public void setQnaref(int qnaref) {
		this.qnaref = qnaref;
	}
	public int getQna_step() {
		return qna_step;
	}
	public void setQna_step(int qna_step) {
		this.qna_step = qna_step;
	}
	public int getQna_level() {
		return qna_level;
	}
	public void setQna_level(int qna_level) {
		this.qna_level = qna_level;
	}
}
