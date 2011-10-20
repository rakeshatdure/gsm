package DAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import POJO.Extension;
import POJO.Inventory;
import POJO.Products;
import UTIL.MySqlDataAccessHelper;

public class ExtensionDAO extends HibernateDAO {
	
	//Get list Request Extension
    public static List<Extension> lstRequestExtension(String lang) {
    	
        return HibernateDAO.getList("from Extension where status='N' ", lang);
    }
    
   //Get list Request Extension
    public static List<Extension> lstRequestExtension(int pageNumber, int pageSize,String lang) {
    	
        return HibernateDAO.getList("from Extension where status='N' ",pageNumber,pageSize, lang);
    }
    
	//Update Extension
    public static boolean updateProducts(Products p, String lang){
        return HibernateDAO.update(p, lang);
    }
    public static Products getProducts( int id, String lang){
        return (Products)HibernateDAO.getObject(Products.class, id, lang);
    }
    public static Extension getExtension(int id,String lang){
    	return (Extension)HibernateDAO.getObject(Extension.class, id, lang);
    }
    public static boolean insertExtension(Extension ex, String lang){
        return HibernateDAO.insert(ex, lang);
    }
    
    
    /*public static boolean DangKy(String tendangnhap, String matkhau, String hoten, String diachi, String email) {
        boolean kq = false;
        MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
        String sql = "insert into nguoidung(tendangnhap,matkhau,hovaten,diachi,email,quyen) values('" + tendangnhap + "','" + matkhau + "','" + hoten + "','" + diachi + "','" + email + "','ND')";
        helper.open();
        int n = helper.executeUpdate(sql);
        if (n == 1) {
            kq = true;
        }
        helper.close();
        return kq;
    }*/
    
    public static boolean InsertExtension(int ProductId,String  LimitDateOld,int LimitDateNum,float charge,String status, String lang){
    	boolean kq=false;
    	MySqlDataAccessHelper helper=new MySqlDataAccessHelper();
    	String sql="insert into extension(ProductId,LimitDateOld,LimitDateNum,Charge,Status) values(" + ProductId + ",'" + LimitDateOld + "'," + LimitDateNum + "," + charge + ",'" + status + "')";
    	helper.open(lang);
    	
    	int n=helper.executeUpdate(sql);
    	if (n == 1) {
            kq = true;
        }
        helper.close();
    	return kq;
    	
    }
}
