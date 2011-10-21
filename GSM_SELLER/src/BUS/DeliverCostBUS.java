package BUS;

import java.util.Date;
import java.util.List;
import java.util.Map;

import DAO.DeliverCostDAO;
import DAO.HibernateDAO;
import POJO.DeliverCostView;
import POJO.Delivercost;
import POJO.Productorder;
import POJO.Productorderdetail;
import POJO.Products;
import POJO.Transport;
import POJO.User;

public class DeliverCostBUS {

	public static boolean insert(User seller, String deliverName, float cost,
			Date updateDate, String typeFee, float feeExtra,
			float conditionForFree, String userornot,
			String deliverCostDefault, String lang) {
		return DeliverCostDAO.insert(seller, deliverName, cost, updateDate,
				typeFee, feeExtra, conditionForFree, userornot,
				deliverCostDefault, lang);
	}

	public static boolean update(Delivercost deliverCost, String lang) {
		return DeliverCostDAO.update(deliverCost, lang);
	}

	public static Delivercost getDeliverCostById(int deliverCostID, String lang) {
		return DeliverCostDAO.getDeliverCostById(deliverCostID, lang);
	}

	public static List<Delivercost> getListDeliverCost(String lang) {
		return DeliverCostDAO.getListDeliverCost(lang);
	}
	public static List<Delivercost> getListDeliverCostBySeller(String seller,String lang) {
		return DeliverCostDAO.getListDeliverCostBySeller(seller,lang);
	}
	
	public static Map getListDeliverCostBySellerPaging(String seller,String lang,int currentPage,int pageSize) {
		return DeliverCostDAO.getListDeliverCostBySellerPaging(seller,lang,currentPage,pageSize);
	}
	
	public static Map getListDeliverCostBySellerUse(String seller,String lang,int currentPage,int pageSize) {
		return DeliverCostDAO.getListDeliverCostBySellerUse(seller,lang,currentPage,pageSize);
	}

	public static Delivercost getDeliverCostByOrderDetailID(int orderDetailID,
			String lang) {
		return DeliverCostDAO
				.getDeliverCostByOrderDetailID(orderDetailID, lang);
	}

	public static List<DeliverCostView> getListDeliverAndFee(String where,
			String AccountSeller, String lang) {
		return DeliverCostDAO.getListDeliverAndFee(where, AccountSeller, lang);
	}
	public static Map getListDeliverAndFeePaging(String where,
			String AccountSeller, String lang,int currentPage,int pageSize) {
		return DeliverCostDAO.getListDeliverAndFeePaging(where, AccountSeller, lang,currentPage,pageSize);
	}
	public static boolean updateDeliverCostDefault(String seller,String deliverCostID, String lang){
		return DeliverCostDAO.updateDeliverCostDefault(seller,deliverCostID, lang);
	}
	
	public static float getDeliverCostByProduct(Products product,float totalMoney,int transportID,
			String lang) {
		return DeliverCostDAO.getDeliverCostByProduct(product, totalMoney, transportID, lang);
	}
	public static void main(String[] args) {
		//getDeliverCostById(658, "MALL_EN");
		String test="true";
		System.out.println("true"!=test);
	}
}
