package BEAN;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import BUS.CompanyBUS;
import DAO.CompanyDAO;
import POJO.Company;
import POJO.User;

@ManagedBean(name="companyBean")
@SessionScoped
public class CompanyBean {
	private String companyName;
	private String reprentation;
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getReprentation() {
		return reprentation;
	}

	public void setReprentation(String reprentation) {
		this.reprentation = reprentation;
	}


	// viet phuong thuc thay doi thong tin nguoi dai dien va ten cong ty _ menu2_11
	public void changeRepresentativeAndCompName(){
		CompanyDAO dao = new CompanyDAO();
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		User user = (User)ses.getAttribute("uselogin");
		ses.setAttribute("uselogin", user);
		Company comp = new Company();
		comp.setUser(user);
		comp.setCompanyName(companyName);
		comp.setRepresentative(reprentation);
		
		dao.updateCompany(companyName, reprentation, "MALL_LA",user.getAccount());
	}
	
	

}
