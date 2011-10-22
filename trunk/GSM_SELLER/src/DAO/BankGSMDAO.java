package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UTIL.MySqlDataAccessHelper;

public class BankGSMDAO extends HibernateDAO{
	 // load bank Name by account
	   public static List<String> lstAllBankName(String lang){
	    	  List<String> list = new ArrayList<String>();
	          MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
	          
	          try {
	              String sql = "SELECT * FROM `bankgsm`";
	              helper.open(lang);
	             
	              ResultSet rs = helper.executeQuery(sql);
	              while (rs.next()) {
	            	  String s = new String(rs.getString("BankName"));
	            	  list.add(s);
	              }
	          } catch (Exception ex) {
	              ex.getMessage();
	          }
	          return list;
	    }
}
