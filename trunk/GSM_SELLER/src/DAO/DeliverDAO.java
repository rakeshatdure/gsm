package DAO;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;

import POJO.Deliver;
import POJO.DeliverView;
import POJO.Delivercost;
import POJO.Deliverstatus;
import POJO.HomeDeliverView;
import POJO.Productorder;
import POJO.Productorderdetail;
import POJO.Products;
import UTIL.MySqlDataAccessHelper;
import UTIL.ResourcesDefault;

public class DeliverDAO extends HibernateDAO {
	public static List<Deliver> listDeliver(String cart,String cost,String lang){
		ArrayList<Deliver> lstDeliver=new ArrayList<Deliver>();
		MySqlDataAccessHelper helper=new MySqlDataAccessHelper();
		try{
			String sql="";
			
		}catch(Exception ex){
			ex.getMessage();
		}
		return lstDeliver;
	}

	public static List<Deliver> getListDeliver(String lang) {
		return HibernateDAO.getList("from deliver", lang);
	}

	public static Deliver getDeliver(int id, String lang) {
		return (Deliver) HibernateDAO.getObject(Deliver.class, id, lang);
	}

	public static boolean update(Deliver deliver, String lang) {
		return HibernateDAO.update(deliver, lang);
	}
	public static boolean insert(Deliver deliver, String lang) {
		return HibernateDAO.insert(deliver, lang);
	}
	

	public static void updateDeliverstatus(int id, int newStatus, String lang) {
		Deliver deliver = getDeliver(id, lang);		
		 deliver.setDeliverstatusID(newStatus);
		 update(deliver, lang);

	}

	public static Object[] listDeliversByWhere(String where,
			String AcountSeller,int currentPage,int pageSize, String lang) {
		Object[] arrays=new Object[2];
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		List<DeliverView> listDelivers=new ArrayList<DeliverView>();
		String fields = "select t.TransportName,deli.DeliverID,pod.ProductOrderDetailId,po.TransportId, "
				+ "(select ds.Deliverstatus from deliverstatus ds where ds.DeliverstatusID=deli.deliverstatusID) as Deliverstatus , "
				+ "po.Reciever,po.Address, po.ProductOrderID, p.ProductID, p.ProductName, pod.ProductNumber, "
				+ "(select pm.PromotionType from promotions pm WHERE pm.PromotionID=p.ProductID) as PromotionType, "
				+ "sta.StateOrderName , po.OrderDate," +
				" (select ShippingCompanyName from shippingcompany where deli.ShippingCompanyID=ShippingCompanyID) as ShippingCompanyName," +
				"(select op.Description from options op where op.OptionID=pod.Options) as options ";
		String tables = " from productorder po, productorderdetail pod, products p, transport t, user u, stateorder sta,deliver deli ,user seller ";

		String wheres = "where seller.Account='"+AcountSeller+"' and seller.Account=p.Account and po.ProductOrderID=pod.ProductOrderId " +
				" and pod.ProductId=p.ProductID and t.TransportID=po.TransportId  and deli.ProductOrderDetail=pod.ProductOrderDetailId " +
				" and u.Account=po.Acount and sta.StateOrderID=po.StateOrderID and pod.OrderDetailStatusId = '2' ";//and deli.deliverstatusID!='1'" ;

		if (where != null) {

			wheres += where;
		}
		try {
			String sql = fields + tables + wheres;
			helper.open(lang);
			
			ResultSet rs = helper.executeQuery(sql);
			rs.last();
			arrays[0]=rs.getRow();
			
			sql+=" limit "+currentPage+","+pageSize;
			rs = helper.executeQuery(sql);
			while (rs.next()) {
				//System.out.println("data");
				DeliverView deliverView=new DeliverView();
				deliverView.setAddress(rs.getString("Address"));
				deliverView.setDeliverID(Integer.parseInt(rs.getString("DeliverID")));
				deliverView.setDeliverstatus(rs.getString("Deliverstatus"));
				deliverView.setOrderDate(rs.getString("OrderDate"));
				deliverView.setProductID(Integer.parseInt(rs.getString("ProductID")));
				deliverView.setProductName(rs.getString("ProductName"));
				deliverView.setProductNumber(Integer.parseInt(rs.getString("ProductNumber")));
				deliverView.setProductOrderID(Integer.parseInt(rs.getString("ProductOrderID")));
				deliverView.setPromotionType(rs.getString("PromotionType"));
				deliverView.setReciever(rs.getString("Reciever"));
				deliverView.setStateOrderName(rs.getString("StateOrderName"));
				deliverView.setTransportName(rs.getString("TransportName"));
				deliverView.setProductOrderDetailId(Integer.parseInt(rs.getString("ProductOrderDetailId")));
				deliverView.setTransportId(rs.getString("TransportId"));
				deliverView.setShippingCompanyName(rs.getString("ShippingCompanyName"));
				if(rs.getString("options")!=null){
					deliverView.setOption(rs.getString("options"));
				}
				listDelivers.add(deliverView);				
			}
			arrays[1]=listDelivers;

		} catch (Exception ex) {
			ex.getMessage();
		}
		return arrays;
	}
	
