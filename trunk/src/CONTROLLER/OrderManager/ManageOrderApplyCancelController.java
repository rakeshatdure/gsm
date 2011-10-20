package CONTROLLER.OrderManager;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BUS.ProductorderdetailBUS;
import POJO.Productorderdetail;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class ManageOrderApplyCancelController
 */
@WebServlet("/ManageOrderApplyCancelController")
public class ManageOrderApplyCancelController extends HttpServlet {
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
	public ManageOrderApplyCancelController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		ServletContext app = getServletContext();
		String user = (String) session.getAttribute("username");
		String lang = (String) app.getAttribute("MALL_LA");

		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		// setting for paging
		navInfo.setPageSize(item);
		navInfo.setMaxIndices(2);
		String page = (String) request.getParameter("page");
		if (null == page)
			navInfo.setCurrentPage(0);
		else
			navInfo.setCurrentPage(Integer.parseInt(page));
		// setting for paging

		List<Productorderdetail>[] arrays = ProductorderdetailBUS
				.getListPODetailApplyCancelByBuyer(user, lang, fromDate,
						toDate, navInfo.getCurrentPage(), navInfo.getPageSize());
		navInfo.setRowCount(arrays[0] != null ? arrays[0].size() : 0);
		request.setAttribute("pagedcust", navInfo);
		request.setAttribute("listOrder", arrays[1]);
		request.getRequestDispatcher("/orderapplycancel.html").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
