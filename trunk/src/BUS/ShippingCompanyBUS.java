package BUS;

import java.util.List;

import DAO.HibernateDAO;
import DAO.ShippingCompanyDAO;
import POJO.Shippingcompany;

public class ShippingCompanyBUS {
	
	public static  Shippingcompany getShippingcompany(int id,String lang){
		return (Shippingcompany)ShippingCompanyDAO.getShippingcompany(id, lang);
	}
	
	public static  Shippingcompany getShippingcompanyByName(String name,String seller,String lang){
		return (Shippingcompany)ShippingCompanyDAO.getShippingcompanyByName( name, seller, lang);
	}
	
	public static List<Shippingcompany> getListShippingcompanỵ(String lang){
		return ShippingCompanyDAO.getListShippingcompanỵ(lang);
	}
	
	public static List<Shippingcompany> getListShippingcompanyBySeller(String seller,String where,String lang){
		return ShippingCompanyDAO.getListShippingcompanyBySeller(seller,where, lang);
	}
	
	public static List<Shippingcompany> getListShippingcompanyBySellerSearch(String seller,String searchColumn,String searchValue,String lang){
		return ShippingCompanyDAO.getListShippingcompanyBySellerSearch(seller, searchColumn, searchValue, lang);
	}

	public static boolean insertShippingCompanỵ̣(Shippingcompany object,String lang){
		return ShippingCompanyDAO.insertShippingCompanỵ̣(object, lang);
	}

	public static boolean updateShippingCompany(Shippingcompany object,String lang){
		return ShippingCompanyDAO.updateShippingCompany(object, lang);
	}
}
