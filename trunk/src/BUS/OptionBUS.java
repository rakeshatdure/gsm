package BUS;

import java.util.List;
import DAO.OptionDAO;
import POJO.Options;

public class OptionBUS {
	// insert
	public static boolean insert(Options options, String lang) {
		return OptionDAO.insert(options, lang);
	}

	// update
	public static boolean update(Options options, String lang) {
		return OptionDAO.update(options, lang);
	}

	// getOption by option id
	public static Options getOptionByID(int optionID, String lang) {
		return OptionDAO.getOptionByID(optionID, lang);
	}

	// getListOption by product id
	public static List<Options> getListOptionByProductID(int productID, String lang) {
		return OptionDAO.getListOptionByProductID(productID, lang);
	}

	// getListOption
	public static List<Options> getListOption(String lang) {
		return OptionDAO.getListOption(lang);
	}
}
