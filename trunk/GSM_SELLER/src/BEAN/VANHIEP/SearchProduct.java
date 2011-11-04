package BEAN.VANHIEP;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.HibernateDAO;
import POJO.*;

@ManagedBean (name = "searchpro")
@SessionScoped
@SuppressWarnings("unchecked")
public class SearchProduct extends HibernateDAO {
	
	private List<Products> listAllProduct;
	//Open Market
	private List<Products> listProduct1; //danh sach san pham ton kho duoi 3 san pham
	private List<Products> listProduct2; //danh sach san pham trong 30 ngay man han
	//Especially price martket
	private List<Products> listProduct3; //danh sach san pham ton kho duoi 3 san pham
	private List<Products> listProduct4; //danh sach san pham trong 30 ngay man han
	//Focus
	private List<Products> listProduct5; //danh sach san pham focus
	private List<Products> listProduct6; //danh sach san pham focus+
	
	private int sizeListAllProduct;
	private int sizeListProduct1;
	private int sizeListProduct2;
	private int sizeListProduct3;
	private int sizeListProduct4;
	private int sizeListProduct5;
	private int sizeListProduct6;
	
	private String lang;
	private String user;
	
	private String ttgiaodich;
	private String sellMode = "";
	private String tinhTrang;
	
	private List<TransactionInfo> listTransInfo;
	
	private boolean onCheck = false;
	private boolean checkTTGD;
	private boolean checkPTB2 = true;
	private String searchWith;
	private Date fromDate;
	private Date toDate;
	
	private List<ProductAll> listProductSearch;
	
	private String showTable = "0";
	private String searchT;
	
	//Category
	private List<Category> lCat;
	private List<CategoryChild> lCatChild;
	private List<CategorySub> lCatSub; 
	private String categoryId;
	private String categoryChildId;
	private String categorySubId;
	
	private boolean onCC = true;
	private boolean onCS = true;
	
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

	public void setOnCheck(boolean onCheck) {
		this.onCheck = onCheck;
	}

	public boolean isOnCheck() {
		return onCheck;
	}

	public void setTtgiaodich(String ttgiaodich) {
		this.ttgiaodich = ttgiaodich;
	}

	public String getTtgiaodich() {
		return ttgiaodich;
	}

	public void setListTransInfo(List<TransactionInfo> listTransInfo) {
		this.listTransInfo = listTransInfo;
	}
	
	public List<TransactionInfo> getListTransInfo() {
		String sql="from TransactionInfo";
		listTransInfo = HibernateDAO.getList(sql, lang);
		return listTransInfo;
	}

	public void setSellMode(String sellMode) {
		this.sellMode = sellMode;
	}

