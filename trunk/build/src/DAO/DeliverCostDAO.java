package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BUS.ProductBUS;
import POJO.DeliverCostView;
import POJO.Delivercost;
import POJO.Productorder;
import POJO.Productorderdetail;
import POJO.Products;
import POJO.Transport;
import POJO.User;
import UTIL.MySqlDataAccessHelper;
import UTIL.ResourcesDefault;

public class DeliverCostDAO extends HibernateDAO {
	public static Delivercost getDeliverProductId(int productId,String lang){
		Delivercost deliCost=null;
		 MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
	        try {
	            String sql = "select delivercost.Cost,delivercost.FeeExtra " +
	            		"from delivercost,products " +
	            		"where products.DeliverCost=DeliverCost.DeliverCostID " +
	            		"and products.ProductID="+productId ;
	            helper.open(lang);
	            ResultSet rs = helper.executeQuery(sql);
	            while (rs.next()) {
	            	deliCost=new Delivercost();
	            	deliCost.setCost(rs.getFloat("Cost"));
	            	deliCost.setFeeExtra(rs.getFloat("FeeExtra"));
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        } finally {
	            helper.close();
	        }
	        return deliCost;
	    }
	

	public static boolean insert(User seller, String deliverName, float cost,
			Date updateDate, String typeFee, float feeExtra,
			float conditionForFree, String userornot,
			String deliverCostDefault, String lang) {
		Delivercost delivercost = new Delivercost(deliverName, cost,
				updateDate, typeFee, feeExtra, conditionForFree, seller,
				userornot, deliverCostDefault);
		return HibernateDAO.insert(delivercost, lang);
	}

	public static boolean update(Delivercost deliverCost, String lang) {
		return HibernateDAO.update(deliverCost, lang);
	}

	public static Delivercost getDeliverCostById(int deliverCostID, String lang) {
		return (Delivercost) HibernateDAO.getObject(Delivercost.class,
				deliverCostID, lang);
	}

	public static List<Delivercost> getListDeliverCost(String lang) {
		return HibernateDAO.getList("from Delivercost", lang);
	}

	public static List<Delivercost> getListDeliverCostBySeller(String seller,
			String lang) {
		return HibernateDAO.getList("from Delivercost where seller.account='"
				+ seller + "'",lang);
	}
	
	public static Map getListDeliverCostBySellerPaging(String seller,
			String lang,int currentPage,int pageSize) {
		return HibernateDAO.getListMap("from Delivercost where seller.account='"
				+ seller + "'",currentPage,pageSize, lang);
	}
	
	public static Map getListDeliverCostBySellerUse(String seller,
			String lang,int currentPage,int pageSize) {
		return HibernateDAO.getListMap("from Delivercost where seller.account='"
				+ seller + "' and isUser='Y'",currentPage,pageSize, lang);
	}

	public static Delivercost getDeliverCostByOrderDetailID(int orderDetailID,
			String lang) {
		Delivercost delivercost = null;
		List<Productorderdetail> orderDetails = HibernateDAO.getList(
				"from Productorderdetail where productOrderDetailId='"
						+ orderDetailID + "'", lang);
		if (orderDetails != null && orderDetails.size() > 0) {
			Productorderdetail productorderdetail = orderDetails.get(0);
		}
		return delivercost;
	}

	public static List<DeliverCostView> getListDeliverAndFee(String where,
			String AccountSeller, String lang) {
		List<DeliverCostView> lstDeliverAndFee = new ArrayList<DeliverCostView>();

		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		String fields = " select p.ProductID, p.ProductName, "
				+ "	 m.ManufacturerName, "
				+ " (select d.DeliverID from productorder po, stateorder so,productorderdetail pod, deliver d "
				+ " where pod.ProductOrderDetailId=d.ProductOrderDetail AND pod.ProductOrderId=po.ProductOrderID and pod.ProductId=p.ProductID "
				+ "	and po.TransportId='2' and po.StateOrderID=so.StateOrderID and  so.StateOrderID !='2') as DeliverID, "
				+ " (select dc.DeliverCostID from delivercost dc where p.DeliverCost=dc.DeliverCostID) as DeliverCostID, "
				+ " (select dc.TypeFee from delivercost dc where p.DeliverCost=dc.DeliverCostID) as TypeFee, "
				+ " (select dc.ConditionForFree from delivercost dc where p.DeliverCost=dc.DeliverCostID) as ConditionForFree, "
				+ " (select dc.Cost from delivercost dc where p.DeliverCost=dc.DeliverCostID) as Cost, "
				+ " (select dc.FeeExtra from delivercost dc where p.DeliverCost=dc.DeliverCostID) as FeeExtra, "
				+ " (select dc.UpdateDate from delivercost dc where p.DeliverCost=dc.DeliverCostID) as UpdateDate ";

		String tables = "  FROM  products p, manufacturer m   ";

		String wheres = "  where p.Account='" + AccountSeller
				+ "' and m.ManufacturerId=p.ManufacturerId ";

		if (where != null) {
			wheres += where;
		}
		try {
			String sql = fields + tables + wheres;
			helper.open(lang);

			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				DeliverCostView dCView = new DeliverCostView();
				dCView.setProductID((rs.getString("ProductID") != null) ? Integer
						.parseInt(rs.getString("ProductID")) : 0);
				dCView.setProductName(rs.getString("ProductName"));
				dCView.setManufacturerName(rs.getString("ManufacturerName"));
				dCView.setDeliverID((rs.getString("DeliverID") != null) ? Integer
						.parseInt(rs.getString("DeliverID")) : 0);
				// dCView.setDeliverAddress(rs.getString("Address"));
				// dCView.setReceiver(rs.getString("Reciever"));
				dCView.setDeliverCostID((rs.getString("DeliverCostID") != null) ? Integer
						.parseInt(rs.getString("DeliverCostID")) : 0);
				dCView.setDeliverFeeType(rs.getString("TypeFee"));
				dCView.setConditionFree((rs.getString("ConditionForFree") != null) ? Float
						.parseFloat(rs.getString("ConditionForFree")) : 0);
				dCView.setDeliverFee((rs.getString("Cost") != null) ? Float
						.parseFloat(rs.getString("Cost")) : 0);
				dCView.setDeliverFeeExtra((rs.getString("FeeExtra") != null) ? Float
						.parseFloat(rs.getString("FeeExtra")) : 0);
				// dCView.setHandleDate((rs.getString("UpdateDate")!=null) ? new
				// Date(rs.getString("UpdateDate")) : null);

				lstDeliverAndFee.add(dCView);
			}
			// System.out.println("test: "+lstDeliverAndFee.size());

		} catch (Exception ex) {
			ex.getMessage();
		}
		return lstDeliverAndFee;
	}
	
