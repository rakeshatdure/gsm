package DAO;

import java.util.List;

import POJO.Options;

public class OptionDAO extends HibernateDAO {
	// insert
	public static boolean insert(Options options, String lang) {
		return HibernateDAO.insert(options, lang);
	}

	// update
	public static boolean update(Options options, String lang) {
		return HibernateDAO.update(options, lang);
	}

	// getOption by option id
	@SuppressWarnings("unchecked")
	public static Options getOptionByID(int optionID, String lang) {
		List<Options> list = HibernateDAO.getList("from Options where optionId='"
				+ optionID + "'", lang);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// getListOption by product id
	@SuppressWarnings("unchecked")
	public static List<Options> getListOptionByProductID(int productID, String lang) {
		List<Options> list = HibernateDAO
				.getList("from Options where product.productId='" + productID
						+ "'", lang);
		return list;
	}

	// getListOption
	@SuppressWarnings("unchecked")
	public static List<Options> getListOption(String lang) {
		return HibernateDAO.getList("from Options", lang);
	}
}
