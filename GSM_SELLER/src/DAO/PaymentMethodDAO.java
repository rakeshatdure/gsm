package DAO;

import java.util.List;
import POJO.*;

public class PaymentMethodDAO extends HibernateDAO{

	//Get all Paymentmethod
    public static List<Paymentmethod> lstPaymentmethod(String lang) {
        return HibernateDAO.getList("from Paymentmethod", lang);
    }
     
    //Get Paymentmethod in ID
    public static Paymentmethod getPaymentmethodById(int id,String lang) {
        return (Paymentmethod)HibernateDAO.getObject(Paymentmethod.class, id, lang);
    }

    //Insert Paymentmethod
    public static boolean insertPaymentmethod(Paymentmethod p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Paymentmethod
    public static boolean updatePaymentmethod(Paymentmethod p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Paymentmethod
    public static boolean deletePaymentmethod(Paymentmethod p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
}
