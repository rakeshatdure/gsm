package DAO;

import java.util.List;

import POJO.Shippingcompany;

public class ShippingCompanyDAO extends HibernateDAO{
public static  Shippingcompany getShippingcompany(int id,String lang){
	return (Shippingcompany)HibernateDAO.getObject(Shippingcompany.class, id, lang);
}
public static  Shippingcompany getShippingcompanyByName(String name,String seller,String lang){
	List<Shippingcompany> list=HibernateDAO.getList("from  Shippingcompany where seller.account='"+seller+"' and shippingCompanyName='"+name+"'", lang);
	return (list.size()>0) ? list.get(0) : null;
}


public static List<Shippingcompany> getListShippingcompanỵ(String lang){
	return HibernateDAO.getList("from  Shippingcompany", lang);
}
public static List<Shippingcompany> getListShippingcompanyBySeller(String seller,String where,String lang){
	return HibernateDAO.getList("from  Shippingcompany where seller.account='"+seller+"' "+where, lang);
}

public static List<Shippingcompany> getListShippingcompanyBySellerSearch(String seller,String searchColumn,String searchValue,String lang){
	return getListShippingcompanyBySeller(seller, " and "+searchColumn+" like'%"+searchValue+"%'", lang);
}

public static boolean insertShippingCompanỵ̣(Shippingcompany object,String lang){
	return HibernateDAO.insert(object, lang);
}

public static boolean updateShippingCompany(Shippingcompany object,String lang){
	return HibernateDAO.update(object, lang);
}

public static void main(String[] args) {
	
	
	List<Shippingcompany> list= getListShippingcompanyBySeller("sell01","", "MALL_EN");
	for(Shippingcompany shippingcompany : list){
		System.out.println(shippingcompany.getShippingCompanyName());
		shippingcompany.setShippingCompanyName("selll");
		update(shippingcompany, "MALL_EN");
	}
	
}

}