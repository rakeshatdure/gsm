/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import POJO.*;
/**
 *
 * @author admin
 */
public class PromotionDAO extends HibernateDAO{
    
    
    //Get all Promotions
    public static List lstPromotions(String lang) {
        return HibernateDAO.getList("from Promotions", lang);
    }
    
   

    //Get Promotions in Id
    public static Promotions getPromotions(int Id,String lang) {
        return (Promotions)HibernateDAO.getObject(Promotions.class, Id, lang);
    }
    
    //Get 1 Promotion  in product
    public static Promotions getPromotion(Products p, String lang) {
        List kq = HibernateDAO.getList("from Promotions where productId=" + p.getProductId(), lang);
        if(kq.size() > 0)
                return (Promotions)kq.get(0);
            return null;

    }
    
     //insert Promotions
    public static boolean insertPromotions(Promotions u, String lang) {
        return HibernateDAO.insert(u, lang);
    }

    //Update Promotions
    public static boolean updatePromotions(Promotions u, String lang) {
        return HibernateDAO.update(u, lang);
    }

    //Delete Promotions
    public static boolean deletePromotions(Promotions u, String lang) {
        return HibernateDAO.delete(u, lang);
    }

}
