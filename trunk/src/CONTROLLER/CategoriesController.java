package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.CategoryBUS;
import BUS.ProductBUS;
import POJO.Category;
import POJO.Productorderdetail;
import POJO.Products;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class CategoriesController
 */
@WebServlet("/CategoriesController")
public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NavigationInfo navInfo = new NavigationInfo();
	private int item = 15;

	public NavigationInfo getNavInfo() {
		return navInfo;
	}

	public void setNavInfo(NavigationInfo navInfo) {

		this.navInfo = navInfo;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriesController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryID = request.getParameter("caID");
		ServletContext app = getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		// setting the paging
		navInfo.setPageSize(item);
		navInfo.setMaxIndices(5);
		String page = (String) request.getParameter("page");

		if (null == page)
			navInfo.setCurrentPage(0);
		else
			navInfo.setCurrentPage(Integer.parseInt(page));
		// setting the paging
		
		Category category = CategoryBUS.getCategory(categoryID, lang);
		Map map = ProductBUS.getListProductByCategoryPaging(category.getCategoryId(),navInfo.getCurrentPage(),navInfo.getPageSize(), lang);
		List<Products> products=(List<Products>) map.get("list");
		
		navInfo.setRowCount((Integer) map.get("rows"));
		
		request.setAttribute("pagedcust", navInfo);
		request.setAttribute("products", products);
		request.getRequestDispatcher("categories.html?caID="+categoryID).forward(request, response);
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
