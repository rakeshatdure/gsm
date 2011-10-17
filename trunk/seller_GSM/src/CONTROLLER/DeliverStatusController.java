package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.DeliverBUS;
import BUS.DeliverStatusBUS;
import BUS.ProductorderdetailBUS;
import BUS.ShippingCompanyBUS;
import POJO.Deliver;
import POJO.DeliverView;
import POJO.Deliverstatus;
import POJO.Productorderdetail;
import POJO.Shippingcompany;
import UTIL.NavigationInfo;
import UTIL.ResourcesDefault;

/**
 * Servlet implementation class DeliverstatusController
 */
@WebServlet("/DeliverstatusController")
public class DeliverStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int item = 7;
	private NavigationInfo navInfo = new NavigationInfo();
	   
    public NavigationInfo getNavInfo() {
        return navInfo;
    }
    public void setNavInfo(NavigationInfo navInfo) {
        
        this.navInfo = navInfo;
    }
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeliverStatusController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<DeliverView> delivers = null;
		String seller = (String) session.getAttribute("username");
		if(seller==null){
			response.sendRedirect("../login.html");
		}
		else{
			ServletContext app=getServletContext();;
			
			String lang = (String) app.getAttribute("MALL_LA");
			String view = request.getParameter("view");
			//setting for paging
	    	navInfo.setPageSize(item);
	    	navInfo.setMaxIndices(5);
	    	String page = (String)request.getParameter("page");
	        if (null == page)
	        	navInfo.setCurrentPage(0);
	        else
	        	navInfo.setCurrentPage(Integer.parseInt(page));       
	        //setting for paging
			if (view != null) {
				String selectView = request.getParameter("selectView");
				String txtSelectView = request.getParameter("txtSelectView");
				String Deliverstatus = request.getParameter("Deliverstatus");
			
				String selectedDate = request.getParameter("selectedDate");
				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String fromHour = request.getParameter("fromHour");
				String toHour = request.getParameter("toHour");
				Object[] arrays = getDeliversView(seller, Deliverstatus
						, selectedDate, from, to, fromHour,
						toHour, selectView, txtSelectView,navInfo.getCurrentPage(),navInfo.getPageSize(), lang);
				navInfo.setRowCount((Integer) (arrays[0]!=null ? arrays[0] : 0));
		    	request.setAttribute("pagedcust", navInfo);
				delivers =(List<DeliverView>) arrays[1];
			}
			String deliverCancel = request.getParameter("submit");
			if (deliverCancel != null & deliverCancel != "") {
				handleSave( request, response, deliverCancel, lang);
				
			}
			
			if (delivers == null) {
				request.setAttribute("hadLoad", "hadLoad");
				Object[] arrays=DeliverBUS.listDeliversByWhere(null, seller,navInfo.getCurrentPage(),navInfo.getPageSize(), lang);
				
				navInfo.setRowCount((arrays[0]!=null)?(Integer)arrays[0] :0);
		    	request.setAttribute("pagedcust", navInfo);
				delivers =(List<DeliverView>) arrays[1];

			}			
			request.setAttribute("delivers", delivers);

			try {
				request.getRequestDispatcher("/sale/deliverstatus.html").forward(
						request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		

	}
	
	public void handleSave(HttpServletRequest request,HttpServletResponse response,String deliverCancel, String lang){
		String choosecancel = request.getParameter("Choose cancel");
		int indexSpace = deliverCancel.indexOf(" ");
		String productOrderDetailID = deliverCancel.substring(0, indexSpace);
		String deliverID = deliverCancel.substring(indexSpace + 1);
		if (choosecancel != null) {
			DeliverBUS.updateDeliverstatus(Integer.parseInt(deliverID),
					ResourcesDefault.CANCEL_DELIVER, lang);
		} 
		//String quantiy = request.getParameter("quantity");
//		String receiver = request.getParameter("receiver");
//		String receiverAddress = request.getParameter("deliverAddress");
		Productorderdetail productorderdetail = ProductorderdetailBUS.getProductorderdetail(Integer.parseInt(productOrderDetailID), lang);
		if (productorderdetail != null) {
			//productorderdetail.setProductNumber(Integer.parseInt(quantiy));
			ProductorderdetailBUS.updateProductorderdetail(productorderdetail, lang);
		}
		int newStatusID=Integer.parseInt(request.getParameter("newStatus"));
			//handle when comfirm the shipping company
			String confirmValue=request.getParameter("confirmValue");
			String deliverIDShippingValue=request.getParameter("deliverIDShippingValue");
			Shippingcompany shippingcompany=null;
			if(confirmValue!=null && confirmValue!="null" && confirmValue.equals(deliverIDShippingValue)){
				shippingcompany=ShippingCompanyBUS.getShippingcompany(Integer.parseInt(request.getParameter("selectShippingCompany")), lang);
			}		
						
			Deliver deliver=DeliverBUS.getDeliverByID(Integer.parseInt(deliverID), lang);
			deliver.setDeliverstatusID(newStatusID);
			if(shippingcompany!=null){
				deliver.setShippingCompanyID(shippingcompany);
			}
			DeliverBUS.update(deliver, lang);
			
		
		
	}

	public static Object[] getDeliversView(String seller,
			String Deliverstatus,
			String selectedDate, String from, String to, String fromHour,
			String toHour, String selectView, String txtSelectView,int currentPage,int pageSize, String lang) {
		String where = "";

		where = filterByDeliverstatus(Deliverstatus, where);
		where = filterBySelectedDate(selectedDate, from, to, fromHour, toHour,
				where);
		where = filterBySelectedView(selectView, txtSelectView, where);

		return DeliverBUS.listDeliversByWhere(where,
				seller,currentPage,pageSize,lang);
	}

	public static String filterByDeliverstatus(String Deliverstatus
			, String where) {
		if (null != Deliverstatus && !("-1".equals(Deliverstatus))) {
			
				where += " and (select count(ds.Deliverstatus) from Deliverstatus ds "
						+ "where ds.DeliverstatusID=deli.DeliverstatusID and ds.DeliverStatusID='"
						+ Deliverstatus + "') >0 ";
			
		}
		
		return where;
	}

	private static String filterBySelectedDate(String selectedDate,
			String from, String to, String fromHour, String toHour, String where) {
		if (null != selectedDate) {
			if ("Order date".equals(selectedDate)) {
				where += " and (po.OrderDate >= '" + from
						+ "' and po.OrderDate <= '" + to + "') ";
			}
			// else if ("Deadline".equals(selectedDate)) {
			// System.out.println("deadline");
			// where += " and (deli.DeadLine >= '" + from + " " + fromHour
			// + "' and deli.DeadLine <= '" + to + " " + toHour
			// + "') ";
			// }
			else if ("finished date".equals(selectedDate)) {
				where += " and (po.OrderDate >= '" + from
						+ "' and po.OrderDate <= '" + to + "') ";
			}
		}
		return where;
	}

	public static String filterBySelectedView(String selectView,
			String txtSelectView, String where) {
		if ((null != selectView) && (null != txtSelectView)) {
			if ("Order id".equals(selectView)) {
				where += " and  po.ProductOrderID='" + txtSelectView + "' ";
			} else if ("Receiver name".equals(selectView)) {
				where += " and  po.Reciever LIKE '%" + txtSelectView + "%' ";

			} else if ("Buyer name".equals(selectView)) {
				where += " and u.FullName LIKE '%" + txtSelectView + "%' ";
			}

		}
		return where;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
