package DAO;

import java.util.Date;
import java.util.List;

import POJO.NLPayment;

public class NLPaymentDAO {
	public static boolean insert(NLPayment nlPayment, String lang) {
		return HibernateDAO.insert(nlPayment, lang);
	}

	public static boolean update(NLPayment nlPayment, String lang) {
		return HibernateDAO.update(nlPayment, lang);
	}

	public static boolean delete(NLPayment nlPayment, String lang) {
		return HibernateDAO.delete(nlPayment, lang);
	}

	@SuppressWarnings("unchecked")
	public static List<NLPayment> getListNLPayment(String lang) {
		return HibernateDAO.getList("from NLPayment", lang);
	}

	@SuppressWarnings("unchecked")
	public static NLPayment getNLPayment(int nLPaymentID, String lang) {
		List<NLPayment> list = HibernateDAO.getList(
				"from NLPayment nlPayment='" + nLPaymentID + "'", lang);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public static void main(String[] args) {
		NLPayment nlPayment=new NLPayment(1,"adf","adf","adf","adf",new Date());
		insert(nlPayment, "MALL_EN");
	}
}
