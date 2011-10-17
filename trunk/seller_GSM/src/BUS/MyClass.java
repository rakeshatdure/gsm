package BUS;

import DAO.LanguegeDAO;
import POJO.Language;

public class MyClass {
	private String s;
	
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	/**Get value  in key**/
    public  String getValue (String key, String lang) {
    	return LanguegeDAO.getValue(key, lang);
    }
    
    /**Insert Language**/
    public  boolean insertLanguage(Language p, String lang) {
        return LanguegeDAO.insertLanguage(p, lang);
    }

    /**Update Language**/
    public  boolean updateLanguage(Language p, String lang) {
        return LanguegeDAO.updateLanguage(p, lang);
    }

    /**Delete Language**/
    public  boolean deleteLanguage(Language p, String lang) {
        return LanguegeDAO.deleteLanguage(p, lang);
    }
}
