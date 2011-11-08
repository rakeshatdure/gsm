package BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.BankGSMDAO;

@ManagedBean(name="bankGSMBean")
@SessionScoped
public class BankGSMBean {
	private List<String> allBankName;
	private String lang;
	
	
	public String getLang() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		lang = (String) ss.getAttribute("MALL_LA");
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	public List<String> getAllBankName() {
		return loadAllBankName();
	}
	public void setAllBankName(List<String> allBankName) {
		this.allBankName = allBankName;
	}
	// viet phuong thuc load tat ca cac bankName co trong database
	public List<String> loadAllBankName(){
		return BankGSMDAO.lstAllBankName(lang);
	}

}
