package DAO;

import POJO.Invenstate;
import POJO.Inventory;

public class InventoryStateDAO  extends HibernateDAO{
	
	 public static Invenstate getInvenState(int id,String lang){
		 return (Invenstate)HibernateDAO.getObject(Invenstate.class, id, lang);
	 }
	 
	 
}
