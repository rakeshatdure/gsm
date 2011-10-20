package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ProductBUS;
import BUS.UserBUS;
import DAO.ExtensionDAO;
import DAO.InventoryDAO;
import DAO.ProductDAO;
import POJO.Extension;
import POJO.Inventory;
import POJO.Products;
import POJO.User;

/**
 * Servlet implementation class extensionController
 */
@WebServlet("/extensionController")
public class extensionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public extensionController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
        ServletContext app=getServletContext();;
		try {
			String btnInsert = (String) request.getParameter("btnInsert");
			int productId = Integer.parseInt(request.getParameter("txtProductId"));
			//int extensionId = Integer.parseInt(request.getParameter("txtInventoryId"));
			String limitDateOld = (String) request.getParameter("txtLimitDateOld");
			int selectLimitDateNum = Integer.parseInt( request.getParameter("selectLimitDateNum"));
			
			if (btnInsert != null) {
				boolean kq = ExtensionDAO.InsertExtension(productId, limitDateOld, selectLimitDateNum,0,"N",(String) app.getAttribute("MALL_LA"));
				
				if (kq == true) {
					request.setAttribute("Message1", "Request extension  successful");
					request.getRequestDispatcher("sale/inventory.jsp").forward(request, response);

				} else {
					request.setAttribute("Message1", "Request extension unsuccessful");
					request.getRequestDispatcher("sale/inventory.jsp").forward(request, response);
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
		try {
			processRequest(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
