package BUS;

import java.util.List;

import DAO.BankDAO;
import POJO.Bank;
import POJO.User;

public class BankBUS {

	 /** Get all Bank **/
    public static List<Bank> lstBank(String lang) {
        return BankDAO.lstBank(lang);
    }

    /**Get Bank in user**/
    public static Bank getBank(User user, String lang){
    	return BankDAO.getBank(user, lang);
    }
    /**Get Bank in username**/
    public static Bank getBank(String sellerAccount, String lang){
    	return BankDAO.getBank(sellerAccount, lang);
    }
    
    /** Get Bank in ID **/
    public static Bank getBank( int id,String lang) {
        return BankDAO.getBank(id, lang);
    }

    /** Insert Bank **/ 
    public static boolean insertBank(Bank p,String lang) {
        return BankDAO.insertBank(p, lang);
    }

    /** Update Bank **/
    public static boolean updateBank(Bank p, String lang) {
        return BankDAO.updateBank(p, lang);
    }

    /** Delete Bank **/
    public static boolean deleteBank(Bank p, String lang) {
        return BankDAO.deleteBank(p, lang);
    }
    
}
