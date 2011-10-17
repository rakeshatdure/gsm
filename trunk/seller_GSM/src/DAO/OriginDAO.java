package DAO;

import java.util.List;

import POJO.Origin;

public class OriginDAO extends HibernateDAO{

	//Get all Origin
    public static List<Origin> lstOrigin(String lang) {
        return HibernateDAO.getList("from Origin", lang);
    }
     
    //Get Origin in ID
    public static Origin getOrigin( int id,String lang) {
        return (Origin)HibernateDAO.getObject(Origin.class, id, lang);
    }

    //Insert Origin
    public static boolean insertOrigin(Origin o,String lang) {
        return HibernateDAO.insert(o, lang);
    }

    //Update Origin
    public static boolean updateOrigin(Origin o,String lang) {
        return HibernateDAO.update(o, lang);
    }

    //Delete Origin
    public static boolean deleteOrigin(Origin o,String lang) {
        return HibernateDAO.delete(o, lang);
    }
    
    
}
