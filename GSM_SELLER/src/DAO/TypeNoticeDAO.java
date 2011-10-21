package DAO;

import java.util.List;

import POJO.Typenotice;

public class TypeNoticeDAO extends HibernateDAO{

	//Get all Typenotice
    public static List<Typenotice> lstTypenotice(String lang) {
        return HibernateDAO.getList("from Typenotice", lang);
    }
     
    //Get Typenotice in ID
    public static Typenotice getTypenotice( int id,String lang) {
        return (Typenotice)HibernateDAO.getObject(Typenotice.class, id, lang);
    }

    //Insert Typenotice
    public static boolean insertTypenotice(Typenotice tn,String lang) {
        return HibernateDAO.insert(tn, lang);
    }

    //Update Typenotice
    public static boolean updateTypenotice(Typenotice tn,String lang) {
        return HibernateDAO.update(tn, lang);
    }

    //Delete Typenotice
    public static boolean deleteTypenotice(Typenotice tn,String lang) {
        return HibernateDAO.delete(tn, lang);
    }
}
