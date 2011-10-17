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
public class ManufacturerDAO extends HibernateDAO{
    
    //Get all Manufacturer
    public static List<Manufacturer> lstManufacturer(String lang) {
        return HibernateDAO.getList("from Manufacturer",lang);
    }

     
    //Get Manufacturer in ID
    public static Manufacturer getManufacturer( int id,String lang) {
        return (Manufacturer)HibernateDAO.getObject(Manufacturer.class, id, lang);
    }

    //Insert Manufacturer
    public static boolean insertManufacturer(Manufacturer m,String lang) {
        return HibernateDAO.insert(m, lang);
    }

    //Update Manufacturer
    public static boolean updateManufacturer(Manufacturer m,String lang) {
        return HibernateDAO.update(m, lang);
    }

    //Delete Manufacturer
    public static boolean deleteManufacturer(Manufacturer m,String lang) {
        return HibernateDAO.delete(m, lang);
    }
    
    
}
