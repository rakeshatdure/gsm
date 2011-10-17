package BUS;

import java.util.List;

import DAO.*;
import POJO.*;

public class CompanyBUS {

	 /** Get all Company **/
    public static List<Company> lstCompany(String lang) {
        return CompanyDAO.lstCompany(lang);
    }

    /**Get Company in user**/
    public static Company getCompany(User user, String lang){
    	return CompanyDAO.getCompany(user, lang);
    }
    /** Get Company in ID **/
    public static Company getCompany( int id,String lang) {
        return CompanyDAO.getCompany(id, lang);
    }

    /** Insert Company **/ 
    public static boolean insertCompany(Company p,String lang) {
        return CompanyDAO.insertCompany(p, lang);
    }

    /** Update Company **/
    public static boolean updateCompany(Company p, String lang) {
        return CompanyDAO.updateCompany(p, lang);
    }

    /** Delete Company **/
    public static boolean deleteCompany(Company p, String lang) {
        return CompanyDAO.deleteCompany(p, lang);
    }
    
}
