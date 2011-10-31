package BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import BUS.CategoryBUS;
import POJO.Category;
import POJO.User;

@ManagedBean(name="categoryBean")
@SessionScoped
public class CategoryBean {
	List<Category> allCategory;
	Category category;
	private List<String> allCateogryByAccount;
	
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
	
	public List<String> getAllCateogryByAccount() {
		return getAllCategoryByUser();
	}
	
	public void setAllCateogryByUser(List<String> allCateogryByAccount) {
		this.allCateogryByAccount = allCateogryByAccount;
	}
	
	public List<Category> getAllCategoryDatabase(){
		return CategoryBUS.lstCategory("MALL_LA");
	}
	
	public List<String> getAllCategoryByUser(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		return CategoryBUS.lstAllCategory("MALL_LA","sell01");
	}
	public static void main(String[] args) {
		CategoryBean cat = new CategoryBean();
		System.out.println(" category Name : " + cat.getAllCategory());
	}

}
