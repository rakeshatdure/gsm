package BUS;

import DAO.*;
import POJO.*;

import java.util.List;

public class OrdercancelBUS {

	/** Get all Ordercancel **/
    public static List<Ordercancel> lstOrdercancel(String lang) {
        return OrdercancelDAO.lstOrdercancel(lang);
    }
    /** Get Ordercancel  in id **/
    public static Ordercancel getOrdercancelByID(int id, String lang) {
    	return OrdercancelDAO.getOrdercancelByID(id, lang);
    }

    /** Get Ordercancel  in OrderDetailid **/
    public static Ordercancel getOrdercancelByPODetailID(int id, String lang) {
    	return OrdercancelDAO.getOrdercancelByPODetailID(id, lang);
    }
    /** Get Ordercancel in ID **/
    public static Ordercancel getOrderDetailById(int id,String lang) {
        return OrdercancelDAO.getOrderDetailById(id, lang);
    }

    /** Insert Ordercancel **/ 
    public static boolean insertOrdercancel(Ordercancel p,String lang) {
        return OrdercancelDAO.insertOrdercancel(p, lang);
    }

    /** Update Ordercancel **/
    public static boolean updateOrdercancel(Ordercancel p, String lang) {
        return OrdercancelDAO.updateOrdercancel(p, lang);
    }

    /** Delete Ordercancel **/
    public static boolean deleteOrdercancel(Ordercancel p, String lang) {
        return OrdercancelDAO.deleteOrdercancel(p, lang);
    }
    
}
