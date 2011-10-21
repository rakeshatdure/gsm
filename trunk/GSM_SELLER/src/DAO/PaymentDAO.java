package DAO;

import java.util.List;
import POJO.*;

public class PaymentDAO extends HibernateDAO{

	//Get all Payment
    public static List<Payment> lstPayment(String lang) {
        return HibernateDAO.getList("from Payment", lang);
    }
     
    //Get Payment in ID
    public static Payment getPaymentById(int id,String lang) {
        return (Payment)HibernateDAO.getObject(Payment.class, id, lang);
    }

    //Insert Payment
    public static boolean insertPayment(Payment p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Payment
    public static boolean updatePayment(Payment p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Payment
    public static boolean deletePayment(Payment p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
}
