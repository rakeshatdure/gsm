package BEAN;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import BUS.BankBUS;
import DAO.BankDAO;
import DAO.CompanyDAO;
import POJO.Bank;
import POJO.Company;
import POJO.User;

@ManagedBean(name="bankBean")
@SessionScoped
public class BankBean {
	@SuppressWarnings("unused")
	private List<String> allListBankName;
	private String bankName;
	private String accountNumber;

	public String getBankName() {
		return loadBankName();
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return loadAccountNumber();
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public List<String> getAllListBankName() {
		return listBankName();
	}
	public void setAllListBankName(List<String> allListBankName) {
		this.allListBankName = allListBankName;
	}

	// viet phuong thuc load tat ca bank name co trong database
	public List<String> listBankName(){
		return BankDAO.lstBankName("MALL_EN");
	}


	// load bank name theo account name
	public String loadBankName(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		return BankDAO.getBankName(user.getAccount(), "MALL_EN");
	}

	// load accountNumber by accountName and bankName
	public String loadAccountNumber(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		Bank bk = BankBUS.getBank(user.getAccount(),"MALL_EN");

			return BankDAO.getAccountNumber(user.getAccount(),"MALL_EN");
	}

	// update bankName, AccountNumber
	public void changeBankAccount(){
		BankDAO dao = new BankDAO();
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		Bank bank = new Bank();
		bank.setUser(user);
		bank.setBanking(bankName);
		bank.setAccountNumber(accountNumber);
		
		System.out.println("Bank Name : " + bankName);
		System.out.println("Account Number : " + accountNumber);
		
		dao.updateBank(bankName, accountNumber, "MALL_EN",user.getAccount());
	}
	
	
}
