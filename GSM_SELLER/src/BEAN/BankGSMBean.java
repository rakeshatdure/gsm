package BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DAO.BankGSMDAO;

@ManagedBean(name="bankGSMBean")
@SessionScoped
public class BankGSMBean {
	private List<String> allBankName;
	
	public List<String> getAllBankName() {
		return loadAllBankName();
	}
	public void setAllBankName(List<String> allBankName) {
		this.allBankName = allBankName;
	}
	// viet phuong thuc load tat ca cac bankName co trong database
	public List<String> loadAllBankName(){
		return BankGSMDAO.lstAllBankName("MALL_LA");
	}

}
