package BUS;

import java.util.List;

import DAO.*;
import POJO.*;

public class ThamsoBUS {


	/** Get all Thamso **/
    public static List<Thamso> lstThamso(String lang) {
        return ThamsoDAO.lstThamso(lang);
    }
   
    /** Get Thamso in ID **/
    public static Thamso getThamsoById(int id,String lang) {
        return ThamsoDAO.getThamsoById(id, lang);
    }

    /** Insert Thamso **/ 
    public static boolean insertThamso(Thamso p,String lang) {
        return ThamsoDAO.insertThamso(p, lang);
    }

    /** Update Thamso **/
    public static boolean updateThamso(Thamso p, String lang) {
        return ThamsoDAO.updateThamso(p, lang);
    }

    /** Delete Thamso **/
    public static boolean deleteThamso(Thamso p, String lang) {
        return ThamsoDAO.deleteThamso(p, lang);
    }
    
    
}
