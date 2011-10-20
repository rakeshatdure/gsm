package DAO;

import java.util.List;

import org.hibernate.Hibernate;

import POJO.Confirm;

public class ConfirmDAO extends HibernateDAO{
//get list confirm
	public static List<Confirm> getListConfirm(String lang){
		List<Confirm> list=HibernateDAO.getList("from Confirm ", lang);
		return list;
	}
//get confirm
	public static Confirm getConfirm(int confirmID,String lang){
		List<Confirm> list=HibernateDAO.getList("from Confirm where confirmId='"+confirmID+"'", lang);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
//insert
	public static boolean insert(Confirm confirm,String lang){
		return HibernateDAO.insert(confirm, lang);
	}
	//update
	public static boolean update(Confirm confirm,String lang){
		return HibernateDAO.update(confirm, lang);
	}
	
	//get current confirm
	public static Confirm getCurrentCancelConfirm(int referenceID,String object,String lang){
		List<Confirm> list=HibernateDAO.getList("from Confirm where orderCancelID ='"+referenceID+"' and objects='"+object+"' and isCurrent ='true'", lang);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	//get current confirm
	public static Confirm getCurrentReturnExchangeConfirm(int referenceID,String lang){
		List<Confirm> list=HibernateDAO.getList("from Confirm where exchangeOrderID ='"+referenceID+"' and isCurrent ='true'", lang);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	

}
