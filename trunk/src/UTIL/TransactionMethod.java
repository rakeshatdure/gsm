package UTIL;
import org.hibernate.Session;
import org.hibernate.Transaction;

import BUS.ProductBUS;
import BUS.ProductorderBUS;
import BUS.ProductorderdetailBUS;
import BUS.UserBUS;
import POJO.Products;
import POJO.User;
import UTIL.HibernateUtil;


public class TransactionMethod {
	static Transaction TRANSACTION=null;
	static Session session=null;
	
	public TransactionMethod() {
		// TODO Auto-generated constructor stub
	}
	
	public static TransactionMethod createInstance(){
		return new TransactionMethod(){
			@Override
			protected void doMethod(Session session, String lang) {
				
			}
		};
	}
	
	/**
	 * try{
			beginTransaction(session);
			save("dfd", session);
			commit();
		}
		catch(Exception e){
			rollBack();
		}
	 * **/
	protected  void doMethod(Session session,String lang) {
		
	}
	
	public static Session getSession(String lang){
		 Session se;
	        if(null==lang || lang.length()<=0 || lang.equals("MALL_EN")){
	        	se =  HibernateUtil.getSessionFactoryEN().openSession();
	        }else{
	        	if(lang.equals("MALL_VN")){
	        		se =  HibernateUtil.getSessionFactoryVN().openSession();
	        	}else{
	        		se =  HibernateUtil.getSessionFactoryKR().openSession();
	        	}
	        }
	        return se;
	}
	
	public static void beginTransaction(Session session){
		TRANSACTION=session.beginTransaction();
		//System.out.println("ste");
	}
	public static void commit(){
		TRANSACTION.commit();
	}
	public static void rollBack(){
		TRANSACTION.rollback();
	}

	public static void save(Object obj,Session session){
		session.save(obj);		
	}
	
	public static void update(Object obj,Session session){
		session.update(obj);
	}
	
	public static void delete(Object obj,Session session){
		//int d=Integer.parseInt("s");
		session.delete(obj);
	}
	
	public  boolean executeTransaction(String lang){
		try{
			//System.out.println("s");
			session=getSession(lang);
			beginTransaction(session);
			//
			doMethod(session, lang);
			//
			commit();
			return true;
			
		}
		catch(Exception e){
			//System.out.println("rollback");
			rollBack();
			System.out.println(e);
			return false;
		}
	}
	
	public static void main(String[] args) {
		TransactionMethod tr=new TransactionMethod(){
			@Override
			protected void doMethod(Session session, String lang) {
				
			}
		};
		tr.executeTransaction("MALL_EN");
	}
	
}
