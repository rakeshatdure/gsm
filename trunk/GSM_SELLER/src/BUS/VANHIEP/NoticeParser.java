package BUS.VANHIEP;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/*import javax.faces.bean.ManagedProperty;*/

import DAO.HibernateDAO;
import POJO.Notice;
import POJO.NoticeView;
import POJO.Typenotice;

@ManagedBean (name="NoticeBean")
@ApplicationScoped
public class NoticeParser extends HibernateDAO {
	private List<NoticeView> listNotice;
	private String action = "";
	
	@SuppressWarnings("unchecked")
	public  List<NoticeView> getListNotice() {
		
		FacesContext f = FacesContext.getCurrentInstance();
		HttpSession ss = (HttpSession)f.getExternalContext().getSession(true);
		if(ss.getAttribute("notice") == null || ss.getAttribute("notice") == ""){
			action = "from Notice n";
		} else { 
			action = "from Notice n where n.typenotice = " + ss.getAttribute("notice").toString();
		}
		listNotice = new ArrayList<NoticeView>();
		List<Notice> lstNo = HibernateDAO.getList(action, "MALL_VN");
		int size = lstNo.size();
		for(int i = 0; i < size; i++)
		{
			Notice not = lstNo.get(i);
			NoticeView nv = new NoticeView();
			nv.setNoticeId(not.getNoticeId());
			nv.setDateNotice(not.getDateNotice());
			nv.setTitle(not.getTitle());
			nv.setContentNotice(not.getContentNotice());
			nv.setNameTypeNotice(getNameTypeNotice(not.getTypenotice()));
			listNotice.add(nv);
		}
		return listNotice;
	}
	
	@SuppressWarnings("unchecked")
	public String getNameTypeNotice(int id) {
		String nameType = "";
		List<Typenotice> lstTn = HibernateDAO.getList("from Typenotice tn where tn.typeNoticeId = " + id, "MALL_VN");
		for(Typenotice t : lstTn){
			nameType = t.getNameTypeNotice();
		}
		return nameType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setListNotice(List<NoticeView> listNotice) {
		this.listNotice = listNotice;
	}	
	
	
}
