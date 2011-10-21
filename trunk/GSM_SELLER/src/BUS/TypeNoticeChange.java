package BUS;

import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.HibernateDAO;
import POJO.Typenotice;

@ManagedBean (name = "typenoticebean")
@SessionScoped
public class TypeNoticeChange extends HibernateDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4932766975326325740L;

	private String idTypeNotice; 
	private String nameTypeNotice;
	
	public TypeNoticeChange() {
		// TODO Auto-generated constructor stub
	}
	
	public void getAllNotice() throws IOException {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		ss.setAttribute("notice", "");
		HttpServletRequest hrl = (HttpServletRequest) f.getExternalContext()
		.getRequest();
		f.getExternalContext().redirect(hrl.getRequestURL().toString());
	}
	
	public void getNotice1() throws IOException {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		ss.setAttribute("notice", "1");
		/*HttpServletRequest hrl = (HttpServletRequest) f.getExternalContext()
		.getRequest();
		f.getExternalContext().redirect(hrl.getRequestURL().toString());*/
	}
	
	public void getNotice2() throws IOException {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		ss.setAttribute("notice", "2");
	}
	
	public void getNotice3() throws IOException {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		ss.setAttribute("notice", "3");
	}
	
	public void getNotice4() throws IOException {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		ss.setAttribute("notice", "4");
	}
	
	public void getNotice5() throws IOException {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession) f.getExternalContext().getSession(true);
		ss.setAttribute("notice", "5");
	}
	
	
	public String getIdTypeNotice() {
		return idTypeNotice;
	}
	public void setIdTypeNotice(String idTypeNotice) {
		this.idTypeNotice = idTypeNotice;
	}

	public String getNameTypeNotice() {
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession)f.getExternalContext().getSession(true);
		if(ss.getAttribute("notice") == null || ss.getAttribute("notice") == ""){
			nameTypeNotice = "Tất cả thông báo";
		} else {
			List<Typenotice> lstTn = HibernateDAO.getList("from Typenotice tn where tn.typeNoticeId = " + ss.getAttribute("notice").toString(), "MALL_VN");
			for(Typenotice t : lstTn){
				nameTypeNotice = t.getNameTypeNotice();
			}
		}
		return nameTypeNotice;
	}

	public void setNameTypeNotice(String nameTypeNotice) {
		this.nameTypeNotice = nameTypeNotice;
	}

}
