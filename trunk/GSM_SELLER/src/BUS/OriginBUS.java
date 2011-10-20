package BUS;

import java.util.List;

import DAO.OriginDAO;
import POJO.Origin;

public class OriginBUS {

	/** Get all Origin **/
    public static List<Origin> lstOrigin(String lang) {
        return OriginDAO.lstOrigin(lang);
    }

     
    /** Get Origin in ID **/
    public static Origin getOrigin( int id,String lang) {
        return OriginDAO.getOrigin(id, lang);
    }

    /** Insert Origin **/ 
    public static boolean insertOrigin(Origin p,String lang) {
        return OriginDAO.insertOrigin(p, lang);
    }

    /** Update Origin **/
    public static boolean updateOrigin(Origin p, String lang) {
        return OriginDAO.updateOrigin(p, lang);
    }

    /** Delete Origin **/
    public static boolean deleteOrigin(Origin p, String lang) {
        return OriginDAO.deleteOrigin(p, lang);
    }
}
