package BEAN.VANHIEP;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.HibernateDAO;
import POJO.SellingMode;

@ManagedBean (name="sellmodeBean")
@SuppressWarnings("unchecked")
public class SellModeBean extends HibernateDAO{
	
	List<SellingMode> lSellMode;
	private String lang;
	
	public SellModeBean() {
	}
	
	public List<SellingMode> getlSellMode() {
		lSellMode = HibernateDAO.getList("from SellingMode sm", lang);
		return lSellMode;
	}

	public void setlSellMode(List<SellingMode> lSellMode) {
		this.lSellMode = lSellMode;
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
	
	

}
