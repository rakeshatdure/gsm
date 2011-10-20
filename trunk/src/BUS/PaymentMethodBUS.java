package BUS;

import java.util.List;

import DAO.*;
import POJO.*;

public class PaymentMethodBUS {

	/** Get all Paymentmethod **/
    public static List<Paymentmethod> lstPaymentmethod(String lang) {
        return PaymentMethodDAO.lstPaymentmethod(lang);
    }

    /** Get Paymentmethod in ID **/
    public static Paymentmethod getPaymentmethodById(int id,String lang) {
        return PaymentMethodDAO.getPaymentmethodById(id, lang);
    }

    /** Insert Paymentmethod **/ 
    public static boolean insertPaymentmethod(Paymentmethod p,String lang) {
        return PaymentMethodDAO.insertPaymentmethod(p, lang);
    }

    /** Update Paymentmethod **/
    public static boolean updatePaymentmethod(Paymentmethod p, String lang) {
        return PaymentMethodDAO.updatePaymentmethod(p, lang);
    }

    /** Delete Paymentmethod **/
    public static boolean deletePaymentmethod(Paymentmethod p, String lang) {
        return PaymentMethodDAO.deletePaymentmethod(p, lang);
    }
}
