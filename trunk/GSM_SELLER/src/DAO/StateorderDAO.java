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
public class StateorderDAO extends HibernateDAO{
    
     //Get all Stateorder
    public static List<Stateorder> lstStateorder(String lang) {
        return HibernateDAO.getList("from Stateorder", lang);
    }

     
    //Get Stateorder in ID
    public static Stateorder getStateorder( int id,String lang) {
        return (Stateorder)HibernateDAO.getObject(Stateorder.class, id, lang);
    }

    //Insert Stateorder
    public static boolean insertStateorder(Stateorder p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Stateorder
    public static boolean updateStateorder(Stateorder p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Stateorder
    public static boolean deleteStateorder(Stateorder p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
}
