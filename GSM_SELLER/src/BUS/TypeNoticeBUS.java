package BUS;

import java.util.List;

import DAO.TypeNoticeDAO;
import POJO.Typenotice;

public class TypeNoticeBUS {

	 /** Get all Typenotice **/
    public static List<Typenotice> lstTypenotice(String lang) {
        return TypeNoticeDAO.lstTypenotice(lang);
    }
     
    /** Get Typenotice in ID **/
    public static Typenotice getTypenotice( int id,String lang) {
        return TypeNoticeDAO.getTypenotice(id, lang);
    }

    /** Insert Typenotice **/ 
    public static boolean insertTypenotice(Typenotice tn,String lang) {
        return TypeNoticeDAO.insertTypenotice(tn, lang);
    }

    /** Update Typenotice **/
    public static boolean updateTypenotice(Typenotice tn, String lang) {
        return TypeNoticeDAO.updateTypenotice(tn, lang);
    }

    /** Delete Typenotice **/
    public static boolean deleteTypenotice(Typenotice tn, String lang) {
        return TypeNoticeDAO.deleteTypenotice(tn, lang);
    }
}
