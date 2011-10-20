package DAO;

import java.sql.ResultSet;
import java.util.List;

import POJO.Bank;
import POJO.Bank;
import POJO.Bank;
import POJO.User;
import UTIL.MySqlDataAccessHelper;

public class BankDAO extends HibernateDAO{

	 //Get all Bank
    public static List<Bank> lstBank(String lang) {
        return HibernateDAO.getList("from Bank", lang);
    }
     
    //Get Bank in user
    public static Bank getBank(User user, String lang){
        List kq = HibernateDAO.getList("from Bank where user.account='" + user.getAccount()+"'", lang);
        if(kq.size() > 0)
            return (Bank)kq.get(0);
        return null;
    }
  //Get Bank in user
    public static Bank getBank(String sellerAccount, String lang){
        List kq = HibernateDAO.getList("from Bank where user.account='" + sellerAccount+"'", lang);
        if(kq.size() > 0)
            return (Bank)kq.get(0);
        return null;
    }
    
    public static Bank getbank(String sellername, String lang) {
        Bank bank = new Bank();
        MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
        
        try {
            String sql = "select  bank.Account,bank.BankId,bank.Banking,bank.AccountNumber,bank.AccountHolder "
                    + "from `user`, bank where `user`.Account=bank.Account  "
                    + "and bank.Account='" + sellername + "'";
            helper.open(lang);
           
            ResultSet rs = helper.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setAccount(rs.getString("Account"));
                bank.setUser(user);
                bank.setBankId(rs.getInt("BankId"));
                bank.setBanking(rs.getString("Banking"));
                bank.setAccountNumber(rs.getString("AccountNumber"));
                bank.setAccountholders(rs.getString("AccountHolder"));                
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
        return bank;
     }
    public static boolean updateBank(Bank bank, String lang){
    	  return HibernateDAO.update(bank, lang);
//        boolean result=false;
//         MySqlDataAccessHelper helper=new MySqlDataAccessHelper();
//        try{
//           // String sql="update bank set AccountNumber='"+bank.getAccountNumber()+"',Banking='"+bank.getBanking()+",'AccountHolder='"+bank.getAccountholders()+"' where BankId="+bank.getBankId();
//            String sql="update bank set AccountNumber='"+bank.getAccountNumber()+"',Banking='"+bank.getBanking()+"',AccountHolder='"+bank.getAccountholders()+"'where BankId="+bank.getBankId();
//            if (null == lang || lang.length() <= 0 || lang.equals("MALL_EN")) {
//                helper.open_en();
//            } else {
//                if (lang.equals("MALL_VN")) {
//                    helper.open_vn();
//                } else {
//                    helper.open_kr();
//                }
//            }
//            int n=helper.executeUpdate(sql);
//            if(n==1){
//                result=true;
//            }
//            helper.close();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return result;
    }
    
  //Get Bank in ID
    public static Bank getBank( int id,String lang) {
        return (Bank)HibernateDAO.getObject(Bank.class, id, lang);
    }

    //Insert Bank
    public static boolean insertBank(Bank p,String lang) {
        return HibernateDAO.insert(p, lang);
    }


    //Delete Bank
    public static boolean deleteBank(Bank p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
}
