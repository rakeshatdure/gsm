package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ProductBUS;
import BUS.UserBUS;
import POJO.Products;
import POJO.User;
import UTIL.DateUtil;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class sellerProductController
 */
@WebServlet("/sellerProductController")
public class sellerProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sellerProductController() {
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
		ServletContext app = getServletContext();;
		try {
			String url = "";
			String login = (String) session.getAttribute("username");
			if (login == null || login.isEmpty()) {
				// request.setAttribute("Message","Please login to access system !");
				response.sendRedirect("../login.html");
			} else {

				if (session.getAttribute("Role").equals("Seller")) {
					String lang = (String) app.getAttribute("MALL_LA");

					String btn = request.getParameter("btnSearch");
					User user = (User) UserBUS.getUser(
							(String) session.getAttribute("username"), lang);
					List<Products> lst = null;
					if (null == btn || btn.isEmpty()) {
						
						lst = ProductBUS.lstProduct(user, lang);
					} else {
						SimpleDateFormat dayFormat = new SimpleDateFormat(
								"yyyy-MM-dd");
						String child = request.getParameter("selectCate");
						String sub = request.getParameter("selectSub");
						String search = request.getParameter("txtSearch");
						String fromDate = request.getParameter("fromDate");
						String toDate = request.getParameter("toDate");

						// System.out.println(fromDate);
						// System.out.println(DateUtil.convertStringToDate(fromDate,
						// "yyyy-MM-dd"));
						lst = ProductBUS.advancedSearchProduct((String) session
								.getAttribute("username"), child, sub, search,
								DateUtil.convertStringToDate(fromDate,
										"yyyy-MM-dd"), DateUtil
										.convertStringToDate(toDate,
												"yyyy-MM-dd"), lang);
					}

					request.setAttribute("ProductPOJOs", lst);

					url = "/sale/upload.jsp";
				} else {
					request.setAttribute("Message",
							"Accounts are not allowed to access!");
					url = "/login.html";
				}
			}

			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception ex) {
			out.println(ex.getMessage());
		} finally {
			out.close();
		}
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
