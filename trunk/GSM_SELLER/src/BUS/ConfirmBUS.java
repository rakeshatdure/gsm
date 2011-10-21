package BUS;

import java.util.List;

import DAO.ConfirmDAO;
import DAO.HibernateDAO;
import POJO.Confirm;

public class ConfirmBUS {
	// get list confirm
	public static List<Confirm> getListConfirm(String lang) {
		return ConfirmDAO.getListConfirm(lang);
	}

	// get confirm
	public static Confirm getConfirm(int confirmID, String lang) {
		return ConfirmDAO.getConfirm(confirmID, lang);
	}

	// insert
	public static boolean insert(Confirm confirm, String lang) {
		return ConfirmDAO.insert(confirm, lang);
	}

	// update
	public static boolean update(Confirm confirm, String lang) {
		return ConfirmDAO.update(confirm, lang);
	}

	/**
	 * get the current confirm of the seller to the order cancel of the buyer
	 * **/

	public static Confirm getCurrentCancelConfirm(int referenceID,String object, String lang) {
		return ConfirmDAO.getCurrentCancelConfirm(referenceID,object, lang);
	}

	/**
	 * get the current confirm of the seller to the order cancel of the buyer
	 * **/

	public static Confirm getCurrentReturnExchangeConfirm(int exchangeID,
			String lang) {
		return ConfirmDAO.getCurrentReturnExchangeConfirm(exchangeID, lang);
	}
}
