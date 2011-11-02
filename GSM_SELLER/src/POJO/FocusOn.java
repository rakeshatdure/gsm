package POJO;

import java.io.Serializable;
import java.util.Date;

public class FocusOn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072452484966899662L;

	private int focusOnId;
	private String nameFocusOn;
	private String cache;
	private Date fromTime;
	private Date toTime;
	
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
	public void setFocusOnId(int focusOnId) {
		this.focusOnId = focusOnId;
	}
	public int getFocusOnId() {
		return focusOnId;
	}
	public void setNameFocusOn(String nameFocusOn) {
		this.nameFocusOn = nameFocusOn;
	}
	public String getNameFocusOn() {
		return nameFocusOn;
	}
	
	
}
