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
public class PromotionBUS {
    
    /** Get all Promotions **/
    public static List lstPromotions(String lang) {
        return PromotionDAO.lstPromotions(lang);
    }

     
    /** Get Promotions in Id **/
    public static Promotions getPromotions(int Id, String lang) {
        return PromotionDAO.getPromotions(Id, lang);
    }
    
    /** Get 1 Promotion  in product **/
    public static Promotions getPromotion(Products p, String lang) {
        return PromotionDAO.getPromotion(p, lang);
    }
     /** insert Promotions **/ 
    public static boolean insertPromotions(Promotions pr, String lang) {
        return PromotionDAO.insertPromotions(pr, lang);
    }

    /** Update Promotions **/
    public static boolean updatePromotions(Promotions pr, String lang) {
        return PromotionDAO.updatePromotions(pr, lang);
    }

    /** Delete Promotions **/
    public static boolean deletePromotions(Promotions pr, String lang) {
        return PromotionDAO.deletePromotions(pr, lang);
    }
    
}
