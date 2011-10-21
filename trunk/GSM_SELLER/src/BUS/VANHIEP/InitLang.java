package BUS.VANHIEP;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import BUS.LanguegeBUS;

@ManagedBean (name = "initLang")
@SessionScoped
public class InitLang implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423655954224094538L;

	private String lang;
	private String mNotice1;
	private String notice;
	private String allnotice;
	private String generalnotice;
	private String safeNotice;
	private String advnotice;
	private String systemnotice;
	private String categorynotice;
	private String _no;
	private String category;
	private String title;
	private String date;
	private String hdNo;
	private String ttNo;
	
	
	public InitLang() {
		// TODO Auto-generated constructor stub
	}

	public String getmNotice1() {
		mNotice1 = LanguegeBUS.getValue("mNotice1", lang);
		return mNotice1;
	}

	public void setmNotice1(String mNotice1) {
		this.mNotice1 = mNotice1;
	}
	
	public String getLang() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession)f.getExternalContext().getSession(true);
		lang = (String) ss.getAttribute("MALL_LA");
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getNotice() {
		notice = LanguegeBUS.getValue("notice", lang);
		return notice;
	}

	public String getAllnotice() {
		allnotice = LanguegeBUS.getValue("allnotice", lang);
		return allnotice;
	}

	public void setAllnotice(String allnotice) {
		this.allnotice = allnotice;
	}

	public String getGeneralnotice() {
		generalnotice = LanguegeBUS.getValue("generalnotice", lang);
		return generalnotice;
	}

	public void setGeneralnotice(String generalnotice) {
		this.generalnotice = generalnotice;
	}

	public String getSafeNotice() {
		safeNotice = LanguegeBUS.getValue("safeNotice", lang);
		return safeNotice;
	}

	public void setSafeNotice(String safeNotice) {
		this.safeNotice = safeNotice;
	}

	public String getAdvnotice() {
		advnotice = LanguegeBUS.getValue("advnotice", lang);
		return advnotice;
	}

	public void setAdvnotice(String advnotice) {
		this.advnotice = advnotice;
	}

	public String getSystemnotice() {
		systemnotice = LanguegeBUS.getValue("systemnotice", lang);
		return systemnotice;
	}

	public void setSystemnotice(String systemnotice) {
		this.systemnotice = systemnotice;
	}

	public String getCategorynotice() {
		categorynotice = LanguegeBUS.getValue("categorynotice", lang);
		return categorynotice;
	}

	public void setCategorynotice(String categorynotice) {
		this.categorynotice = categorynotice;
	}

	public String get_no() {
		_no = LanguegeBUS.getValue("_no", lang);
		return _no;
	}

	public void set_no(String _no) {
		this._no = _no;
	}

	public String getCategory() {
		category = LanguegeBUS.getValue("category", lang);
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		title = LanguegeBUS.getValue("title", lang);
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		date = LanguegeBUS.getValue("date", lang);
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHdNo() {
		hdNo = LanguegeBUS.getValue("hdNo", lang);
		return hdNo;
	}

	public void setHdNo(String hdNo) {
		this.hdNo = hdNo;
	}

	public String getTtNo() {
		ttNo = LanguegeBUS.getValue("ttNo", lang);
		return ttNo;
	}

	public void setTtNo(String ttNo) {
		this.ttNo = ttNo;
	}

	
	
	
}
