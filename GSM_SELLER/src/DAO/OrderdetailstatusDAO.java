package DAO;

import java.util.List;

import POJO.Orderdetailstatus;

import java.util.List;
import POJO.*;

public class OrderdetailstatusDAO extends HibernateDAO{
	
	//Get all Orderdetailstatus
    public static List<Orderdetailstatus> lstOrderdetailstatus(String lang) {
        return HibernateDAO.getList("from Orderdetailstatus", lang);
    }
     
    //Get Orderdetailstatus in ID
    public static Orderdetailstatus getOrderDetailStatusById(int id,String lang) {
        return (Orderdetailstatus)HibernateDAO.getObject(Orderdetailstatus.class, id, lang);
    }

    //Insert Orderdetailstatus
    public static boolean insertOrderdetailstatus(Orderdetailstatus p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Orderdetailstatus
    public static boolean updateOrderdetailstatus(Orderdetailstatus p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Orderdetailstatus
    public static boolean deleteOrderdetailstatus(Orderdetailstatus p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
}
