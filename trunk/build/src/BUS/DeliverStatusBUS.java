package BUS;

import java.util.List;

import DAO.DeliverDAO;
import DAO.DeliverStatusDAO;
import POJO.Deliverstatus;

public class DeliverStatusBUS {

	public static Deliverstatus getDeliverStatus(int id,String lang){
		return DeliverStatusDAO.getDeliverstatus(id, lang);
	}
	
	public static List<Deliverstatus> getList(String lang) {		
		return DeliverStatusDAO.getList(lang);
	}
	public static void update(Deliverstatus Deliverstatus, String lang) {
		DeliverStatusDAO.update(Deliverstatus, lang);
	}
	
	public static Deliverstatus getByStatus(String newStatus,String lang){
		return DeliverStatusDAO.getByStatus(newStatus, lang);
	}
}
