package BEAN;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import BUS.DayBUS;
import POJO.Day;

@ManagedBean(name="dayBean")
@SessionScoped
public class DayBEAN {
	private int dayId;
	private int time;
	private List<Day> allDay;
	private String lang;
	private String calend;
	

	public String getLang() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession)f.getExternalContext().getSession(true);
		lang = (String) ss.getAttribute("MALL_LA");
		return lang;
	}
	public int getDayId() {
		return dayId;
	}
	public int getTime() {
		return loadDay();
	}
	public void setTime(int time) {
		this.time = time;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<Day> getAllDay() {
		return getListAllDay();
	}
	public void setAllDay(List<Day> allDay) {
		this.allDay = allDay;
	}
	// load all day
	public List<Day> getListAllDay(){
		return DayBUS.lstDay(lang);
	}

	public int loadDay(){
		return DayBUS.loadDayById(lang, dayId);
	}
	public String getCalend() {
		return getCalendarByID();
	}
	public void setCalend(String calend) {
		this.calend = calend;
	}
	
	public String getCalendarByID(){
		 Calendar calendar = Calendar.getInstance();
		 calendar.add(Calendar.DATE,getTime());
		return calendar.get(Calendar.YEAR) +  "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
	}
}
