package BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import POJO.CategoryChild;

@ManagedBean(name="categoryChildBean")
@SessionScoped
public class CategoryChildBean {
	private List<CategoryChild> allCategoryChild;
	
	public CategoryChildBean() {
	}
	
	public List<CategoryChild> getAllCategoryChild() {
		return allCategoryChild;
	}
	
	public void setAllCategoryChild(List<CategoryChild> allCategoryChild) {
		this.allCategoryChild = allCategoryChild;
	}
	

}
