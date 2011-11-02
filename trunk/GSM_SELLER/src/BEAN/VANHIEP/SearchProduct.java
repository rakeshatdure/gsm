package BEAN.VANHIEP;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.HibernateDAO;
import POJO.*;

@ManagedBean (name = "searchpro")
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
	
	private boolean onCheck = false;
	
	public SearchProduct() {
		setUser(getUser());
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLang() {
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		lang = (String) ses.getAttribute("MALL_LA");
		return lang;
	}

	
	@SuppressWarnings("unchecked")
	public List<Products> getListAllProduct() {
		String sql="from Products p where p.account = '" + user + "'";
		System.out.println("[[[[[[[from Products p where p.account = '" + user + "']]]]]]]");
		listAllProduct = HibernateDAO.getList(sql, lang);
		return listAllProduct;
	}

	public void setListAllProduct(List<Products> listAllProduct) {
		this.listAllProduct = listAllProduct;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getListProduct1() {
		String sql="from Products p where p.account = '" + user + "' and p.amount < 3 and p.marketId = 1";
		listProduct1 = HibernateDAO.getList(sql, lang);
		return listProduct1;
	}

	public void setListProduct1(List<Products> listProduct1) {
		this.listProduct1 = listProduct1;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getListProduct2() {
		String sql="from Products p where p.account = '" + user + "' and DATEDIFF(p.fromDate, p.toDate) < 30 and p.marketId = 1";
		listProduct2 = HibernateDAO.getList(sql, lang);
		return listProduct2;
	}

	public void setListProduct2(List<Products> listProduct2) {
		this.listProduct2 = listProduct2;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getListProduct3() {
		String sql="from Products p where p.account = '" + user + "' and p.amount < 3 and p.marketId = 2";
		listProduct3 = HibernateDAO.getList(sql, lang);
		return listProduct3;
	}

	public void setListProduct3(List<Products> listProduct3) {
		this.listProduct3 = listProduct3;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getListProduct4() {
		String sql="from Products p where p.account = '" + user + "' and DATEDIFF(p.fromDate, p.toDate) < 30 and p.marketId = 2";
		listProduct4 = HibernateDAO.getList(sql, lang);
		return listProduct4;
	}

	public void setListProduct4(List<Products> listProduct4) {
		this.listProduct4 = listProduct4;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getListProduct5() {
		String sql="from Products p where p.account = '" + user + "' and focusId != 0";
		listProduct5 = HibernateDAO.getList(sql, lang);
		return listProduct5;
	}

	public void setListProduct5(List<Products> listProduct5) {
		this.listProduct5 = listProduct5;
	}

	@SuppressWarnings("unchecked")
	public List<Products> getListProduct6() {
		String sql="from Products p where p.account = '" + user + "' and focusOnId != 0";
		listProduct6 = HibernateDAO.getList(sql, lang);
		return listProduct6;
	}

	public void setListProduct6(List<Products> listProduct6) {
		this.listProduct6 = listProduct6;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		user = (String) ses.getAttribute("account");
		return user;
	}

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

	public void setOnCheck(boolean onCheck) {
		this.onCheck = onCheck;
	}

	public boolean isOnCheck() {
		return onCheck;
	}

	public boolean checkTTGD() {
		if(!onCheck){
			return true;
		}
		return false;
	}
	
	public boolean checkPTB1() {
		if(onCheck){
			return true;
		}
		return false;
	}
	public boolean checkPTB2() {
		if(!onCheck){
			return true;
		}
		return false;
	}
	
	
}
