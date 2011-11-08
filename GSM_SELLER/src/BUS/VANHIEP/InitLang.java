package BUS.VANHIEP;


import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import UTIL.HtmlStringUtils;

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
	
	// TODO MO
	/* login*/
	private String username;
	private String password;
	private String login;
	private String do_not_have_permission;
	/*profile seller */
	private String sellerinfo;
	private String general;
	private String is_report_document;
	private String agent_name;
	private String register_date;
	private String ID;
	private String agent_type;
	private String contract_fee;
	private String view_detail;
	private String level_seller;
	private String credit_core;
	private String auction_manage;
	private String penalty_score;
	private String dolar;
	private String level_cs_seller;
	
	
	
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


	// Mo
	
	//getter and setter login
	public String getUsername(){
		username = LanguegeBUS.getValue("username", lang);
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		password = LanguegeBUS.getValue("password", lang);
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getLogin(){
		login = LanguegeBUS.getValue("login", lang);
		return login;
	}
	
	public void setLogin(String login){
		this.login = login;
	}
	
	public String getDo_not_have_permission(){
		do_not_have_permission = LanguegeBUS.getValue("do_not_have_permission", lang);
		return do_not_have_permission;
	}
	
	public void setDo_not_have_permission(String do_not_have_permission){
		this.do_not_have_permission = do_not_have_permission;
	}
	// getter and setter profile seller
	public String getSellerinfo() {
		sellerinfo = LanguegeBUS.getValue("sellerinfo", lang);
		return sellerinfo;
	}

	public void setSellerinfo(String sellerinfo) {
		this.sellerinfo = sellerinfo;
	}

	public String getGeneral() {
		general = LanguegeBUS.getValue("general", lang);
		return general;
	}

	public void setGeneral(String general) {
		this.general = general;
	}

	public String getIs_report_document() {
		is_report_document = LanguegeBUS.getValue("is_report_document", lang);
		return is_report_document;
	}

	public void setIs_report_document(String is_report_document) {
		this.is_report_document = is_report_document;
	}

	public String getAgent_name() {
		agent_name = LanguegeBUS.getValue("agent_name", lang);
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getRegister_date() {
		register_date = LanguegeBUS.getValue("register_date", lang);
		return register_date;
	}

	public void setRegister_date(String register_date) {
		this.register_date = register_date;
	}

	public String getID() {
		ID = LanguegeBUS.getValue("ID", lang);
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getAgent_type() {
		agent_type = LanguegeBUS.getValue("agent_type", lang);
		return agent_type;
	}

	public void setAgent_type(String agent_type) {
		this.agent_type = agent_type;
	}

	public String getContract_fee() {
		contract_fee = LanguegeBUS.getValue("contract_fee", lang);
		return contract_fee;
	}

	public void setContract_fee(String contract_fee) {
		this.contract_fee = contract_fee;
	}

	public String getView_detail() {
		view_detail = LanguegeBUS.getValue("view_detail", lang);
		return view_detail;
	}

	public void setView_detail(String view_detail) {
		this.view_detail = view_detail;
	}

	public String getLevel_seller() {
		level_seller = LanguegeBUS.getValue("level_seller", lang);
		return level_seller;
	}

	public void setLevel_seller(String level_seller) {
		this.level_seller = level_seller;
	}

	public String getCredit_core() {
		credit_core = LanguegeBUS.getValue("credit_core", lang);
		return credit_core;
	}

	public void setCredit_core(String credit_core) {
		this.credit_core = credit_core;
	}

	public String getAuction_manage() {
		auction_manage = LanguegeBUS.getValue("auction_manage", lang);
		return auction_manage;
	}

	public void setAuction_manage(String auction_manage) {
		this.auction_manage = auction_manage;
	}

	public String getPenalty_score() {
		penalty_score = LanguegeBUS.getValue("penalty_score", lang);
		return penalty_score;
	}

	public void setPenalty_score(String penalty_score) {
		this.penalty_score = penalty_score;
	}

	public String getDolar() {
		dolar = LanguegeBUS.getValue("dolar", lang);
		return dolar;
	}

	public void setDolar(String dolar) {
		this.dolar = dolar;
	}

	public String getLevel_cs_seller() {
		level_cs_seller = LanguegeBUS.getValue("level_cs_seller", lang);
		return level_cs_seller;
	}

	public void setLevel_cs_seller(String level_cs_seller) {
		this.level_cs_seller = level_cs_seller;
	}
	public String getContent() {
		System.out.println("adfasdf");
		HtmlStringUtils u = new HtmlStringUtils(getmNotice1());
		String content = new HtmlStringUtils(getmNotice1()).subStringHtmlString(
				u.parseHtmlToTextNoEntity().length()).getPrimeContent();
		System.out.println(content);
		return content;
	}
	
}
