package BUS;

import java.util.List;

import DAO.NLPaymentDAO;
import POJO.NLPayment;

public class NLPaymentBUS {
	public static boolean insert(NLPayment nlPayment, String lang) {
		return NLPaymentDAO.insert(nlPayment, lang);
	}

	public static boolean update(NLPayment nlPayment, String lang) {
		return NLPaymentDAO.update(nlPayment, lang);
	}

	public static boolean delete(NLPayment nlPayment, String lang) {
		return NLPaymentDAO.delete(nlPayment, lang);
	}

	public static List<NLPayment> getListNLPayment(String lang) {
		return NLPaymentDAO.getListNLPayment(lang);
	}

	public static NLPayment getNLPayment(int nLPaymentID, String lang) {
		return NLPaymentDAO.getNLPayment(nLPaymentID, lang);
	}
}
