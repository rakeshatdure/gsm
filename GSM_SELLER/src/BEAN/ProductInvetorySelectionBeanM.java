package BEAN;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpSession;

import org.richfaces.component.UIExtendedDataTable;

import BUS.DayBUS;
import DAO.InventoryDAO;
import POJO.Day;
import POJO.ProductInvetoryView;
import POJO.Products;
import POJO.User;

@ManagedBean(name="productInvetorySelectionBeanM")
@SessionScoped
public class ProductInvetorySelectionBeanM {
	private static final long serialVersionUID = -619881878045300680L;

	private Collection<Object> selection;
	@ManagedProperty(value = "#{productBeanM.allProductInventory}")
	private List<ProductInvetoryView> allProductInventory;
	private List<ProductInvetoryView> selectionItems = new ArrayList<ProductInvetoryView>();
	private int proId;
	private int dayId;
	private int time;
	private List<Day> allDay;
	private String calend;
	private String lang;


	public void selectionListener(AjaxBehaviorEvent event) {
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
		Object originalKey = dataTable.getRowKey();
		selectionItems.clear();
		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {
				selectionItems.add((ProductInvetoryView) dataTable.getRowData());
			}
		}
		dataTable.setRowKey(originalKey);
		for(ProductInvetoryView prs : selectionItems){
			setProId(prs.getProductCode());
		}
	}

	public Collection<Object> getSelection() {
		return selection;
	}
	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}
	public List<ProductInvetoryView> getAllProductInventory() {
		return allProductInventory;
	}
	public List<ProductInvetoryView> getSelectionItems() {
		return selectionItems;
	}
	public void setAllProductInventory(List<ProductInvetoryView> allProductInventory) {
		this.allProductInventory = allProductInventory;
	}
	public void setSelectionItems(List<ProductInvetoryView> selectionItems) {
		this.selectionItems = selectionItems;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getProId() {
		return proId;
	}

	public String getCalend() {
		return getCalendarByID();
	}
	public void setCalend(String calend) {
		this.calend = calend;
	}
	public int getTime() {
		return loadDay();
	}
	public void setTime(int time) {
		this.time = time;
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

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

	public int getDayId() {
		return dayId;
	}

	public void setAllDay(List<Day> allDay) {
		this.allDay = allDay;
	}

	public List<Day> getAllDay() {
		return getListAllDay();
	}
	public int loadDay(){
		System.out.println("Day Id : " + dayId);
		return DayBUS.loadDayById(lang, dayId);
	}
	// load all day
	public List<Day> getListAllDay(){
		return DayBUS.lstDay(lang);
	}
	public void updateLimitDate() throws ParseException{
		HttpSession ses = (HttpSession) FacesContext.getCurrentInstance()
		.getExternalContext().getSession(true);
		User user = (User) ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);

		SimpleDateFormat sdfSource = new SimpleDateFormat("yyyy-MM-dd");
		//parse the string into Date object
		Date date1 = sdfSource.parse(getCalend());
		//create SimpleDateFormat object with desired date format
		SimpleDateFormat sdfDestination1 = new SimpleDateFormat("yyyy-MM-dd");
		//parse the date into another format
		String cf_create1 = sdfDestination1.format(date1);

		System.out.println("ProductID : " + getProId());
		System.out.println("date time : " + cf_create1);
		InventoryDAO.updateInvetoryByProductId(cf_create1, lang, user.getAccount(),getProId());
	}
	public String getCalendarByID(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE,getTime());
		return calendar.get(Calendar.YEAR) +  "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
	}
}
