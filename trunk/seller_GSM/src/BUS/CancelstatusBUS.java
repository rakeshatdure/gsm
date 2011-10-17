package BUS;

import DAO.*;
import POJO.*;

import java.util.List;

public class CancelstatusBUS {

	/** Get all Cancelstatus **/
    public static List<Cancelstatus> lstCancelstatus(String lang) {
        return CancelstatusDAO.lstCancelstatus(lang);
    }

    /** Get Cancelstatus in ID **/
    public static Cancelstatus getCancelstatusById(int id,String lang) {
        return CancelstatusDAO.getCancelstatusById(id, lang);
    }

    /** Insert Cancelstatus **/ 
    public static boolean insertCancelstatus(Cancelstatus p,String lang) {
        return CancelstatusDAO.insertCancelstatus(p, lang);
    }

    /** Update Cancelstatus **/
    public static boolean updateCancelstatus(Cancelstatus p, String lang) {
        return CancelstatusDAO.updateCancelstatus(p, lang);
    }

    /** Delete Cancelstatus **/
    public static boolean deleteCancelstatus(Cancelstatus p, String lang) {
        return CancelstatusDAO.deleteCancelstatus(p, lang);
    }
}
