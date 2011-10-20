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
public class ManufacturerBUS {
    
    /** Get all Manufacturer **/
    public static List<Manufacturer> lstManufacturer(String lang) {
        return ManufacturerDAO.lstManufacturer(lang);
    }

     
    /** Get Manufacturer in ID **/
    public static Manufacturer getManufacturer( int id, String lang) {
        return ManufacturerDAO.getManufacturer(id, lang);
    }

    /** Insert Manufacturer **/
    public static boolean insertManufacturer(Manufacturer m, String lang) {
        return ManufacturerDAO.insertManufacturer(m, lang);
    }

    /** Update Manufacturer **/
    public static boolean updateManufacturer(Manufacturer m, String lang) {
        return ManufacturerDAO.updateManufacturer(m, lang);
    }

    /** Delete Manufacturer **/
    public static boolean deleteManufacturer(Manufacturer m, String lang) {
        return ManufacturerDAO.deleteManufacturer(m, lang);
    }
    
    /**
     * get manufacturer by manufacturerName
     * **/
    public static Manufacturer getManufacturerByName(String manufacturerName,String lang){
    	return ManufacturerDAO.getManufacturerByName(manufacturerName, lang);
    }
}
