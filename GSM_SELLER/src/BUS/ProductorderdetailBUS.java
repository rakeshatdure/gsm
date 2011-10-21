/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;
import POJO.View.ManageCancelView;
import UTIL.ResourcesDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author admin
 */
public class ProductorderdetailBUS {

	public static List<Productorderdetail> lstProductOrderDetailByOrderId(
			int id, String lang) {
		return ProductorderdetailDAO.lstProductOrderDetailByOrderId(id, lang);
	}

	/** Get all Product order detail **/
	public static List<Productorderdetail> lstProductorderdetail(String lang) {
		return ProductorderdetailDAO.lstProductorderdetail(lang);
	}

	/** Get Product order detail in ID **/
	public static Productorderdetail getProductorderdetail(int id, String lang) {
		return ProductorderdetailDAO.getProductorderdetail(id, lang);
	}

	/** Insert Product order detail **/
	public static boolean insertProductorderdetail(Productorderdetail p,
			String lang) {
		return ProductorderdetailDAO.insertProductorderdetail(p, lang);
	}

	/** Update Product order detail **/
	public static boolean updateProductorderdetail(Productorderdetail p,
			String lang) {
		return ProductorderdetailDAO.updateProductorderdetail(p, lang);
	}

	/** Delete Product order detail **/
	public static boolean deleteProductorderdetail(Productorderdetail p,
			String lang) {
		return ProductorderdetailDAO.deleteProductorderdetail(p, lang);
	}

	/** Get list Orderdetail in Order **/
	public static List<Productorderdetail> lstOrderdetailBy(Productorder pd,
			String lang) {
		return ProductorderdetailDAO.lstOrderdetailBy(pd, lang);
	}

	/** Get list Productorderdetail in user **/
	public static List<Productorderdetail> lstProductorderdetail(String user,
			String lang) {
		return ProductorderdetailDAO.lstProductorderdetail(user, lang);
	}

	public static ArrayList<Productorderdetail> lstProductSeller(
			String fromDate, String fromTo, String Acountseller, String lang) {
		return ProductorderdetailDAO.listProductSeller(fromDate, fromTo,
				Acountseller, lang);
	}

	public static ArrayList<Productorderdetail> lstProductSeller(
			String fromDate, String fromTo, String Acountseller,
			int pageNumber, int pageSize, String lang) {
		return ProductorderdetailDAO.listProductSeller(fromDate, fromTo,
				Acountseller, pageNumber, pageSize, lang);
	}

	public static ArrayList<Productorderdetail> lstProductAcount(
			String Acountseller, int pageNumber, int pageSize, String lang) {
		return ProductorderdetailDAO.listProductAcount(Acountseller,
				pageNumber, pageSize, lang);
	}

	public static ArrayList<Productorderdetail> lstProductAcount(
			String Acountseller, String lang) {
		return ProductorderdetailDAO.listProductAcount(Acountseller, lang);
	}

	public static ArrayList<Productorderdetail> listProductStatisticsAdmin(
			String lang) {
		return ProductorderdetailDAO.listProductStatisticsAdmin(lang);
	}

	public static ArrayList<Productorderdetail> listProductStatisticsAdmin(
			int pageNumber, int pageSize, String lang) {
		return ProductorderdetailDAO.listProductStatisticsAdmin(pageNumber,
				pageSize, lang);
	}

	public static ArrayList<Productorderdetail> listStisticsAdmin(
			String fromDate, String toDate, String lang) {
		return ProductorderdetailDAO.listStisticsAdmin(fromDate, toDate, lang);
	}

	public static ArrayList<Productorderdetail> listStisticsAdmin(
			String fromDate, String toDate, int pageNumber, int pageSize,
			String lang) {
		return ProductorderdetailDAO.listStisticsAdmin(fromDate, toDate,
				pageNumber, pageSize, lang);
	}

	public static ArrayList<Productorderdetail> lstStaticAdminDateCurrent(
			String lang) {
		return ProductorderdetailDAO.lstStaticAdminDateCurrent(lang);
	}

	public static ArrayList<Productorderdetail> lstStaticAdminMonth(
			String month, String lang) {
		return ProductorderdetailDAO.lstStaticAdminMonth(month, lang);
	}

	public static ArrayList<Productorderdetail> lstStaticAdminMonth(
			String month, int pageNumber, int pageSize, String lang) {
		return ProductorderdetailDAO.lstStaticAdminMonth(month, pageNumber,
				pageSize, lang);
	}

	public static ArrayList<Productorderdetail> lstStaticAdminYear(String year,
			String lang) {
		return ProductorderdetailDAO.lstStaticAdminYear(year, lang);
	}

