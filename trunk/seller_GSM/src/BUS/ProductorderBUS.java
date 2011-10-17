/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;

import java.util.List;
/**
 *
 * @author admin
 */
public class ProductorderBUS {
    
     /** Get all Productorder **/
    public static List<Productorder> lstProductorder(String lang) {
        return ProductorderDAO.lstProductorder(lang);
    }

     
    /** Get Productorder in ID **/
    public static Productorder getProductorder( int id, String lang) {
        return ProductorderDAO.getProductorder(id, lang);
    }

    /** Insert Productorder **/ 
    public static boolean insertProductorder(Productorder p, String lang) {
        return ProductorderDAO.insertProductorder(p, lang);
    }

    /** Update Productorder **/
    public static boolean updateProductorder(Productorder p, String lang) {
        return ProductorderDAO.updateProductorder(p, lang);
    }

    /** Delete Productorder **/
    public static boolean deleteProductorder(Productorder p, String lang) {
        return ProductorderDAO.deleteProductorder(p, lang);
    }
    
     /** Get 1 new  Productorder **/
    public static Productorder getProductorderNew(String lang) {
        return ProductorderDAO.getProductorderNew(lang);
    }
    
    /**Get list productorder in Stateorder **/
    public static List<Productorder> lstProductorder(Stateorder state, String lang) {
    	return ProductorderDAO.lstProductorder(state, lang);
    }
    
    /**Get list productorder in Stateorder **/
    public static List<Productorder> lstProductorder(Stateorder state,int pageNumber, int pageSize, String lang) {
    	return ProductorderDAO.lstProductorder(state, pageNumber, pageSize, lang);
    }
    
    /**Get list productorder in Stateorder **/
    public static List<Productorder> lstOrderPayment(String lang) {
    	return ProductorderDAO.lstOrderPayment(lang);
    }
    
    /**Get list productorder in Stateorder **/
    public static List<Productorder> lstOrderPayment(int pageNumber, int pageSize, String lang) {
    	return ProductorderDAO.lstOrderPayment(pageNumber, pageSize,lang);
    }
    
    /** Get all Productorder with id **/
    public static List<Productorder> getListProductorder(int id,String myAccount, String lang){
       return ProductorderDAO.getListProductorder(id,myAccount, lang);
    }
}
