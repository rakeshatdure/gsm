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

@ManagedBean (name="categoryBean")
@SessionScoped
public class CategoryBean extends HibernateDAO {
	
	private List<Category> lCat;
	private List<CategoryChild> lCatChil;
	private List<CategorySub> lCatSub; 
	private String categoryId;
	private String categoryChildId;
	private String categorySubId;
	private String lang;

	public void setlCat(List<Category> lCat) {
		this.lCat = lCat;
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	public List<CategoryChild> getlCatChil() {
		lCatChil = HibernateDAO.getList("from CategoryChild cc where cc.categoryId= '" + categoryId +"'", lang);
		return lCatChil;
	}

	public void setlCatChil(List<CategoryChild> lCatChil) {
		this.lCatChil = lCatChil;
	}

	@SuppressWarnings("unchecked")
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
		this.categoryId = categoryId;
	}

	public String getCategoryChildId() {
		return categoryChildId;
	}

	public void setCategoryChildId(String categoryChildId) {
		this.categoryChildId = categoryChildId;
	}

	public String getCategorySubId() {
		return categorySubId;
	}

	public void setCategorySubId(String categorySubId) {
		this.categorySubId = categorySubId;
	}
	
	
}
