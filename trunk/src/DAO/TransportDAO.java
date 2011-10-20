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
public class TransportDAO extends HibernateDAO{
    
    //Get all Transport
    public static List<Transport> lstTransport(String lang) {
        return HibernateDAO.getList("from Transport", lang);
    }

     
    //Get Transport in ID
    public static Transport getTransport( int id, String lang) {
        return (Transport)HibernateDAO.getObject(Transport.class, id, lang);
    }

    //Insert Transport
    public static boolean insertTransport(Transport p, String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Transport
    public static boolean updateTransport(Transport p, String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Transport
    public static boolean deleteTransport(Transport p, String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
}
