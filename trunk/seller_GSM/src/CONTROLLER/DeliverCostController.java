package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NumberUtils;

import BUS.DeliverBUS;
import BUS.DeliverCostBUS;
import BUS.UserBUS;
import DAO.DeliverCostDAO;
import POJO.Deliver;
import POJO.DeliverCostView;
import POJO.Delivercost;
import POJO.User;
import UTIL.NavigationInfo;
import UTIL.ResourcesDefault;

/**
 * Servlet implementation class DeliverCostController
 */
@WebServlet("/DeliverCostController")
public class DeliverCostController extends HttpServlet {
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
	public DeliverCostController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		String account = (String) session.getAttribute("username");
		if (account == null) {
			response.sendRedirect("../login.html");
		} else {
			// setting the paging
			navInfo.setPageSize(item);
			navInfo.setMaxIndices(5);
			String page = (String) request.getParameter("page");
			if (null == page)
				navInfo.setCurrentPage(0);
			else
				navInfo.setCurrentPage(Integer.parseInt(page));
			// setting the paging

			String lang = (String) app.getAttribute("MALL_LA");
			PrintWriter out = response.getWriter();
			String save = request.getParameter("save");
			if (save != null && "save".equals(save)) {
				handleSave(request, response, account, lang);

			} else if (save != null && "edit".equals(save)) {
				String deliverCostID = request
						.getParameter("deliverCostIDEdit");

				handleEdit(request, response, deliverCostID, account, lang);
				navInfo.setCurrentPage(0);

			}

			// load data
			Map map = DeliverCostBUS.getListDeliverCostBySellerPaging(account,
					lang, navInfo.getCurrentPage(), navInfo.getPageSize());
			List<Delivercost> deliverCosts = (List<Delivercost>) map
					.get("list");
			navInfo.setRowCount((Integer) map.get("rows"));
			request.setAttribute("pagedcust", navInfo);
			request.setAttribute("listDeliverCost", deliverCosts);

			request.getRequestDispatcher("/sale/delivercost.html").forward(
					request, response);

		}
	}

	public void handleSave(HttpServletRequest request,
			HttpServletResponse response, String account, String lang)
			throws IOException {

		String deliverName = request.getParameter("deliverName");
		String typeFee = request.getParameter("typeFee");
		float cost = 0;
		float feeExtra = 0;
		float conditionForFree = 0;
		String userornot = "Y";
		String deliverCostDefault = ResourcesDefault.FALSE;
		if (ResourcesDefault.FREE.equals(typeFee)) {

		} else if (ResourcesDefault.HAS_FEE.equals(typeFee)) {
			String costStr = request.getParameter("cost1");
			if (costStr != null && NumberUtils.isNumber(costStr)) {
				cost = Float.parseFloat(costStr);
			}
			userornot = request.getParameter("yesno1");
		} else if (ResourcesDefault.FEE_HAS_CONDITION.equals(typeFee)) {
			String costStr = request.getParameter("cost2");
			if (costStr != null && NumberUtils.isNumber(costStr)) {
				cost = Float.parseFloat(costStr);
			}
			userornot = request.getParameter("yesno2");
			String conditionFee = request.getParameter("conditionFee2");
			if (conditionFee != null && NumberUtils.isNumber(conditionFee)) {
				conditionForFree = Float.parseFloat(conditionFee);
			}
		} else if (ResourcesDefault.PREPAY.equals(typeFee)) {
			String costStr = request.getParameter("cost3");
			if (costStr != null && NumberUtils.isNumber(costStr)) {
				cost = Float.parseFloat(costStr);
			}
			if (request.getParameter("userConditionFree") != null
					&& request.getParameter("userConditionFree") != "") {

				String conditionFee = request.getParameter("conditionFee3");
				if (conditionFee != null && NumberUtils.isNumber(conditionFee)) {
					conditionForFree = Float.parseFloat(conditionFee);
				}
			}
			userornot = request.getParameter("yesno3");
		}

		if ("Y".equals(userornot)) {
			userornot = ResourcesDefault.YES;
		} else {
			userornot = ResourcesDefault.NO;
		}
		String extraCost = request.getParameter("extraCost");
		if (extraCost != null && extraCost != "null" && extraCost != "") {
			feeExtra = Float.parseFloat(extraCost);
		}
		String feeDefault = request.getParameter("feeDefault");
		if ("on".equals(feeDefault)) {
			deliverCostDefault = ResourcesDefault.TRUE;
			// update the current deliverCostDefault]
			DeliverCostBUS.updateDeliverCostDefault(account, account, lang);

		}
		User seller = UserBUS.getUser(account, lang);
		DeliverCostBUS
				.insert(seller, deliverName, cost, new Date(), typeFee,
						feeExtra, conditionForFree, userornot,
						deliverCostDefault, lang);

		// response.sendRedirect("sale/delivercost.html");

	}

	public void handleEdit(HttpServletRequest request,
			HttpServletResponse response, String deliverCostID, String account,
			String lang) throws IOException {

		String deliverName = request.getParameter("deliverName");
		String typeFee = request.getParameter("typeFee");
		float cost = 0;
		float feeExtra = 0;
		float conditionForFree = 0;
		String userornot = "Y";
		String deliverCostDefault = ResourcesDefault.FALSE;
		if (ResourcesDefault.FREE.equals(typeFee)) {

		} else if (ResourcesDefault.HAS_FEE.equals(typeFee)) {
			String costStr = request.getParameter("cost1");
			if (costStr != null && NumberUtils.isNumber(costStr)) {
				cost = Float.parseFloat(costStr);
			}
			userornot = request.getParameter("yesno1");
		} else if (ResourcesDefault.FEE_HAS_CONDITION.equals(typeFee)) {
			String costStr = request.getParameter("cost2");
			if (costStr != null && NumberUtils.isNumber(costStr)) {
				cost = Float.parseFloat(costStr);
			}
			userornot = request.getParameter("yesno2");
			String conditionFee = request.getParameter("conditionFee2");
			if (conditionFee != null && NumberUtils.isNumber(conditionFee)) {
				conditionForFree = Float.parseFloat(conditionFee);
			}
		} else if (ResourcesDefault.PREPAY.equals(typeFee)) {
			String costStr = request.getParameter("cost3");
			if (costStr != null && NumberUtils.isNumber(costStr)) {
				cost = Float.parseFloat(costStr);
			}
			if (request.getParameter("userConditionFree") != null
					&& request.getParameter("userConditionFree") != "") {

				String conditionFee = request.getParameter("conditionFee3");
				if (conditionFee != null && NumberUtils.isNumber(conditionFee)) {
					conditionForFree = Float.parseFloat(conditionFee);
				}
			}
			userornot = request.getParameter("yesno3");
		}

		if ("Y".equals(userornot)) {
			userornot = ResourcesDefault.YES;
		} else {
			userornot = ResourcesDefault.NO;
		}
		String extraCost = request.getParameter("extraCost");
		if (extraCost != null && extraCost != "null" && extraCost != "") {
			feeExtra = Float.parseFloat(extraCost);
		}
		String feeDefault = request.getParameter("feeDefault");

		User seller = UserBUS.getUser(account, lang);

		try {
			Delivercost deliverCost = DeliverCostBUS.getDeliverCostById(
					Integer.parseInt(deliverCostID), lang);
			deliverCost.setSeller(seller);
			deliverCost.setDeliverCostName(deliverName);
			deliverCost.setCost(cost);
			deliverCost.setUpdateDate(new Date());
			deliverCost.setTypeFee(typeFee);
			deliverCost.setFeeExtra(feeExtra);
			deliverCost.setConditionForFree(conditionForFree);
			deliverCost.setIsUser(userornot);
			if ("on".equals(feeDefault)) {
				deliverCostDefault = ResourcesDefault.TRUE;
				// update the current deliverCostDefault]
				DeliverCostBUS.updateDeliverCostDefault(account, deliverCostID,
						lang);
			}
			deliverCost.setDeliverCostDefault(deliverCostDefault);

			DeliverCostBUS.update(deliverCost, lang);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// response.sendRedirect("sale/delivercost.html");
		// request.getRequestDispatcher("/sale/delivercost.html").forward(
		// request, response);
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
