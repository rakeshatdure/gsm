package BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.richfaces.model.Filter;

import BUS.CategoryBUS;
import BUS.CategoryChildBUS;
import BUS.CategorySubBUS;
import POJO.Category;
import POJO.CategoryChild;
import POJO.CategorySub;
import POJO.User;
import POJO.View.ProductsView;
@SuppressWarnings("unused")
@ManagedBean(name="categoryBeanM")
@SessionScoped
public class CategoryBeanM {
	List<Category> allCategory;
	Category category;
	
	private String lang;
	private String categoryId;
	private String categoryChildId;
	private String categorySubId;
	
	private List<Category> allCateogryByAccount;
	
	private List<CategoryChild> allCategoryChild;
	private List<CategorySub> allCategorySub;

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getAllCategory() {
		return getAllCategoryDatabase();
	}
	public void setAllCategory(List<Category> allCategory) {
		this.allCategory = allCategory;
	}

	public List<Category> getAllCateogryByAccount() {
		return getAllCategoryByUser();
	}
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		categoryChildId = null;
		categorySubId = null;
		this.categoryId = categoryId;
	}

	public void setAllCateogryByUser(List<Category> allCateogryByAccount) {
		this.allCateogryByAccount = allCateogryByAccount;
	}
	
	public List<CategoryChild> getAllCategoryChild() {
		return getListAllCategoryChild();
	}
	
	public void setAllCategoryChild(List<CategoryChild> allCategoryChild) {
		this.allCategoryChild = allCategoryChild;
	}
	
	public String getCategoryChildId() {
		return categoryChildId;
	}
	
	public void setCategoryChildId(String categoryChildId) {
		categorySubId = null;
		this.categoryChildId = categoryChildId;
	}
	
	public String getCategorySubId() {
		return categorySubId;
	}
	
	public void setCategorySubId(String categorySubId) {
		this.categorySubId = categorySubId;
	}
	public List<CategorySub> getAllCategorySub() {
		return getListAllCategorySub();
	}
	
	public void setAllCategorySub(List<CategorySub> allCategorySub) {
		this.allCategorySub = allCategorySub;
	}
	
	public String getLang() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession)f.getExternalContext().getSession(true);
		lang = (String) ss.getAttribute("MALL_LA");
		return lang;
	}
	public List<Category> getAllCategoryDatabase(){
		return CategoryBUS.lstCategory(lang);
	}
	// load all category name by user
	public List<Category> getAllCategoryByUser(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		return CategoryBUS.lstAllCategory(lang,user.getAccount());
	}
	// load all categoryChild by user, and categoryName
	public List<CategoryChild> getListAllCategoryChild(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		return CategoryChildBUS.lstAllCategoryChild(lang, categoryId, user.getAccount());
	}
	// load categorySub by account, categogyName, categoryChild
	public List<CategorySub> getListAllCategorySub(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		return CategorySubBUS.lstAllCategorySub(lang, categoryId, categoryChildId, user.getAccount());
	}
}
