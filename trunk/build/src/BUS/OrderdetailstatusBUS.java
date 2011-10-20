package BUS;

import DAO.*;
import POJO.*;

import java.util.List;

public class OrderdetailstatusBUS {

	/** Get all Orderdetailstatus **/
    public static List<Orderdetailstatus> lstOrderdetailstatus(String lang) {
        return OrderdetailstatusDAO.lstOrderdetailstatus(lang);
    }

     
    /** Get Orderdetailstatus in ID **/
    public static Orderdetailstatus getOrderDetailStatusById(int id,String lang) {
        return OrderdetailstatusDAO.getOrderDetailStatusById(id, lang);
    }

    /** Insert Orderdetailstatus **/ 
    public static boolean insertOrderdetailstatus(Orderdetailstatus p,String lang) {
        return OrderdetailstatusDAO.insertOrderdetailstatus(p, lang);
    }

    /** Update Orderdetailstatus **/
    public static boolean updateOrderdetailstatus(Orderdetailstatus p, String lang) {
        return OrderdetailstatusDAO.updateOrderdetailstatus(p, lang);
    }

    /** Delete Orderdetailstatus **/
    public static boolean deleteOrderdetailstatus(Orderdetailstatus p, String lang) {
        return OrderdetailstatusDAO.deleteOrderdetailstatus(p, lang);
    }
    
}
