package DAO;

import POJO.*;

public class LanguegeDAO {

	//Get value  in key
    public static String getValue (String key, String lang) {
    	Language lg = (Language)HibernateDAO.getObject(Language.class, key, lang);
        if(lg != null)
        	return lg.getValue();
        return null;
    }
    
    //Insert Language
    public static boolean insertLanguage(Language p, String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Language
    public static boolean updateLanguage(Language p, String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Language
    public static boolean deleteLanguage(Language p, String lang) {
        return HibernateDAO.delete(p, lang);
    }
    
}