	public String getSellMode() {
		return sellMode;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setCheckTTGD(boolean checkTTGD) {
		if(!onCheck){
			checkTTGD = false;
		} else {
			checkTTGD = true;
		}
		this.checkTTGD = checkTTGD;
	}

	public boolean isCheckTTGD() {
		if(!onCheck){
			checkTTGD = false;
		} else {
			checkTTGD = true;
		}
		return checkTTGD;
	}

	public void setCheckPTB2(boolean checkPTB2) {
		if(!onCheck){
			if(sellMode.equals("1") || sellMode.equals("2")) {
				checkPTB2 = true;
			}
		} else {
			checkPTB2 = false;
		}
		this.checkPTB2 = checkPTB2;
	}

	public boolean isCheckPTB2() {
		if(!onCheck){
			if(sellMode.equals("0") || sellMode.equals("1")) {
				checkPTB2 = false;
			}
			else{
				checkPTB2 = true;
			}
		} else {
			checkPTB2 = true;
		}
		return checkPTB2;
	}
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setSearchWith(String searchWith) {
		this.searchWith = searchWith;
	}

	public String getSearchWith() {
		return searchWith;
	}

	public void setSearchT(String searchT) {
		this.searchT = searchT;
	}

	public String getSearchT() {
		return searchT;
	}
	
	public SearchProduct() {
		setUser(getUser());
	}
	
	//Get list product in link size
	public List<Products> getListAllProduct() {
		String sql="from Products p where p.account = '" + user + "'";
		listAllProduct = HibernateDAO.getList(sql, lang);
		return listAllProduct;
	}

	public void setListAllProduct(List<Products> listAllProduct) {
		this.listAllProduct = listAllProduct;
	}
	
	public List<Products> getListProduct1() {
		String sql="from Products p where p.account = '" + user + "' and p.amount < 3 and p.marketId = 1";
		listProduct1 = HibernateDAO.getList(sql, lang);
		return listProduct1;
	}

	public void setListProduct1(List<Products> listProduct1) {
		this.listProduct1 = listProduct1;
	}
	
	public List<Products> getListProduct2() {
		String sql="from Products p where p.account = '" + user + "' and DATEDIFF(p.fromDate, p.toDate) < 30 and p.marketId = 1";
		listProduct2 = HibernateDAO.getList(sql, lang);
		return listProduct2;
	}

	public void setListProduct2(List<Products> listProduct2) {
		this.listProduct2 = listProduct2;
	}
	
	public List<Products> getListProduct3() {
		String sql="from Products p where p.account = '" + user + "' and p.amount < 3 and p.marketId = 2";
		listProduct3 = HibernateDAO.getList(sql, lang);
		return listProduct3;
	}

	public void setListProduct3(List<Products> listProduct3) {
		this.listProduct3 = listProduct3;
	}
	
	public List<Products> getListProduct4() {
		String sql="from Products p where p.account = '" + user + "' and DATEDIFF(p.fromDate, p.toDate) < 30 and p.marketId = 2";
		listProduct4 = HibernateDAO.getList(sql, lang);
		return listProduct4;
	}

	public void setListProduct4(List<Products> listProduct4) {
		this.listProduct4 = listProduct4;
	}
	
	public List<Products> getListProduct5() {
		String sql="from Products p where p.account = '" + user + "' and focusId != 0";
		listProduct5 = HibernateDAO.getList(sql, lang);
		return listProduct5;
	}

	public void setListProduct5(List<Products> listProduct5) {
		this.listProduct5 = listProduct5;
	}

	public List<Products> getListProduct6() {
		String sql="from Products p where p.account = '" + user + "' and focusOnId != 0";
		listProduct6 = HibernateDAO.getList(sql, lang);
		return listProduct6;
	}

	public void setListProduct6(List<Products> listProduct6) {
		this.listProduct6 = listProduct6;
	}

	//get User from session
	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		user = (String) ses.getAttribute("account");
		return user;
	}

	//get size of lists product to show display
	public int getSizeListAllProduct() {
		/*System.out.println("{{{{{{{{{{ Account la: " + getUser() + " }}}}}}}}}}}}}");
		System.out.println("{{{{{{{{{{ Language la: " + getLang() + " }}}}}}}}}}}}}");*/
		if(getListAllProduct() != null){
			sizeListAllProduct = getListAllProduct().size();
		} else {
			sizeListAllProduct = 0;
		}
		return sizeListAllProduct;
	}

	public void setSizeListAllProduct(int sizeListAllProduct) {
		this.sizeListAllProduct = sizeListAllProduct;
	}

	public int getSizeListProduct1() {
		if(getListProduct1() != null){
			sizeListProduct1 = getListProduct1().size();
		} else {
			sizeListProduct1 = 0;
		}
		return sizeListProduct1;
	}

	public void setSizeListProduct1(int sizeListProduct1) {
		this.sizeListProduct1 = sizeListProduct1;
	}

	public int getSizeListProduct2() {
		if(getListProduct2() != null){
			sizeListProduct2 = getListProduct2().size();
		} else {
			sizeListProduct2 = 0;
		}
		return sizeListProduct2;
	}

	public void setSizeListProduct2(int sizeListProduct2) {
		this.sizeListProduct2 = sizeListProduct2;
	}

	public int getSizeListProduct3() {
		if(getListProduct3() != null){
			sizeListProduct3 = getListProduct3().size();
		} else {
			sizeListProduct3 = 0;
		}
		return sizeListProduct3;
	}

	public void setSizeListProduct3(int sizeListProduct3) {
		this.sizeListProduct3 = sizeListProduct3;
	}

	public int getSizeListProduct4() {
		if(getListProduct4() != null){
			sizeListProduct4 = getListProduct4().size();
		} else {
			sizeListProduct4 = 0;
		}
		return sizeListProduct4;
	}

	public void setSizeListProduct4(int sizeListProduct4) {
		this.sizeListProduct4 = sizeListProduct4;
	}

