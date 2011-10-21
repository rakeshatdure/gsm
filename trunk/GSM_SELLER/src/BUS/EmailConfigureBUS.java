package BUS;


import java.util.List;

import DAO.EmailConfigureDAO;
import POJO.Emailconfigure;

public class EmailConfigureBUS {
	/**
	 * insert
	 **/
	public static boolean insert(Emailconfigure emailconfigure,String lang){
		return EmailConfigureDAO.insert(emailconfigure, lang);
	}
	/**
	 * update
	 **/
	public static boolean update(Emailconfigure emailconfigure,String lang){
		return EmailConfigureDAO.update(emailconfigure, lang);
	}
	/**
	 * delete 
	 **/
	public static boolean delete(Emailconfigure emailconfigure,String lang){
		return EmailConfigureDAO.delete(emailconfigure, lang);
	}
	/**
	 * get a Emailconfigure by emailconfigureid
	 **/
	public static Emailconfigure getEmailConfigure(int id,String lang){
		return EmailConfigureDAO.getEmailConfigure(id, lang);
	}
	/**
	 * get The list of the Emailconfigure
	 **/
	public static List<Emailconfigure> getListEmailConfigure(String lang){
		return EmailConfigureDAO.getListEmailConfigure(lang);
	}
}
