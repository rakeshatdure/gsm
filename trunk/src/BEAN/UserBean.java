package BEAN;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import BUS.UserBUS;
import DAO.UserDAO;
import POJO.User;
import UTIL.VuHong_MD5;


@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
	private String oldPass="",newPass="",rePass="";

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getRePass() {
		return rePass;
	}

	public void setRePass(String rePass) {
		this.rePass = rePass;
	}
	public void changePass(){
		if(valid()){
			UserDAO dao = new UserDAO();
			HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			User user = (User)ses.getAttribute("uselogin");
			user.setPass(VuHong_MD5.encrypt(newPass));
			ses.setAttribute("uselogin", user);
			
			dao.updatePass(newPass,user.getAccount());
		}
	}
	public boolean valid(){
		boolean flag = true;
		if(!checkSame()){
			FacesContext.getCurrentInstance().addMessage("frmPass",new FacesMessage( "old password and new password are diference"));
			flag = false;
		}
		if(!checkOld()){
			FacesContext.getCurrentInstance().addMessage("frmPass",new FacesMessage( "invalid"));
			flag = false;
		}
		if(newPass.trim().length() <1 || newPass.trim().length() > 20){
			FacesContext.getCurrentInstance().addMessage("frmPass",new FacesMessage( "password maximum is 20 character"));
			flag = false;
		}
		return flag;
	}
	public boolean checkOld(){
		HttpSession ses = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return VuHong_MD5.encrypt(getOldPass()).equals(((User)ses.getAttribute("uselogin")).getPass());
	}
	public boolean checkSame(){
		return newPass.equals(rePass);
	}
}