	public static ArrayList<Productorderdetail> lstStaticAdminYear(String year,
			int pageNumber, int pageSize, String lang) {
		return ProductorderdetailDAO.lstStaticAdminYear(year, pageNumber,
				pageSize, lang);
	}

	public static List<Productorderdetail> getListProductorderdetail(int id,
			String lang) {
		return ProductorderdetailDAO.getListProductorderdetail(id, lang);
	}

	/**
	 * Get list ProductOrderDetail in OrderDetailStatus
	 * 
	 * **/
	public static List<Productorderdetail> lstOrderDetailByStatus(
			Orderdetailstatus status, String lang) {
		return ProductorderdetailDAO.lstOrderDetailByStatus(status, lang);
	}

	/**
	 * Get list ProductOrderDetail in OrderDetailStatus (pagging)
	 * 
	 * **/
	public static List<Productorderdetail> lstOrderDetailByStatus(
			Orderdetailstatus status, int pageNumber, int pageSize, String lang) {
		return ProductorderdetailDAO.lstOrderDetailByStatus(status, pageNumber,
				pageSize, lang);
	}

	/**
	 * Get list ProductOrderDetail in OrderDetailStatus and Product
	 * 
	 * **/
	public static List<Productorderdetail> lstOrderDetailBy(
			Orderdetailstatus status, Products p, String lang) {
		return ProductorderdetailDAO.lstOrderDetailBy(status, p, lang);
	}

	/**
	 * Get the list productorderdetail of the buyer
	 * 
	 * **/
	public static List<Productorderdetail>[] getListPODetailByBuyer(
			String account, String lang, String fromDate, String toDate,int pageNumber,int pageSize) {
		return ProductorderdetailDAO.getListPODetailByBuyer(account, lang,
				fromDate, toDate,pageNumber,pageSize);
	}
	/**
	 * Get the list productorderdetail of the buyer
	 * width orderdetatilstatus='1 or 2' , deliverstaus='1 or 2'
	 * **/
	public static List<Productorderdetail>[] getListPODetailApplyCancelByBuyer(
			String account, String lang, String fromDate, String toDate,int currentPage,int pageSize) {
		return ProductorderdetailDAO.getListPODetailApplyCancelByBuyer(account, lang,
				fromDate, toDate, currentPage, pageSize);
	}
	/**
	 * Get the Productorderdetail list of the buyer
	 * with orderdetailstatus='3' - (cancel status)
	 * **/
	public static List<Productorderdetail>[] getListPODetailCancelByBuyer(
			String account, String lang, String fromDate, String toDate,int currentPage,int pageSize) {
		return ProductorderdetailDAO.getListPODetailCancelByBuyer(account,
				lang, fromDate, toDate,currentPage,pageSize);
	}
	/**
	 * Get the Productorderdetail list of the buyer
	 * with orderdetailstatus='4' - (return/exchange status)
	 * **/
	public static List<Productorderdetail>[] getListPODetailReturnExchangeByBuyer(
			String account, String lang, String fromDate, String toDate,int currentPage,int pageSize) {
		return ProductorderdetailDAO.getListPODetailReturnExchangeByBuyer(
				account, lang, fromDate, toDate,currentPage,pageSize);
	}
	/**
	 * Get the Productorderdetail list of the buyer
	 * with orderdetailstatus='2', deliverstatus='7'  - (delivered status)
	 * **/
	public static List<Productorderdetail>[] getListPODetailShippingCompletedByBuyer(
			String account, String lang, String fromDate, String toDate,int currentPage,int pageSize) {
		return ProductorderdetailDAO.getListPODetailShippingCompletedByBuyer(
				account, lang, fromDate, toDate, currentPage, pageSize);
	}
	
	/**
	 * Get the list productorderdetail of the seller
	 * width orderdetailstatus='3' -(cancel status)
	 * **/
	public static Map getListManageCancel(String seller,String lang,String where,int currentPage,int pageSize){
		return ProductorderdetailDAO.getListManageCancel(seller, lang, where,currentPage,pageSize);
	}
	/*get ListManageCancelWithSearch*/
	 public static Map getListManageCancelWithSearch(String seller,String lang,String cancelStatusID,String selectDate,String fromDate,String toDate,String selectAdvanced,String textSearch,int currentPage,int pageSize){
		 return ProductorderdetailDAO.getListManageCancelWithSearch(seller, lang, cancelStatusID, selectDate, fromDate, toDate,selectAdvanced,textSearch,currentPage,pageSize);
	 }
	 
	 public static void main(String[] args) {
		 Productorderdetail productorderdetail =getProductorderdetail(63, "MALL_VN");
		 System.out.println(productorderdetail);
		 productorderdetail
			.setOrderDetailStatusId(ResourcesDefault.ODS_RETURN_EXCHANGE);
		 updateProductorderdetail(productorderdetail, "MALL_VN");
	}

}