	public static Map getListDeliverAndFeePaging(String where,
			String AccountSeller, String lang,int currentPage,int pageSize) {
		Map map=new HashMap();
		List<DeliverCostView> lstDeliverAndFee = new ArrayList<DeliverCostView>();

		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		String fields = " select p.ProductID, p.ProductName, "
				+ "	 m.ManufacturerName, "
				+ " (select d.DeliverID from productorder po, stateorder so,productorderdetail pod, deliver d "
				+ " where pod.ProductOrderDetailId=d.ProductOrderDetail AND pod.ProductOrderId=po.ProductOrderID and pod.ProductId=p.ProductID "
				+ "	and po.TransportId='2' and po.StateOrderID=so.StateOrderID and  so.StateOrderID !='2') as DeliverID, "
				+ " (select dc.DeliverCostID from delivercost dc where p.DeliverCost=dc.DeliverCostID) as DeliverCostID, "
				+ " (select dc.TypeFee from delivercost dc where p.DeliverCost=dc.DeliverCostID) as TypeFee, "
				+ " (select dc.ConditionForFree from delivercost dc where p.DeliverCost=dc.DeliverCostID) as ConditionForFree, "
				+ " (select dc.Cost from delivercost dc where p.DeliverCost=dc.DeliverCostID) as Cost, "
				+ " (select dc.FeeExtra from delivercost dc where p.DeliverCost=dc.DeliverCostID) as FeeExtra, "
				+ " (select dc.UpdateDate from delivercost dc where p.DeliverCost=dc.DeliverCostID) as UpdateDate ";

		String tables = "  FROM  products p, manufacturer m   ";

		String wheres = "  where p.Account='" + AccountSeller
				+ "' and m.ManufacturerId=p.ManufacturerId ";
		String limit=" limit "+currentPage+","+pageSize;

		if (where != null) {
			wheres += where;
		}
		try {
			String sql = fields + tables + wheres;			
			helper.open(lang);

			ResultSet rs = helper.executeQuery(sql);
			rs.last();
			map.put("rows", rs.getRow());
			sql+=limit;
			rs=helper.executeQuery(sql);
			while (rs.next()) {
				DeliverCostView dCView = new DeliverCostView();
				dCView.setProductID((rs.getString("ProductID") != null) ? Integer
						.parseInt(rs.getString("ProductID")) : 0);
				dCView.setProductName(rs.getString("ProductName"));
				dCView.setManufacturerName(rs.getString("ManufacturerName"));
				dCView.setDeliverID((rs.getString("DeliverID") != null) ? Integer
						.parseInt(rs.getString("DeliverID")) : 0);
				// dCView.setDeliverAddress(rs.getString("Address"));
				// dCView.setReceiver(rs.getString("Reciever"));
				dCView.setDeliverCostID((rs.getString("DeliverCostID") != null) ? Integer
						.parseInt(rs.getString("DeliverCostID")) : 0);
				dCView.setDeliverFeeType(rs.getString("TypeFee"));
				dCView.setConditionFree((rs.getString("ConditionForFree") != null) ? Float
						.parseFloat(rs.getString("ConditionForFree")) : 0);
				dCView.setDeliverFee((rs.getString("Cost") != null) ? Float
						.parseFloat(rs.getString("Cost")) : 0);
				dCView.setDeliverFeeExtra((rs.getString("FeeExtra") != null) ? Float
						.parseFloat(rs.getString("FeeExtra")) : 0);
				// dCView.setHandleDate((rs.getString("UpdateDate")!=null) ? new
				// Date(rs.getString("UpdateDate")) : null);

				lstDeliverAndFee.add(dCView);
			}
			map.put("list", lstDeliverAndFee);

		} catch (Exception ex) {
			ex.getMessage();
		}
		return map;
	}

