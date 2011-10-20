package DAO;

import java.util.List;
import POJO.*;

public class OrdercancelDAO extends HibernateDAO{

	//Get all Ordercancel
    public static List<Ordercancel> lstOrdercancel(String lang) {
        return HibernateDAO.getList("from Ordercancel", lang);
    }
  //Get all Ordercancel  in id
    public static Ordercancel getOrdercancelByID(int id, String lang) {
        List lst = HibernateDAO.getList("from Ordercancel where orderCancelId="+id,lang);
        if(lst.size() > 0)
       	 return (Ordercancel)lst.get(0);
        return null;
    }
    
    //Get all Ordercancel  in OrderDetail
     public static Ordercancel getOrdercancelByPODetailID(int id, String lang) {
         List lst = HibernateDAO.getList("from Ordercancel where orderDetaiId="+id+" order by updateDate desc",lang);
         if(lst.size() > 0)
        	 return (Ordercancel)lst.get(0);
         return null;
     }
     
    //Get Ordercancel in ID
    public static Ordercancel getOrderDetailById(int id,String lang) {
        return (Ordercancel)HibernateDAO.getObject(Ordercancel.class, id, lang);
    }

    //Insert Ordercancel
    public static boolean insertOrdercancel(Ordercancel p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Ordercancel
    public static boolean updateOrdercancel(Ordercancel p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Ordercancel
    public static boolean deleteOrdercancel(Ordercancel p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
       
}
