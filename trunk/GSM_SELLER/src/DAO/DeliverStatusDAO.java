package DAO;

import java.util.List;

import POJO.Deliverstatus;
import POJO.Productorderdetail;

public class DeliverStatusDAO extends HibernateDAO {
	
	public static List<Deliverstatus> getList(String lang){
	return HibernateDAO.getList("from Deliverstatus order by deliverStatusId", lang);
	
	
}
	public static Deliverstatus getDeliverstatus(int id,String lang){
		return (Deliverstatus) HibernateDAO.getObject(Deliverstatus.class, id, lang);
	}
	public static void update(Deliverstatus Deliverstatus,String lang){
		HibernateDAO.update(Deliverstatus, lang);
	}
	
	public static Deliverstatus getByStatus(String newStatus,String lang){
		List<Deliverstatus> list=HibernateDAO.getList("from Deliverstatus where deliverStatus='"+newStatus+"'" , lang);
		if(list!=null){
			return list.get(0);
			
		}return null;
	}
	
	public static void main(String[] args) {
		int id=1;
    	String lang="MALL_EN";
    	Deliverstatus pod=(Deliverstatus) getObject(Deliverstatus.class, id, lang);
		update(pod, lang);
	}
}
