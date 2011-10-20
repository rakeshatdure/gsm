package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.*;
import POJO.*;
import POJO.View.ManageCancelView;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class sellerOrderCancelController
 */
@WebServlet("/sellerOrderCancelController")
public class sellerOrderCancelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NavigationInfo navInfo = new NavigationInfo();
	int item = 10;
    public NavigationInfo getNavInfo() {
        return navInfo;
    }
    public void setNavInfo(NavigationInfo navInfo) {
        
        this.navInfo = navInfo;
    }

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sellerOrderCancelController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			String url = "";
			String login = (String) session.getAttribute("username");
			ServletContext app=getServletContext();;
			String lang = (String) app.getAttribute("MALL_LA");
			if (login == null || login.isEmpty()) {
				// request.setAttribute("Message","Please login to access system !");
				response.sendRedirect("../login.html");
			} else {

				if (session.getAttribute("Role").equals("Seller")) {
					//setting the paging
			    	navInfo.setPageSize(item);
			    	navInfo.setMaxIndices(5);
			    	String page = (String)request.getParameter("page");
			        if (null == page)
			        	navInfo.setCurrentPage(0);
			        else
			        	navInfo.setCurrentPage(Integer.parseInt(page));       
			        //setting the paging

					// edit
					if (request.getParameter("confirmCancel") != null) {
						System.out.println("confirmcancel");
						handleEdit(lang, login, request, response);
					}
					// search
					List<ManageCancelView> list = null;
					//System.out.println(request.getParameter("search"));
					if (request.getParameter("search") != null) {
						String cancelStatusID=request.getParameter("selectCancelStatus");
						String selectDate=request.getParameter("selectDate");
						String fromDate=request.getParameter("fromDate");
						String toDate=request.getParameter("toDate");
						String selectAdvanced=request.getParameter("selectAdvanced");
						String textSearch=request.getParameter("textSearch");
						Map map=ProductorderdetailBUS.getListManageCancelWithSearch(login,
								lang, cancelStatusID,selectDate,fromDate,toDate,selectAdvanced,textSearch,navInfo.getCurrentPage(),navInfo.getPageSize());
						list =(List<ManageCancelView>) map.get("list");
						navInfo.setRowCount((Integer) map.get("rows"));
				    	request.setAttribute("pagedcust", navInfo);
					} else {
						Map map=ProductorderdetailBUS.getListManageCancel(login,
								lang, "",navInfo.getCurrentPage(),navInfo.getPageSize());
						list = (List<ManageCancelView>) map.get("list");
						navInfo.setRowCount((Integer) map.get("rows"));
				    	request.setAttribute("pagedcust", navInfo);
					}
					
					request.setAttribute("lstOrderCancels", list);

					url = "/sale/cancelorder.html";
					
				} else {
					request.setAttribute("Message",
							"Accounts are not allowed to access!");
					url = "/login.html";
				}
			}
			// System.out.println("url: "+url);
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception ex) {
			out.println(ex.getMessage());
		} finally {
			out.close();
		}
	}

	private void handleEdit(String lang, String login,
			HttpServletRequest request, HttpServletResponse response) {
		String penalty_money = request.getParameter("penalty_money");
		String confirmCancelStatus = request
				.getParameter("confirmCancelStatus");
		String memoToCustomer = request.getParameter("memoToCustomer");

		String id = request.getParameter("orderCancel");
		Ordercancel ordercancel = OrdercancelBUS.getOrdercancelByID(
				Integer.parseInt(id), lang);
		ordercancel.setCancelStatusId(Integer.parseInt(confirmCancelStatus));
		ordercancel.setCancellationPenalty(Float.parseFloat(penalty_money));
		ordercancel.setUpdateDate(new Date());
		OrdercancelBUS.updateOrdercancel(ordercancel, lang);
		// get current confirm
		System.out.println("id: "+id);
		Confirm currentConfirm = ConfirmBUS.getCurrentCancelConfirm(
				Integer.parseInt(id),"S", lang);
		// update current confirm
		if (currentConfirm != null) {
			System.out.println("currentConfirm: "+currentConfirm);
			currentConfirm.setIsCurrent("false");
			ConfirmBUS.update(currentConfirm, lang);
		}
			
		// save confirm for seller
		Confirm confirm = new Confirm();
		confirm.setOrderCancelID(Integer.parseInt(id));
		confirm.setObjects("S");
		confirm.setMemo(memoToCustomer);
		confirm.setIsCurrent("true");
		confirm.setUpdateDate(new Date());
		ConfirmBUS.insert(confirm, lang);

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
