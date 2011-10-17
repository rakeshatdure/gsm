/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import DAO.ProductDAO;
import DAO.ProductorderdetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import POJO.*;
import UTIL.DateUtil;
import UTIL.NavigationInfo;

import java.text.DateFormat;
import java.util.Date;
import DAO.*;
import BUS.*;

/**
 * 
 * @author Admin
 */
@WebServlet(name = "StatisticsController", urlPatterns = { "/StatisticsController" })
public class StatisticsController extends HttpServlet {

	private NavigationInfo navInfo = new NavigationInfo();

	public NavigationInfo getNavInfo() {
		return navInfo;
	}

	public void setNavInfo(NavigationInfo navInfo) {

		this.navInfo = navInfo;
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
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		try {
			/*
			 * TODO output your page here out.println("<html>");
			 * out.println("<head>");
			 * out.println("<title>Servlet StatisticsController</title>");
			 * out.println("</head>"); out.println("<body>");
			 * out.println("<h1>Servlet StatisticsController at " +
			 * request.getContextPath () + "</h1>"); out.println("</body>");
			 * out.println("</html>");
			 */
			// DateFormat dateformat = null;
			if (session.getAttribute("username") == null
					|| !session.getAttribute("Role").equals("Seller")) {
				request.setAttribute("Message",
						"Please login to access system !");
				request.getRequestDispatcher("/login.html").forward(request,
						response);

			} else {
				ServletContext app = getServletContext();;
				String lang = (String) app.getAttribute("MALL_LA");

				navInfo.setPageSize(10);
				String page = (String) request.getParameter("page");
				if (null == page)
					navInfo.setCurrentPage(0);
				else
					navInfo.setCurrentPage(Integer.parseInt(page));

				String fromD = String.valueOf(request
						.getParameter("datepicker1"));
				String toD = String
						.valueOf(request.getParameter("datepicker2"));
				String SellerAcount = String.valueOf(session
						.getAttribute("username"));
				// Date dateFrom = DateUtil.getDateFromString(fromD);
				// Date dateTo=DateUtil.getDateFromString(toD);

				if (request.getParameter("Search") != null) {
					List<Productorderdetail> lstProductDetail = ProductorderdetailBUS
							.lstProductSeller(fromD, toD, SellerAcount,
									navInfo.getCurrentPage(),
									navInfo.getPageSize(), lang);
					request.setAttribute("lstProductSeller", lstProductDetail);
					if (lstProductDetail == null
							|| lstProductDetail.size() == 0) {

						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("Statistics.jsp").forward(
								request, response);
					} else {
						navInfo.setRowCount(ProductorderdetailBUS
								.lstProductSeller(fromD, toD, SellerAcount,
										lang).size());
						session.setAttribute("pagedcust", navInfo);
						session.setAttribute("selldate", "datepicker1=" + fromD
								+ "&datepicker2=" + toD + "&Search=search"
								+ "&");
						request.getRequestDispatcher("Statistics.jsp").forward(
								request, response);
					}
				} else {
					List<Productorderdetail> listProductAcount = ProductorderdetailBUS
							.lstProductAcount(SellerAcount,
									navInfo.getCurrentPage(),
									navInfo.getPageSize(), lang);
					request.setAttribute("lstProductSeller", listProductAcount);
					if (listProductAcount == null
							|| listProductAcount.size() == 0) {

						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("Statistics.jsp").forward(
								request, response);
					} else {
						navInfo.setRowCount(ProductorderdetailBUS
								.lstProductAcount(SellerAcount, lang).size());
						session.setAttribute("pagedcust", navInfo);
						session.setAttribute("selldate", null);
						request.getRequestDispatcher("Statistics.jsp").forward(
								request, response);
					}
				}
			}
		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
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
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ParseException ex) {
			Logger.getLogger(StatisticsController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
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
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ParseException ex) {
			Logger.getLogger(StatisticsController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
