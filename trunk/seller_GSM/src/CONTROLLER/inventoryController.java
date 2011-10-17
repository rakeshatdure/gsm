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

import BUS.ManufacturerBUS;
import BUS.ProductBUS;
import BUS.UserBUS;
import DAO.InventoryDAO;
import DAO.ProductDAO;
import POJO.Inventory;
import POJO.Manufacturer;
import POJO.Products;
import POJO.User;

/**
 * Servlet implementation class inventoryController
 */
@WebServlet("/inventoryController")
public class inventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public inventoryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
        ServletContext app=getServletContext();;
		try {
			List<Inventory> lstInven1 = InventoryDAO.lstInventory(
					(String) session.getAttribute("username"),
					(String) app.getAttribute("MALL_LA"));
			request.setAttribute("lstInven", lstInven1);
			if (lstInven1 == null || lstInven1.size() == 0) {
				request.setAttribute("Message", "Product not exist");
				request.getRequestDispatcher("sale/inventory.jsp").forward(
						request, response);
			} else {
				request.getRequestDispatcher("sale/inventory.jsp").forward(
						request, response);
			}

		} catch (Exception ex) {
			ex.getMessage();
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