	public static List<HomeDeliverView> getListHomeDeliverView(String seller,String where,String lang){
		
		@SuppressWarnings("unchecked")
		List<Object[]> listObj= (List<Object[]>)HibernateDAO.getList("select pod.productOrderDetailId as productID, d.deliverId as deliverID, sc.shippingCompanyName as shippingCompanyName, " +
				" (select tr.transportName from Transport tr where tr.transportId=pod.productorder.transport.transportId) as deliverType," +
				" d.finishedDate as finishedDate, pod.products.deliverCost.deliverCostId as shippingCost, " +
				" pod.products.price, pod.productNumber" +
				" from Deliver d,Shippingcompany sc,Productorderdetail pod " +
				" where pod.products.user.account='"+seller+"' and pod.orderDetailStatusId ='2'" +
				" and pod.productOrderDetailId=d.productOrderDetail.productOrderDetailId " +
				" and d.shippingCompanyID.shippingCompanyId=sc.shippingCompanyId "+where, lang);
		List<HomeDeliverView> list=new ArrayList<HomeDeliverView>();
		for (Object[] obj : listObj) {
			HomeDeliverView hdv=new HomeDeliverView();
			hdv.setProductID(obj[0].toString());
			hdv.setDeliverID(obj[1].toString());
			hdv.setShippingCompanyName(obj[2].toString());
			hdv.setDeliverType(obj[3].toString());
			SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-DD");
			Date d=null;
			try {
				d = sdf.parse(obj[4]+"");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			hdv.setFinishedDate( (d != null) ? sdf.format(d) : null);
			if(obj[5]!=null){
				Delivercost dc= DeliverCostDAO.getDeliverCostById(Integer.parseInt(obj[5].toString()), lang);
				Float fee=new Float(0);
				if(ResourcesDefault.HAS_FEE.equals(dc.getTypeFee())){
					fee=dc.getCost();
					fee+=dc.getFeeExtra();
				}
				else if(ResourcesDefault.FEE_HAS_CONDITION.equals(dc.getTypeFee())){
					float price=Float.parseFloat(obj[6].toString());
					float quantity=Float.parseFloat(obj[7].toString());
					if(dc.getConditionForFree()>(price*quantity)){
						fee=dc.getCost();
					}
					fee+=dc.getFeeExtra();
				}
				else if(ResourcesDefault.PREPAY.equals(dc.getTypeFee())){
					float price=Float.parseFloat(obj[6].toString());
					float quantity=Float.parseFloat(obj[7].toString());
					if(dc.getConditionForFree()>(price*quantity)){
						fee=dc.getCost();
					}
					fee+=dc.getFeeExtra();
				}

				hdv.setShippingCost(fee+"");
			}
			list.add(hdv);
		}
		return list;
	}
	
	public static List<HomeDeliverView> getListHomeDeliverViewSearch(String seller,String from,String to,String lang){
		String where=" and d.finishedDate BETWEEN '"+from+"' and '"+to+"'";
		
		return getListHomeDeliverView(seller, where, lang);
	}
	public static Deliver getDeliverByPODetail(int pODetailID,String lang){
		@SuppressWarnings("unchecked")
		List<Deliver> list=HibernateDAO.getList("from Deliver where productOrderDetail.productOrderDetailId='"+pODetailID+"'", lang);		
		return (list.size()>0) ? list.get(0) : null;
	}
//	public static void main(String[] args) throws ParseException {
		
//		SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-DD");
//		Date d=sdf.parse("2011-08-12 18:05:51.0");
//		System.out.println(sdf.format(d));
		
//		getListHomeDeliverView("sell01", "", "MALL_EN");
//		List<DeliverView> list=listDeliversByWhere("", "sell01", "MALL_EN");
//		if(list!=null && list.size()>0){
//			System.out.println(list.get(0).getAddress());
//		}
		
//		int id = 1;
//		String lang = "MALL_EN";
//		Deliver deliver = getDeliver(id, lang);
//		// System.out.println("Deliverstatusold: "+deliver.getDeliverstatus().getDeliverStatusId());
//
//		// deliver.setRequire("cancel");
//		// Deliverstatus
//		// Deliverstatus=DeliverstatusDAO.getDeliverstatus(deliver.getDeliverstatus().getDeliverstatusID(),
//		// lang);
//		// deliver.setDeliverstatus(Deliverstatus);
//		update(deliver, lang);
		
//	}
	
	
}
