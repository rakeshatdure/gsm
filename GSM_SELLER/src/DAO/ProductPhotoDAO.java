/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.*;
import java.util.List;
/**
 *
 * @author admin
 */
public class ProductPhotoDAO extends HibernateDAO{
    
    //Get 1 photos  in product
    public static Productphotos lstProductPhoto(Products p, String lang) {
        List kq = HibernateDAO.getList("from Productphotos where productId=" + p.getProductId(), lang);
        if(kq.size() > 0)
                return (Productphotos)kq.get(0);
            return null;

    }
    
    //Get all photos  in product
    public static List<Productphotos> lstProductPhotoAll(Products p, String lang) {
        return HibernateDAO.getList("from Productphotos where productId=" + p.getProductId(), lang);
    }
    
  //Insert products
    public static boolean insertProductPhotos(Productphotos p, String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update products
    public static boolean updateProductPhotos(Productphotos p, String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete products
    public static boolean deleteProductPhotos(Productphotos p, String lang) {
        return HibernateDAO.delete(p, lang);
    }
}
