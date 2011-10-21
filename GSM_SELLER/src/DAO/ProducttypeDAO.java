package DAO;

import java.util.List;

import POJO.Producttype;

public class ProducttypeDAO extends HibernateDAO{

	//Get all Producttype
    public static List<Producttype> lstProducttype(String lang) {
        return HibernateDAO.getList("from Producttype", lang);
    }
     
    //Get Producttype in ID
    public static Producttype getProducttype( int id,String lang) {
        return (Producttype)HibernateDAO.getObject(Producttype.class, id, lang);
    }

    //Insert Producttype
    public static boolean insertProducttype(Producttype p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Producttype
    public static boolean updateProducttype(Producttype p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Producttype
    public static boolean deleteProducttype(Producttype p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
}
