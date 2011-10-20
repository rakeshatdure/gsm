package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.CategoryBUS;
import BUS.ProductBUS;
import BUS.UserBUS;
import DAO.BankDAO;
import DAO.CompanyDAO;
import DAO.ProductDAO;
import POJO.Bank;
import POJO.Category;
import POJO.Company;
import POJO.Products;
import POJO.User;

/**
 * Servlet implementation class amountPriceController
 */
@WebServlet("/amountPriceController")
public class amountPriceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public amountPriceController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		try {
			String login = (String) session.getAttribute("username");
			if (login == null || login.isEmpty()) {
				response.sendRedirect("../login.html");
			} else {
				String lang = (String) app.getAttribute("MALL_LA");

				User user = UserBUS.getUser(
						(String) session.getAttribute("username"), lang);
				List<Products> lstProduct = ProductDAO.lstProduct(user, lang);
				request.setAttribute("lstProduct", lstProduct);
				String btnAmout = request.getParameter("btnAmout");
				if (btnAmout == null) {

					request.getRequestDispatcher("/sale/amountprice.html")
							.forward(request, response);
				}

				else {

					int txtId = Integer.parseInt(request
							.getParameter("txtIdpro"));
					Products p = ProductBUS.getProducts(txtId, lang);
					int txtAmount = Integer.parseInt(request
							.getParameter("txtAmount"));
					float txtPrice = Float.valueOf(request
							.getParameter("txtPrice"));
					p.setAmount(txtAmount);
					p.setPrice(txtPrice);
					boolean kq = ProductDAO.updateProducts(p, lang);
					if (kq == true) {
						// request.setAttribute("Message",
						// "Update information of seller successfull !");
						response.sendRedirect("sale/amountprice.html");
					} else {
						request.getRequestDispatcher("/sale/amountprice.jsp")
								.forward(request, response);
					}

				}

			}

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
