package BUS;

import java.util.List;

import DAO.*;
import POJO.*;

public class PaymentBUS {

	/** Get all Payment **/
    public static List<Payment> lstPayment(String lang) {
        return PaymentDAO.lstPayment(lang);
    }

    /** Get Payment in ID **/
    public static Payment getPaymentById(int id,String lang) {
        return PaymentDAO.getPaymentById(id, lang);
    }

    /** Insert Payment **/ 
    public static boolean insertPayment(Payment p,String lang) {
        return PaymentDAO.insertPayment(p, lang);
    }

    /** Update Payment **/
    public static boolean updatePayment(Payment p, String lang) {
        return PaymentDAO.updatePayment(p, lang);
    }

    /** Delete Payment **/
    public static boolean deletePayment(Payment p, String lang) {
        return PaymentDAO.deletePayment(p, lang);
    }
    
}
