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
public class TransportBUS {
    
     /** Get all Transport **/
    public static List<Transport> lstTransport(String lang) {
        return TransportDAO.lstTransport(lang);
    }

     
    /** Get Transport in ID **/
    public static Transport getTransport( int id, String lang) {
        return TransportDAO.getTransport(id, lang);
    }

    /** Insert Transport **/ 
    public static boolean insertTransport(Transport p, String lang) {
        return TransportDAO.insertTransport(p, lang);
    }

    /** Update Transport **/
    public static boolean updateTransport(Transport p, String lang) {
        return TransportDAO.updateTransport(p, lang);
    }

    /** Delete Transport **/
    public static boolean deleteTransport(Transport p, String lang) {
        return TransportDAO.deleteTransport(p, lang);
    }
}
