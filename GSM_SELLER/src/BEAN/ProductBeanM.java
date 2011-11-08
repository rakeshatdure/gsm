package BEAN;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.richfaces.component.UIExtendedDataTable;
import org.richfaces.model.Filter;

import BUS.CategoryBUS;
import BUS.CategoryChildBUS;
import BUS.CategorySubBUS;
import BUS.DayBUS;
import BUS.ProductBUS;
import DAO.InventoryDAO;
import POJO.Category;
import POJO.CategoryChild;
import POJO.CategorySub;
import POJO.Day;
import POJO.Inventory;
import POJO.ProductInvetoryView;
import POJO.Products;
import POJO.User;
import POJO.View.ProductsView;
import UTIL.MySqlDataAccessHelper;

@SuppressWarnings("unused")
@ManagedBean(name = "productBeanM")
@SessionScoped
public class ProductBeanM {
	private String lang;
	private String productId;
	private String where = "";
	private int amount;
	private float price;
	private int amountNew;
	private float priceNew;
	private int idProCurrent;
	private int dayId;
	private int time;
	private String calend;
	private ProductsView productsView = new ProductsView();
	public Products currentProduct = new Products();

	private List<ProductsView> allProductView = null;
	private List<Day> allDay;
	private List<Products> allProduct;
	private List<ProductInvetoryView> allProductInventory;
	private Collection<Object> selection;
	private List<ProductsView> selectionItems = new ArrayList<ProductsView>();
	private List<ProductsView> allProductSelling;


	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	public List<ProductsView> getSelectionItems() {
		return selectionItems;
	}

	public void setSelectionItems(List<ProductsView> selectionItems) {
		this.selectionItems = selectionItems;
	}

	public Products getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(Products currentProduct) {
		this.currentProduct = currentProduct;
	}

	public String getLang() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		lang = (String) ss.getAttribute("MALL_LA");
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public List<Products> getAllProduct() {
		// return getListAllProduct(getWhere());
		return allProduct;
	}

	public void setAllProduct(List<Products> allProduct) {
		this.allProduct = allProduct;
	}

	public List<ProductsView> getAllProductView() {
		if (allProductView != null) {
			return allProductView;
		} else {
			return getListAllProduct();
		}
	}

	public void setAllProductView(List<ProductsView> allProductView) {
		this.allProductView = allProductView;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public float getPrice() {
		return currentProduct.getPrice();
	}

	public void setPrice(float price) {
		currentProduct.setPrice(price);
	}

	public int getAmount() {
		return currentProduct.getAmount();
	}

	public void setAmount(int amount) {
		currentProduct.setAmount(amount);
	}

	public List<ProductInvetoryView> getAllProductInventory() {
		return getListAllProductInventory();
	}

	public void setAllProductInventory(
			List<ProductInvetoryView> allProductInventory) {
		this.allProductInventory = allProductInventory;
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

	public List<Day> getAllDay() {
		return getListAllDay();
	}

	public void setAllDay(List<Day> allDay) {
		this.allDay = allDay;
	}

	// load all day
	public List<Day> getListAllDay() {
		return DayBUS.lstDay(lang);
	}

	public int loadDay() {
		return DayBUS.loadDayById(lang, dayId);
	}

	public String getCalend() {
		return getCalendarByID();
	}

	public void setCalend(String calend) {
		this.calend = calend;
	}

	public void setAmountNew(int amountNew) {
		this.amountNew = amountNew;
	}

	public int getAmountNew() {
		return amountNew;
	}

	public void setPriceNew(float priceNew) {
		this.priceNew = priceNew;
	}

	public float getPriceNew() {
		return priceNew;
	}

	public void change(ValueChangeEvent e) {
		setPriceNew(Float.parseFloat(e.getNewValue().toString()));
	}

	public void changeAmount(ValueChangeEvent event) {
		setAmountNew(Integer.parseInt(event.getNewValue().toString()));
	}

	public void setIdProCurrent(int idProCurrent) {
		this.idProCurrent = idProCurrent;
	}

	public int getIdProCurrent() {
		return idProCurrent;
	}

	public void getAllProductSelling() {
		lstAllProductSelling();
	}

	public void setAllProductSelling(List<ProductsView> allProductSelling) {
		this.allProductSelling = allProductSelling;
	}

	public ProductsView getProductsView() {
		return productsView;
	}
	public void setProductsView(ProductsView productsView) {
		this.productsView = productsView;
	}

	public void selectionListener(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
		.getComponent();
		Object originalKey = dataTable.getRowKey();
		selectionItems.clear();
		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				selectionItems.add((ProductsView) dataTable.getRowData());
			}
		}
		dataTable.setRowKey(originalKey);
		for (ProductsView p : selectionItems) {
			setIdProCurrent(p.getProductId());
		}
	}

	// load all product by user
	public List<ProductsView> getListAllProduct() {
		return ProductBUS.getAllProductByUser(getUser(), lang);
	}

	// update price and amount
	public void editPriceAndAmount() throws ParseException {
		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
		// parse the string into Date object
		Date date1 = sdfSource.parse(getCalend());
		// create SimpleDateFormat object with desired date format
		SimpleDateFormat sdfDestination1 = new SimpleDateFormat("yyyy-MM-dd");
		// parse the date into another format
		String cf_create1 = sdfDestination1.format(date1);

		ProductBUS.updateProductByUser(priceNew, amountNew, lang,
				getUser(), getIdProCurrent(), cf_create1);
	}

