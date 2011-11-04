package BEAN.VANHIEP;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.HibernateDAO;
import POJO.Category;
import POJO.CategoryChild;
import POJO.CategorySub;
import POJO.Focus;
import POJO.FocusOn;

@ManagedBean (name="registryProduct")
@SessionScoped
@SuppressWarnings("unchecked")
public class RegistryProductBean extends HibernateDAO{

	private List<Category> lCat;
	private List<CategoryChild> lCatChild;
	private List<CategorySub> lCatSub; 
	private String categoryId;
	private String categoryChildId;
	private String categorySubId;
	private String lang;
	private String SellingModeId;
	
	private boolean onCC = true;
	private boolean onCS = true;
	
	private List<Focus> lFocus;
	private String idFocus;
	private List<FocusOn> lFocusOn;
	private String idFocusOn;
	
	private boolean on1 = false;
	private boolean on2 = false;
	private boolean on3 = false;
	
	

	public void setlCat(List<Category> lCat) {
		this.lCat = lCat;
	}

	public List<Category> getlCat() {
		String action = "from Category";
		lCat = HibernateDAO.getList(action, lang);
		return lCat;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLang() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession)f.getExternalContext().getSession(true);
		lang = (String) ss.getAttribute("MALL_LA");
		return lang;
	}

	public List<CategoryChild> getlCatChild() {
		lCatChild = HibernateDAO.getList("from CategoryChild cc where cc.categoryId= '" + categoryId +"'", lang);
		return lCatChild;
	}

	public void setlCatChil(List<CategoryChild> lCatChild) {
		this.lCatChild = lCatChild;
	}

	public List<CategorySub> getlCatSub() {
		lCatSub = HibernateDAO.getList("from CategorySub cs where cs.categoryChildId = '" + categoryChildId +"'", lang);
		return lCatSub;
	}

	public void setlCatSub(List<CategorySub> lCatSub) {
		this.lCatSub = lCatSub;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		categoryChildId = "";
		categorySubId = "";
		setOnCC(false);
		setOnCS(true);
		this.categoryId = categoryId;
	}

	public String getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(String categoryChildId) {
		categorySubId = "";
		setOnCS(false);
		this.categoryChildId = categoryChildId;
	}

	public String getCategorySubId() {
		return categorySubId;
	}

	public void setCategorySubId(String categorySubId) {
		this.categorySubId = categorySubId;
	}

	public void setOnCC(boolean onCC) {
		this.onCC = onCC;
	}

	public boolean isOnCC() {
		return onCC;
	}

	public void setOnCS(boolean onCS) {
		this.onCS = onCS;
	}

	public boolean isOnCS() {
		return onCS;
	}

	public void setSellingModeId(String sellingModeId) {
		SellingModeId = sellingModeId;
	}

	public String getSellingModeId() {
		return SellingModeId;
	}

	public void setOn1(boolean on1) {
		this.on1 = on1;
	}

	public boolean isOn1() {
		return on1;
	}

	public void setOn2(boolean on2) {
		this.on2 = on2;
	}

	public boolean isOn2() {
		return on2;
	}

	public void setOn3(boolean on3) {
		this.on3 = on3;
	}

	public boolean isOn3() {
		return on3;
	}

	public void setlFocus(List<Focus> lFocus) {
		this.lFocus = lFocus;
	}

	
	public List<Focus> getlFocus() {
		lFocus = HibernateDAO.getList("from Focus", lang);
		return lFocus;
	}

	public void setIdFocus(String idFocus) {
		this.idFocus = idFocus;
	}

	public String getIdFocus() {
		return idFocus;
	}

	public void setlFocusOn(List<FocusOn> lFocusOn) {
		this.lFocusOn = lFocusOn;
	}

	public List<FocusOn> getlFocusOn() {
		lFocusOn = HibernateDAO.getList("from FocusOn", lang);
		return lFocusOn;
	}

	public void setIdFocusOn(String idFocusOn) {
		this.idFocusOn = idFocusOn;
	}

	public String getIdFocusOn() {
		return idFocusOn;
	}
	
}
