package POJO;

import java.io.Serializable;

public class FocusOn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072452484966899662L;

	private int focusOnId;
	private String nameFocusOn;
	private String cache;
	private int time;
	
	public String getCache() {
		return cache;
	}
	public void setCache(String cache) {
		this.cache = cache;
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
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime() {
		return time;
	}
	
	
}
