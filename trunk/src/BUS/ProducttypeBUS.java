package BUS;

import java.util.List;

import DAO.ProducttypeDAO;
import POJO.Producttype;

public class ProducttypeBUS {

	/** Get all Producttype **/
    public static List<Producttype> lstProducttype(String lang) {
        return ProducttypeDAO.lstProducttype(lang);
    }

     
    /** Get Producttype in ID **/
    public static Producttype getProducttype( int id,String lang) {
        return ProducttypeDAO.getProducttype(id, lang);
    }

    /** Insert Producttype **/ 
    public static boolean insertProducttype(Producttype p,String lang) {
        return ProducttypeDAO.insertProducttype(p, lang);
    }

    /** Update Producttype **/
    public static boolean updateProducttype(Producttype p, String lang) {
        return ProducttypeDAO.updateProducttype(p, lang);
    }

    /** Delete Producttype **/
    public static boolean deleteProducttype(Producttype p, String lang) {
        return ProducttypeDAO.deleteProducttype(p, lang);
    }
}
