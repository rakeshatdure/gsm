package POJO;

import java.io.Serializable;
import java.util.Date;

public class ProductAll implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3442413139699829331L;
	
	private Integer productId;
	private String account;
	private String category;
	private String categoryChild;
    private String categorySub;
    private String productName;
    private String manufacturerId;
    private String unit;
    private String productType;
    private String market;
    private String focus;
    private String focusOn;
    private Date uploadDate;
    private String origin;
    private int amount;
    private float price;
    private String transactionInfo;
    
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getFocus() {
		return focus;
	}
	public void setFocus(String focus) {
		this.focus = focus;
	}
	public String getFocusOn() {
		return focusOn;
	}
	public void setFocusOn(String focusOn) {
		this.focusOn = focusOn;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(String transactionInfo) {
		this.transactionInfo = transactionInfo;
	}
	
	

}
