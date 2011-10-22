package POJO;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NoticeView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7155539652007397752L;

	private int noticeId;
    private String nameTypeNotice;
    private Date dateNotice;
    private String contentNotice;
    private String title;
    
    public NoticeView() {
		// TODO Auto-generated constructor stub
	}
    
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int i) {
		this.noticeId = i;
	}
	public String getNameTypeNotice() {
		return nameTypeNotice;
	}
	public void setNameTypeNotice(String nameTypeNotice) {
		this.nameTypeNotice = nameTypeNotice;
	}

	public Date getDateNotice() {
		return dateNotice;
	}
	public void setDateNotice(Date dateNotice) {
		this.dateNotice = dateNotice;
	}
	public String getContentNotice() {
		return contentNotice;
	}
	public void setContentNotice(String contentNotice) {
		this.contentNotice = contentNotice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    
    
}
