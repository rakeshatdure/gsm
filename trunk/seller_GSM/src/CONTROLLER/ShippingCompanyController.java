package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ShippingCompanyBUS;
import BUS.UserBUS;
import POJO.Shippingcompany;
import POJO.User;

/**
 * Servlet implementation class ShippingCompanyController
 */
@WebServlet("/ShippingCompanyController")
public class ShippingCompanyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShippingCompanyController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String seller = (String) session.getAttribute("username");
		ServletContext app=getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		if (seller == null) {
			response.sendRedirect("../login.html");
		} else {
			String submit = request.getParameter("submit");
			if (submit != null) {
				String shID = request.getParameter("shID");
				String shName = request.getParameter("shName");
				String shPhone = request.getParameter("shPhone");
				String shAddress = request.getParameter("shAddress");
				String shFax = request.getParameter("shFax");
				String shWeb = request.getParameter("shWeb");
				String shEmail = request.getParameter("shEmail");
				if ("shUpdate".equals(submit)) {
					Shippingcompany shcom = ShippingCompanyBUS
							.getShippingcompany(Integer.parseInt(shID), lang);
					shcom.setShippingCompanyName(shName);
					shcom.setAddress(shAddress);
					shcom.setPhone(shPhone);
					shcom.setFax(shFax);
					shcom.setWeb(shWeb);
					shcom.setEmail(shEmail);
					ShippingCompanyBUS.updateShippingCompany(shcom, lang);
				} else if ("shCreate".equals(submit)) {
					Shippingcompany shcom = new Shippingcompany();
					shcom.setShippingCompanyName(shName);
					shcom.setAddress(shAddress);
					shcom.setPhone(shPhone);
					shcom.setFax(shFax);
					shcom.setWeb(shWeb);
					shcom.setEmail(shEmail);
					User sellerAccoutnt = UserBUS.getUser(seller, lang);
					shcom.setSeller(sellerAccoutnt);
					ShippingCompanyBUS.insertShippingCompanỵ̣(shcom, lang);
				}
			}

			String search = request.getParameter("search");
			String selectSearch = request.getParameter("selectSearch");	
			if (search != null && !("0".equals(selectSearch))) {						
					String searchValue = request.getParameter("searchValue");
					List<Shippingcompany> shippingCompanys = null;
					if ("name".equals(selectSearch)) {
						shippingCompanys = ShippingCompanyBUS
								.getListShippingcompanyBySellerSearch(seller,
										"shippingCompanyName", searchValue, lang);
					} else if ("address".equals(selectSearch)) {
						shippingCompanys = ShippingCompanyBUS
								.getListShippingcompanyBySellerSearch(seller,
										"address", searchValue, lang);
					}
					request.setAttribute("shippingCompanys", shippingCompanys);
									

			} else {
				List<Shippingcompany> shippingCompanys = ShippingCompanyBUS
						.getListShippingcompanyBySeller(seller, "", lang);
				request.setAttribute("shippingCompanys", shippingCompanys);

			}
			request.getRequestDispatcher("/sale/shippingcompany.html").forward(
					request, response);
		}
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
