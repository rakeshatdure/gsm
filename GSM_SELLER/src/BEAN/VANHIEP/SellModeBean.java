package BEAN.VANHIEP;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.HibernateDAO;
import POJO.SellingMode;

@ManagedBean (name="sellmodeBean")
public class SellModeBean extends HibernateDAO{
	
	List<SellingMode> lSellMode;
	private String lang;
	
	public SellModeBean() {
	}

	@SuppressWarnings("unchecked")
	public List<SellingMode> getlSellMode() {
		lSellMode = HibernateDAO.getList("select sm.SellingModeId, sm.SellingMode from SellingMode sm", lang);
		System.out.println("[[[[[[[[[[[[[[[[ So luong trong list: " + lSellMode.size());
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
