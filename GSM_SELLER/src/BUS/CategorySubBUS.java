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
public class CategorySubBUS {
    
    /** Get all CategorySub **/
    public static List<CategorySub> lstCategorySub(String lang) {
        return CategorySubDAO.lstCategorySub(lang);
    }

    /** Get all CategorySubs  in Category **/ 
    public static List<CategorySub> lstCategorySub(CategoryChild cc,String lang) {
        return CategorySubDAO.lstCategorySub(cc, lang);
    }
     
    /** Get CategorySub in ID **/
    public static CategorySub getCategorySub(String id,String lang) {
        return CategorySubDAO.getCategorySub(id, lang);
    }

    /** Insert CategorySub **/
    public static boolean insertCategorySub(CategorySub cb,String lang) {
        return CategorySubDAO.insertCategorySub(cb, lang);
    }

    /** Update CategorySub **/
    public static boolean updateCategorySub(CategorySub cb,String lang) {
        return CategorySubDAO.updateCategorySub(cb, lang);
    }

    /** Delete CategorySub **/
    public static boolean deleteCategorySub(CategorySub cb,String lang) {
        return CategorySubDAO.deleteCategorySub(cb, lang);
    }
}
