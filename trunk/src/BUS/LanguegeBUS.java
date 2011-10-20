package BUS;

import DAO.LanguegeDAO;
import POJO.*;

public class LanguegeBUS {

	/**Get value  in key**/
    public static String getValue (String key, String lang) {
    	return LanguegeDAO.getValue(key, lang);
    }
    
    /**Insert Language**/
    public static boolean insertLanguage(Language p, String lang) {
        return LanguegeDAO.insertLanguage(p, lang);
    }

    /**Update Language**/
    public static boolean updateLanguage(Language p, String lang) {
        return LanguegeDAO.updateLanguage(p, lang);
    }

    /**Delete Language**/
    public static boolean deleteLanguage(Language p, String lang) {
        return LanguegeDAO.deleteLanguage(p, lang);
    }
}
