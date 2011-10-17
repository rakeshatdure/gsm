/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;
import java.util.List;
/**
 *
 * @author admin
 */
public class ProductPhotoBUS {
    
     /** Get all photos  in product **/
    public static Productphotos lstProductPhoto(Products p, String lang) {
        return ProductPhotoDAO.lstProductPhoto(p, lang);
    }
    
     /** Get all photos  in product **/
    public static List<Productphotos> lstProductPhotoAll(Products p, String lang) {
        return ProductPhotoDAO.lstProductPhotoAll(p, lang);
    }
    
    /** Insert photo products **/
    public static boolean insertProductPhotos(Productphotos p, String lang) {
        return ProductPhotoDAO.insertProductPhotos(p, lang);
    }

    /** Update photo products **/
    public static boolean updateProductPhotos(Productphotos p, String lang) {
        return ProductPhotoDAO.updateProductPhotos(p, lang);
    }

    /** Delete photo products **/
    public static boolean deleteProductPhotos(Productphotos p, String lang) {
        return ProductPhotoDAO.deleteProductPhotos(p, lang);
    }
    
}
