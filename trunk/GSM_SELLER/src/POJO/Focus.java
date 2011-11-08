package POJO;

import java.io.Serializable;

public class Focus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2072452484966899662L;

	private int focusId;
	private String nameFocus;
	private String cache;
	private int time;
	
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
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime() {
		return time;
	}
	
}