	public int getSizeListProduct5() {
		if(getListProduct5() != null){
			sizeListProduct5 = getListProduct5().size();
		} else {
			sizeListProduct5 = 0;
		}
		return sizeListProduct5;
	}

	public void setSizeListProduct5(int sizeListProduct5) {
		this.sizeListProduct5 = sizeListProduct5;
	}

	public int getSizeListProduct6() {
		if(getListProduct6() != null){
			sizeListProduct6 = getListProduct6().size();
		} else {
			sizeListProduct6 = 0;
		}
		return sizeListProduct6;
	}

	public void setSizeListProduct6(int sizeListProduct6) {
		this.sizeListProduct6 = sizeListProduct6;
	}

	public List<ProductAll> getListProductSearch() {
		listProductSearch = new ArrayList<ProductAll>();
		String sql = "from Products";
		if(showTable.equals("0")){
			sql = "from Products";
		}
		if(showTable.equals("1")){
			sql = "from Products p where p.account = '" + user + "' and p.amount < 3 and p.marketId = 1";
		}
		if(showTable.equals("2")){
			sql = "from Products p where p.account = '" + user + "' and DATEDIFF(p.fromDate, p.toDate) < 30 and p.marketId = 1";
		}
		if(showTable.equals("3")){
			sql = "from Products p where p.account = '" + user + "' and p.amount < 3 and p.marketId = 2";
		}
		if(showTable.equals("4")){
			sql = "from Products p where p.account = '" + user + "' and DATEDIFF(p.fromDate, p.toDate) < 30 and p.marketId = 2";
		}
		if(showTable.equals("5")){
			sql = "from Products p where p.account = '" + user + "' and focusId != 0";
		}
		if(showTable.equals("6")){
			sql = "from Products p where p.account = '" + user + "' and focusOnId != 0";
		}
		if(showTable.equals("7")){
			sql = searchProduct();
		}
		
		List<Products> lpro = HibernateDAO.getList(sql, lang);
		for (Products p : lpro) {
			ProductAll pa = new ProductAll();
			pa.setProductId(p.getProductId());
			pa.setAccount(p.getAccount());
			pa.setCategory(getCategotyS(p.getCategoryChildId()));
			pa.setCategoryChild(getCategoryChildS(p.getCategoryChildId()));
			pa.setCategorySub(getCategotySubS(p.getCategorySubId()));
			pa.setProductName(p.getProductName());
			pa.setManufacturerId(getManufacturer(p.getManufacturerId()));
			pa.setUnit(p.getUnit());
			pa.setProductType(getProductType(p.getProductTypeId()));
			pa.setMarket(getMarket(p.getMarketId()));
			pa.setFocus(getFocus(p.getFocusId()));
			pa.setFocusOn(getFocusOn(p.getFocusOnId()));
			pa.setUploadDate(p.getUploadDate());
			pa.setOrigin(getOrigin(p.getOriginId()));
			pa.setAmount(p.getAmount());
			pa.setPrice(p.getPrice());
			pa.setTransactionInfo(getTras(p.getTransactionInfoId()));
			listProductSearch.add(pa);
		}
		return listProductSearch;
	}

	
	private String searchProduct() {
		String excute = "from Products p where p.account = '" + user + "'";
		String sCategoryChild = "";
		String sCategorySub = "";
		String date = "";
		String ttgd = "";
		String sellM = "";
		String amount = "";
		String searchText = "";
		
		Format format = new SimpleDateFormat("yyyy/MM/dd");
		
		//SET CATEGORY
		if(categoryChildId != ""){
			sCategoryChild = " and p.categoryChildId = '" + categoryChildId + "'";
		}
		if(categorySubId != ""){
			sCategorySub = " and p.categorySubId = '" + categorySubId + "'";
		}
		
		//SET DATE
		if(fromDate != null){
			String fdate = format.format(fromDate);
			if(toDate != null){
				String tdate = format.format(toDate);
				date = " and p.uploadDate between '"+fdate+"' and '"+tdate+"'";
			} else{
				date = " and p.uploadDate > '"+fdate+"'";
			}
		} else {
			if(toDate != null){
				String tdate = format.format(toDate);
				date = " and p.uploadDate < '"+tdate+"'";
			}
		}
		
		//SET TRANSACTION
		if(!checkTTGD){
			ttgd = " and p.transactionInfoId = '" + ttgiaodich + "'";
		}
		
		//SET SELL MODE
		if(sellMode != ""){
			sellM =  " and p.sellingModeId = '"+sellMode+"'";
		}
		
		//SET AMOUNT OF PRODUCT
		if(!checkPTB2){
			if(tinhTrang.equals("0")){
				amount = " and p.amount > 0";
			} else {
				if(tinhTrang.equals("1")){
					amount = " and p.amount = 0";
				}
				
			}
		}
		
		//SET SEARCH WITH PRODUCTNAME OR ACCOUNT
		if(searchT != ""){
			if(searchWith.equals("1")){
				searchText = " and MATCH(p.productName) AGAINST('"+ searchT +"')";
			}
			if(searchWith.equals("2")){
				searchText = " and MATCH(p.account) AGAINST('"+ searchT +"')";
			}
		}
		
		excute += sCategoryChild + sCategorySub + date + ttgd + sellM + amount + searchText;
		
		System.out.println("Query String: ["+excute+"]");
		
		return excute;
	}

