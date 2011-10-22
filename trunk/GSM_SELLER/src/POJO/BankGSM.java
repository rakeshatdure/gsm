package POJO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="bankGSM")
@SessionScoped
public class BankGSM {
	private String bankName;

	public BankGSM() {}

	public BankGSM(String bankName){
		this.bankName = bankName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
