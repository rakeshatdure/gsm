package DAO;

import java.util.List;
import POJO.*;

public class ThamsoDAO extends HibernateDAO{

	//Get all Thamso
    public static List<Thamso> lstThamso(String lang) {
        return HibernateDAO.getList("from Thamso", lang);
    }
 
  
    //Get Thamso in ID
    public static Thamso getThamsoById(int id,String lang) {
        return (Thamso)HibernateDAO.getObject(Thamso.class, id, lang);
    }

    //Insert Thamso
    public static boolean insertThamso(Thamso p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Thamso
    public static boolean updateThamso(Thamso p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Thamso
    public static boolean deleteThamso(Thamso p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
}