	public String getShowTable() {
		return showTable;
	}

	public void setShowTable(String showTable) {
		this.showTable = showTable;
	}

	private String getTras(int transactionInfoId) {
		String name = "";
		List<TransactionInfo> lst = HibernateDAO.getList("from TransactionInfo tn where tn.transactionInfoId = " + transactionInfoId, lang);
		for(TransactionInfo t : lst){
			name = t.getTransactionInfo();
		}
		return name;
	}

	private String getOrigin(int originId) {
		String name = "";
		List<Origin> lst = HibernateDAO.getList("from Origin tn where tn.originId = " + originId, lang);
		for(Origin t : lst){
			name = t.getOriginName();
		}
		return name;
	}

	private String getFocusOn(int focusOnId) {
		String name = "";
		List<FocusOn> lst = HibernateDAO.getList("from FocusOn tn where tn.focusOnId = " + focusOnId, lang);
		for(FocusOn t : lst){
			name = t.getNameFocusOn();
		}
		return name;
	}

	private String getFocus(int focusId) {
		String name = "";
		List<Focus> lst = HibernateDAO.getList("from Focus tn where tn.focusId = " + focusId, lang);
		for(Focus t : lst){
			name = t.getNameFocus();
		}
		return name;
	}

	private String getMarket(int marketId) {
		String name = "";
		List<market> lst = HibernateDAO.getList("from market tn where tn.marketId = " + marketId, lang);
		for(market t : lst){
			name = t.getNameMarket();
		}
		return name;
	}

	private String getProductType(int productTypeId) {
		String name = "";
		List<Producttype> lst = HibernateDAO.getList("from Producttype tn where tn.productTypeId = " + productTypeId, lang);
		for(Producttype t : lst){
			name = t.getProductTypeName();
		}
		return name;
	}

	private String getManufacturer(int manufacturerId) {
		String name = "";
		List<Manufacturer> lst = HibernateDAO.getList("from Manufacturer tn where tn.manufacturerId = " + manufacturerId, lang);
		for(Manufacturer t : lst){
			name = t.getManufacturerName();
		}
		return name;
	}

	private String getCategotySubS(String categorySubId) {
		String name = "";
		List<CategorySub> lst = HibernateDAO.getList("from CategorySub tn where tn.categorySubId = '" + categorySubId + "'", lang);
		for(CategorySub t : lst){
			name = t.getCategorySubName();
		}
		return name;
	}

	private String getCategotyS(String categoryChildId) {
		String name = "";
		List<CategoryChild> lst = HibernateDAO.getList("from CategoryChild tn where tn.categoryChildId = '" + categoryChildId + "'", lang);
		for(CategoryChild t : lst){
			List<Category> lstC = HibernateDAO.getList("from Category tn where tn.categoryId = '" + t.getCategoryId() + "'", lang);
			for (Category category : lstC) {
				name = category.getCategoryName();
			}
		}
		return name;
	}

	private String getCategoryChildS(String categoryChildId) {
		String name = "";
		List<CategoryChild> lst = HibernateDAO.getList("from CategoryChild tn where tn.categoryChildId = '" + categoryChildId + "'", lang);
		for(CategoryChild t : lst){
			name = t.getCategoryChildName();
		}
		return name;
	}
	

}