	// load all product invetory by user
	public List<ProductInvetoryView> getListAllProductInventory() {
		return ProductBUS.lstProductInvetory(getUser(), lang);
	}

	public String getCalendarByID() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, getTime());
		return calendar.get(Calendar.YEAR) + "-"
		+ (calendar.get(Calendar.MONTH) + 1) + "-"
		+ calendar.get(Calendar.DATE);
	}
	

	// update expired Date
	public void updateLimitDate() throws ParseException {
		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
		// parse the string into Date object
		Date date1 = sdfSource.parse(getCalend());
		// create SimpleDateFormat object with desired date format
		SimpleDateFormat sdfDestination1 = new SimpleDateFormat("yyyy-MM-dd");
		// parse the date into another format
		String cf_create1 = sdfDestination1.format(date1);
		InventoryDAO.updateInvetoryByProductId(cf_create1, lang,
				getUser(), getIdProCurrent());
	}

	// delete product(reUpdate stateInventory)
	public void updateStateInvetory() throws ParseException {
		InventoryDAO.updateStateInvetoryByProductId(lang, getUser(),
				getIdProCurrent());
	}

	// load all product selling(conditon: stateName ="OutputSto")
	public void lstAllProductSelling() {
		List<ProductsView> r = ProductBUS.getAllProductSellingByUser(
				getUser(), lang);
		if (r != null) {
			setAllProductView(r);
		}
	}
	// reload all product after submit ChooseAll button
	public void lstAllProduct(){
		List<ProductsView> r = ProductBUS.getAllProductByUser(
				getUser(), lang);
		if (r != null) {
			setAllProductView(r);
		}
	}
	// Check Select
	public void checkSelect(AjaxBehaviorEvent event){
		try {
			ProductsView prView = (ProductsView) event.getComponent().getAttributes().get("partObject");
			productsView = prView;
			if(productsView.isSelect() == false){
				for (int i = 0; i < allProductView.size(); i++) {
					allProductView.get(i).setSelect(false);
					productsView = new ProductsView();
				}
			}else{
				for (int i = 0; i <  allProductView.size(); i++) {
					if(allProductView.get(i).getProductId() == prView.getProductId()){
						allProductView.get(i).setSelect(true);
						prView = allProductView.get(i);
					}
					else{
						allProductView.get(i).setSelect(false);
					}
				}
			}
		} catch (Exception e) {
		}
	}
	Inventory inventory = new Inventory();

	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	// update extension product
	public void UpdateExtensionProduct(ActionEvent event){
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		SimpleDateFormat simp=new SimpleDateFormat("yyyy-MM-dd");
		String currentDate=simp.format(new Date());
		System.out.println("Current Date + Extention date : " + getCalend());
		System.out.println("ProductId ===== " + productsView.getProductId());
		try {
			helper.open("MALL_LA");
			String query = "UPDATE inventory SET inventory.LimitDate='"+getCalend()+"' where inventory.ProductId = '"+productsView.getProductId()+"'";
			int result = helper.executeUpdate(query);
			if(result > 0){
				allProductView = null;
				getAllProductView();
			}
		} catch (Exception e) {
		}
	}
	
	List<Category> allCategory;
	Category category;

	private String categoryId;
	private String categoryChildId;
	private String categorySubId;
	private String user;

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		HttpSession ses = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(true);
		User user = (User) ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		return user.getAccount();
	}

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

	public List<Category> getAllCategoryDatabase(){
		return CategoryBUS.lstCategory(lang);
	}

	// load all category name by user
	public List<Category> getAllCategoryByUser(){
		return CategoryBUS.lstAllCategory(lang,getUser());
	}

	// load all categoryChild by user, and categoryName
	public List<CategoryChild> getListAllCategoryChild(){
		return CategoryChildBUS.lstAllCategoryChild(lang, categoryId,getUser());
	}

	// load categorySub by account, categogyName, categoryChild
	public List<CategorySub> getListAllCategorySub(){
		return CategorySubBUS.lstAllCategorySub(lang, categoryId, categoryChildId, getUser());
	}
	
	// search
	public void search() {
		String whereCategoryName =" and (SELECT cat.CategoryID  from category cat, categorychild catch" +
		" 	WHERE prs.CategoryChildId = catch.CategoryChildId and catch.CategoryId = cat.CategoryID) = '"+categoryId+"'";
		String whereCategoryChild=" AND (SELECT catch.CategoryChildId  from categorychild catch WHERE prs.CategoryChildId = catch.CategoryChildId ) = '"+categoryChildId+"'";
		String whereCategorySub =" AND (SELECT catsub.CategorySubId  from categorysub catsub WHERE prs.CategorySubId= catsub.CategorySubId) = '"+categorySubId+"'";
		if(categoryId !=null ){
			List<ProductsView> lst = ProductBUS.getAllProductByCategory(getUser(),lang,whereCategoryName);
				setAllProductView(lst);
				
				System.out.println("+++= CategoryChildId ++++ " + categoryChildId);
		}

	}
}
