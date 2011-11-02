package POJO;

import java.io.Serializable;
import java.util.Date;

public class Focus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072452484966899662L;

	private int focusId;
	private String nameFocus;
	private String cache;
	private Date fromTime;
	private Date toTime;
	
	public int getFocusId() {
		return focusId;
	}
	public void setFocusId(int focusId) {
		this.focusId = focusId;
	}
	public String getNameFocus() {
		return nameFocus;
	}
	public void setNameFocus(String nameFocus) {
		this.nameFocus = nameFocus;
	}
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
	}
	public Date getFromTime() {
		return fromTime;
	}
	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	public Date getToTime() {
		return toTime;
	}
	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}
	
	
}
