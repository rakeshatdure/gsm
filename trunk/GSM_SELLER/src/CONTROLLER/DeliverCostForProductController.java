package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.DeliverBUS;
import BUS.DeliverCostBUS;
import BUS.ProductBUS;
import POJO.Deliver;
import POJO.DeliverCostView;
import POJO.Delivercost;
import POJO.Products;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class DeliverCostForProductController
 */
@WebServlet("/DeliverCostForProductController")
public class DeliverCostForProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NavigationInfo navInfo = new NavigationInfo();
	private NavigationInfo navInfo2 = new NavigationInfo();
	int item = 7, maxIndieces = 5;

	public NavigationInfo getNavInfo() {
		return navInfo;
	}

	public void setNavInfo(NavigationInfo navInfo) {

		this.navInfo = navInfo;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeliverCostForProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		String account = (String) session.getAttribute("username");
		if (account == null) {
			response.sendRedirect("../login.html");
		} else {
			// setting the paging
			navInfo.setPageSize(item);
			navInfo.setMaxIndices(maxIndieces);
			String page = (String) request.getParameter("page");
			if (null == page)
				navInfo.setCurrentPage(0);
			else
				navInfo.setCurrentPage(Integer.parseInt(page));
			// setting the paging
			// setting the paging
			navInfo2.setPageSize(item);
			navInfo2.setMaxIndices(maxIndieces);
			String page2 = (String) request.getParameter("page2");
			if (null == page2)
				navInfo2.setCurrentPage(0);
			else
				navInfo2.setCurrentPage(Integer.parseInt(page2));
			// setting the paging

			String whereSearch = handleSearch(request, response, lang);

			if (request.getParameter("saveDCostPOrder") != null) {
				handleMapDeAndDeCost(request, response, account, lang);

			} else {
				Map map2 = DeliverCostBUS.getListDeliverAndFeePaging(
						whereSearch, account, lang, navInfo2.getCurrentPage(),
						navInfo2.getPageSize());
				// System.out.println("rows: "+(Integer) map2.get("rows"));
				navInfo2.setRowCount((Integer) map2.get("rows"));
				request.setAttribute("pagedcust2", navInfo2);
				List<DeliverCostView> deliverCostViews = (List<DeliverCostView>) map2
						.get("list");
				request.setAttribute("deliverCostViews", deliverCostViews);

				// System.out.println("adfasdfasdfas");
				Map map = DeliverCostBUS.getListDeliverCostBySellerUse(account,
						lang, navInfo.getCurrentPage(), navInfo.getPageSize());
				List<Delivercost> deliverCosts = (List<Delivercost>) map
						.get("list");
				navInfo.setRowCount((Integer) map.get("rows"));
				request.setAttribute("pagedcust", navInfo);
				request.setAttribute("listDeliverCost", deliverCosts);

				request.getRequestDispatcher("/sale/delivercostforproduct.html")
						.forward(request, response);
			}

		}
	}

	public String handleSearch(HttpServletRequest request,
			HttpServletResponse response, String lang) {
		String whereSearch = "";
		if (request.getParameter("searchDeliverCosts") != null) {
			String selectCatogory = (String) request
					.getParameter("selectCatogory");
			String categorychildId = (String) request
					.getParameter("selectCatogoryChild");
			String categorysubId = (String) request
					.getParameter("selectCatogorySub");

			if (!"0".equals(selectCatogory)) {
				whereSearch += " and (select count(*) from categorychild cach where p.CategorySubId=cach.CategoryChildId and cach.CategoryId='"
						+ selectCatogory + "') >0 ";

				if (!"0".equals(categorychildId)) {
					whereSearch += " and p.CategoryChildId='" + categorychildId
							+ "' ";

					if (!"0".equals(categorysubId)) {
						whereSearch += " and p.CategorySubId='" + categorysubId
								+ "' ";
					}
				}
			}

		}
		String selectView = request.getParameter("selectView");
		if ("Product name".equals(selectView)) {
			whereSearch += " and p.ProductName  like '%"
					+ request.getParameter("contentView") + "%' ";
		}

		return whereSearch;
	}

	public void handleMapDeAndDeCost(HttpServletRequest request,
			HttpServletResponse response, String account, String lang)
			throws ServletException, IOException {
		int deCostIDSelect = -1;
		List<Integer> listProductsID = new ArrayList<Integer>();
		// Array of the deliver is checked.
		Map mapss = DeliverCostBUS.getListDeliverAndFeePaging("", account,
				lang, navInfo2.getCurrentPage(), navInfo2.getPageSize());
		List<DeliverCostView> deliverCostViews = (List<DeliverCostView>) mapss
				.get("list");
		for (DeliverCostView deliverCostView : deliverCostViews) {
			if ("on".equals(request.getParameter("deID_"
					+ deliverCostView.getProductID()))) {
				listProductsID.add(deliverCostView.getProductID());
			}
		}

		// The delivercost is checked.
		Map map = DeliverCostBUS.getListDeliverCostBySellerUse(account, lang,
				navInfo.getCurrentPage(), navInfo.getPageSize());
		List<Delivercost> deliverCosts = (List<Delivercost>) map.get("list");
		for (Delivercost delivercost : deliverCosts) {
			if ((delivercost.getDeliverCostId() + "").equals(request
					.getParameter("de_CostID"))) {
				deCostIDSelect = delivercost.getDeliverCostId();
			}
		}

		// map the delivercostid with the array of the deliver
		Delivercost delivercostSelect = DeliverCostBUS.getDeliverCostById(
				deCostIDSelect, lang);
		for (int i = 0; i < listProductsID.size(); i++) {
			// System.out.println("listProductsID: "+listProductsID.get(i));
			Products products = ProductBUS.getProducts(listProductsID.get(i),
					lang);
			products.setDeliverCost(delivercostSelect);
			ProductBUS.updateProducts(products, lang);
		}

		Map map2 = DeliverCostBUS.getListDeliverAndFeePaging("", account, lang,
				navInfo2.getCurrentPage(), navInfo2.getPageSize());
		// System.out.println("rows: "+(Integer) map2.get("rows"));
		navInfo2.setRowCount((Integer) map2.get("rows"));
		request.setAttribute("pagedcust2", navInfo2);
		List<DeliverCostView> list = (List<DeliverCostView>) map2.get("list");
		request.setAttribute("deliverCostViews", list);

		Map maps = DeliverCostBUS.getListDeliverCostBySellerUse(account, lang,
				navInfo.getCurrentPage(), navInfo.getPageSize());
		List<Delivercost> listDeliverCosts = (List<Delivercost>) maps
				.get("list");
		navInfo.setRowCount((Integer) maps.get("rows"));
		request.setAttribute("pagedcust", navInfo);
		request.setAttribute("listDeliverCost", listDeliverCosts);
		request.setAttribute("showTab2", "show");

		request.getRequestDispatcher("/sale/delivercostforproduct.html")
				.forward(request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
