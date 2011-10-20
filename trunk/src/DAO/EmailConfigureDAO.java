package DAO;

import java.util.List;


import POJO.Emailconfigure;

public class EmailConfigureDAO extends HibernateDAO{
public static boolean insert(Emailconfigure emailconfigure,String lang){
	return HibernateDAO.insert(emailconfigure, lang);
}
public static boolean update(Emailconfigure emailconfigure,String lang){
	return HibernateDAO.update(emailconfigure, lang);
}
public static boolean delete(Emailconfigure emailconfigure,String lang){
	return HibernateDAO.delete(emailconfigure, lang);
}
@SuppressWarnings("unchecked")
public static Emailconfigure getEmailConfigure(int id,String lang){
	List<Emailconfigure> list=HibernateDAO.getList("from Emailconfigure where emailConfigureId='"+id+"'", lang);
	if(list.size()>0) return list.get(0);
	return null;
}
@SuppressWarnings("unchecked")
public static List<Emailconfigure> getListEmailConfigure(String lang){
	return HibernateDAO.getList("from Emailconfigure", lang);
}
}
