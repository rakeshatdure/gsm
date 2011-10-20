package BUS;

import java.util.List;
import java.util.Set;

import DAO.DeliverDAO;
import DAO.HibernateDAO;
import POJO.Deliver;
import POJO.DeliverView;
import POJO.HomeDeliverView;

public class DeliverBUS {
	public static List<Deliver> getListDeliver(String lang) {
		return DeliverDAO.getListDeliver(lang);
	}

	public static Deliver getDeliverByID(int id, String lang) {
		return DeliverDAO.getDeliver(id, lang);
	}
	public static void update(Deliver deliver,String lang){
		DeliverDAO.update(deliver, lang);
	}
	public static boolean insert(Deliver deliver, String lang) {
		return DeliverDAO.insert(deliver, lang);
	}
	public static void updateDeliverstatus(int id,int newStatus,String lang){
		DeliverDAO.updateDeliverstatus(id, newStatus, lang);
	}
	
	public static Object[] listDeliversByWhere(String where,
			String AcountSeller,int currentPage,int pageSize, String lang) {
		return DeliverDAO.listDeliversByWhere(where, AcountSeller,currentPage,pageSize, lang);
	}
	public static List<HomeDeliverView> getListHomeDeliverView(String seller,String where,String lang){
		return DeliverDAO.getListHomeDeliverView(seller, where, lang);
	}
	
	public static List<HomeDeliverView> getListHomeDeliverViewSearch(String seller,String from,String to,String lang){
		return DeliverDAO.getListHomeDeliverViewSearch( seller, from, to, lang);
	}
	
	public static Deliver getDeliverByPODetail(int pODetailID,String lang){
		return DeliverDAO.getDeliverByPODetail(pODetailID, lang);
	}
	
	public static void main(String[] args) {
		updateDeliverstatus(1, 5,"MALL_EN");
	}
}
