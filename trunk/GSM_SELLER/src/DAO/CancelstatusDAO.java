package DAO;

import java.util.List;
import POJO.*;

public class CancelstatusDAO extends HibernateDAO{

	//Get all Cancelstatus
    public static List<Cancelstatus> lstCancelstatus(String lang) {
        return HibernateDAO.getList("from Cancelstatus order by cancelStatusId", lang);
    }
     
    //Get Cancelstatus in ID
    public static Cancelstatus getCancelstatusById(int id,String lang) {
        return (Cancelstatus)HibernateDAO.getObject(Cancelstatus.class, id, lang);
    }

    //Insert Cancelstatus
    public static boolean insertCancelstatus(Cancelstatus p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Cancelstatus
    public static boolean updateCancelstatus(Cancelstatus p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Cancelstatus
    public static boolean deleteCancelstatus(Cancelstatus p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
}
