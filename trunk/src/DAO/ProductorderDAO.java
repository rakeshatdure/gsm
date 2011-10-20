/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import POJO.*;
/**
 *
 * @author admin
 */
public class ProductorderDAO extends HibernateDAO{
    
    //Get all Productorder
    public static List<Productorder> lstProductorder(String lang) {
        return HibernateDAO.getList("from Productorder", lang);
    }
     
    //Get Productorder in ID
    public static Productorder getProductorder( int id, String lang) {
        return (Productorder)HibernateDAO.getObject(Productorder.class, id, lang);
    }

    //Insert Productorder
    public static boolean insertProductorder(Productorder p, String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Productorder
    public static boolean updateProductorder(Productorder p, String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Productorder
    public static boolean deleteProductorder(Productorder p, String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
     //Get 1 new   Productorder
    public static Productorder getProductorderNew(String lang) {
        List kq = HibernateDAO.getList("from Productorder order by productOrderId desc", lang);
        if(kq.size() > 0)
                return (Productorder)kq.get(0);
            return null;

    }
    
    //Get list productorder in Stateorder
    public static List<Productorder> lstProductorder(Stateorder state, String lang) {
        return (List<Productorder>)HibernateDAO.getList("from Productorder where stateorder.stateOrderId=" + state.getStateOrderId(), lang);
    }
    
    //Get list productorder in Stateorder
    public static List<Productorder> lstProductorder(Stateorder state,int pageNumber, int pageSize, String lang) {
        return (List<Productorder>)HibernateDAO.getList("from Productorder where stateorder.stateOrderId=" + state.getStateOrderId(),pageNumber,pageSize, lang);
    }
    
   //Get list productorder in Stateorder
    public static List<Productorder> lstOrderPayment(String lang) {
        return (List<Productorder>)HibernateDAO.getList("from Productorder where stateOrderId=3 or stateOrderId=5 or stateOrderId=4", lang);
    }
    
  //Get list productorder in Stateorder
    public static List<Productorder> lstOrderPayment(int pageNumber, int pageSize,String lang) {
        return (List<Productorder>)HibernateDAO.getList("from Productorder where stateOrderId=3 or stateOrderId=5 or stateOrderId=4",pageNumber,pageSize,lang);
    }
    
    
    /** Get all Productorder with id **/
    public static List<Productorder> getListProductorder(int id,String myAccount, String lang){
        String sql="from Productorder where StateOrderID ="+id+" and Acount='"+myAccount+"'";
        return HibernateDAO.getList(sql, lang);
    }
    
}