	public static Delivercost getDeliverCostDefault(String seller, String lang) {
		List<Delivercost> list = HibernateDAO.getList(
				"from Delivercost where seller.account='" + seller
						+ "' and deliverCostDefault='true'", lang);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// set the current DeliverCostDefault become the delivercost isn't not
	// default
	public static boolean updateDeliverCostDefault(String seller,
			String deliverCostID, String lang) {
		Delivercost deCostDefaultCurrent = getDeliverCostDefault(seller, lang);
		if (deCostDefaultCurrent != null
				&& !(deliverCostID.equals(deCostDefaultCurrent
						.getDeliverCostId()))) {
			deCostDefaultCurrent.setDeliverCostDefault(ResourcesDefault.FALSE);
			return HibernateDAO.update(deCostDefaultCurrent, lang);
		}
		return false;

	}

	public static float getCostByProduct(Products product, float totalMoney,
			String lang) {
		float total = 0;
		if (product.getDeliverCost() != null) {
			Delivercost delivercost = getDeliverCostById(product
					.getDeliverCost().getDeliverCostId(), lang);

			if (totalMoney < delivercost.getConditionForFree()) {
				total = delivercost.getCost();
			}
		}
		return total;

	}
	
	//get shippingcost , totalMoney=price*quantity
	public static float getDeliverCostByProduct(Products product,float totalMoney,int transportID,
			String lang) {
		float total = 0;
		if(transportID==1){
			if (product.getDeliverCost() != null) {
				Delivercost delivercost = getDeliverCostById(product
						.getDeliverCost().getDeliverCostId(), lang);

				if (totalMoney < delivercost.getConditionForFree()) {
					total = delivercost.getCost();
				}
				total+=delivercost.getFeeExtra();
				
			}
		}
		return total;

	}
	

	public static void main(String[] args) {
		// updateDeliverCostDefault("sell01","MALL_EN");
		// List<Delivercost>
		// list=getListDeliverCostBySeller("sell01","MALL_EN");
		// List<DeliverCostView> list = getListDeliverAndFee("",
		// "sell01","MALL_EN");

		// System.out.println(insert("test", 1000, new Date(), "Free",
		// "MALL_EN"));
		// Delivercost dc=getDeliverCostByOrderDetailID(2, "MALL_EN");
		// System.out.println(dc.getDeliverName());
	}
}
