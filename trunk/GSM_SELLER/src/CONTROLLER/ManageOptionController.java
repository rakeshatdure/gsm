package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.OptionBUS;
import BUS.ProductBUS;
import BUS.UserBUS;
import DAO.OptionDAO;
import POJO.Options;
import POJO.Products;
import POJO.User;

/**
 * Servlet implementation class ManageOptionController
 */
@WebServlet("/ManageOptionController")
public class ManageOptionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageOptionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			ServletContext app = getServletContext();;
			String url = "";
			String login = (String) session.getAttribute("username");
			String lang = (String) app.getAttribute("MALL_LA");
			if (login == null || login.isEmpty()) {
				// request.setAttribute("Message","Please login to access system !");
				response.sendRedirect("../login.html");
			} else {

				// if (session.getAttribute("Role").equals("Seller")) {

				User user = (User) UserBUS.getUser(
						(String) session.getAttribute("username"), lang);
				String submit = request.getParameter("submitButton");
				if (submit != null) {
					if ("create".equals(submit)) {
						handleCreate(lang, request, response);
					} else if ("edit".equals(submit)) {
						handleEdit(lang, request, response);
					}

				}

				List<Products> lst = ProductBUS.lstProduct(user, lang);

				request.setAttribute("ProductPOJOs", lst);

				url = "/sale/manageoption.html";
				// } else {
				// request.setAttribute("Message",
				// "Accounts are not allowed to access!");
				// url = "/login.html";
				// }
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

	private void handleCreate(String lang, HttpServletRequest request,
			HttpServletResponse response) {
		String productID = request.getParameter("productID");
		if (productID != null) {
			Products product = ProductBUS.getProducts(
					Integer.parseInt(productID), lang);
			Options option = new Options();
			option.setProduct(product);
			option.setCreateDate(new Date());
			option.setDescription(request.getParameter("optionDescription"));
			option.setOptionPrice(Float.parseFloat(request
					.getParameter("optionPrice")));
			boolean result = OptionBUS.insert(option, lang);
			if (result == true) {
				request.setAttribute("productID", productID);
			}
		}

	}

	private void handleEdit(String lang, HttpServletRequest request,
			HttpServletResponse response) {
		String productID = request.getParameter("productID");
		String optionID = request.getParameter("optionID");
		if (optionID != null) {
			Options option = OptionBUS.getOptionByID(
					Integer.parseInt(optionID), lang);

			option.setDescription(request.getParameter("optionDescription"));
			option.setOptionPrice(Float.parseFloat(request
					.getParameter("optionPrice")));
			option.setUpdateDate(new Date());
			boolean result = OptionBUS.update(option, lang);
			if (result == true) {
				request.setAttribute("productID", productID);
			}
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
