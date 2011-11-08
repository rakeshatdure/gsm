package POJO.View;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;

@Entity
@ManagedBean
@SessionScoped
public class ProductsView implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private int productId;
	private String productName;
	private float price;
	private float totalPrice;
	private int amount;
	private Date limitDate;
	private String categoryName;
	private String categoryChild;
	private String categorySub;
	private int invetoryID;
	private String stateName;

	private boolean checked;
	private boolean isSelect = false;

	public ProductsView() {
	}

	public ProductsView(int productId, String productName, float price,
			float totalPrice, int amount, Date limitDate, String categoryName,
			String categoryChild, String categorySub, int invetoryID,
			String stateName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.totalPrice = totalPrice;
		this.amount = amount;
		this.limitDate = limitDate;
		this.categoryName = categoryName;
		this.categoryChild = categoryChild;
		this.categorySub = categorySub;
		this.invetoryID = invetoryID;
		this.stateName = stateName;
	}

	public ProductsView(int productId, String productName, float price,
			float totalPrice, int amount, Date limitDate, String categoryName,
			String categoryChild, String categorySub, int invetoryID) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.totalPrice = totalPrice;
		this.amount = amount;
		this.limitDate = limitDate;
		this.categoryName = categoryName;
		this.categoryChild = categoryChild;
		this.categorySub = categorySub;
		this.invetoryID = invetoryID;
	}

	public ProductsView(int productId, String productName, float price,
			float totalPrice, int amount, Date limitDate, String categoryName,
			String categoryChild, String categorySub, int invetoryID,
			String stateName, boolean isSelect) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.totalPrice = totalPrice;
		this.amount = amount;
		this.limitDate = limitDate;
		this.categoryName = categoryName;
		this.categoryChild = categoryChild;
		this.categorySub = categorySub;
		this.invetoryID = invetoryID;
		this.stateName = stateName;
		this.isSelect = isSelect;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getLimitDate() {
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");

		return fo.format(limitDate);
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryChild() {
		return categoryChild;
	}

	public void setCategoryChild(String categoryChild) {
		this.categoryChild = categoryChild;
	}

	public String getCategorySub() {
		return categorySub;
	}

	public void setCategorySub(String categorySub) {
		this.categorySub = categorySub;
	}

	public int getInvetoryID() {
		return invetoryID;
	}

	public void setInvetoryID(int invetoryID) {
		this.invetoryID = invetoryID;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}
}
