/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import POJO.*;
import BUS.*;
import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author admin
 */
@WebServlet(name = "cartController", urlPatterns = { "/cartController" })
public class cartController extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			/*
			 * TODO output your page here out.println("<html>");
			 * out.println("<head>");
			 * out.println("<title>Servlet cartController</title>");
			 * out.println("</head>"); out.println("<body>");
			 * out.println("<h1>Servlet cartController at " +
			 * request.getContextPath () + "</h1>"); out.println("</body>");
			 * out.println("</html>");
			 */
			HttpSession session = request.getSession();
			ServletContext app = getServletContext();;
			String lang = (String) app.getAttribute("MALL_LA");
			ArrayList<Productorderdetail> cart = new ArrayList<Productorderdetail>();
			Productorderdetail ct = new Productorderdetail();
			Products p = new Products();
			String action = request.getParameter("action");
			String update = request.getParameter("update");
			if (session.getAttribute("username") == null) {
				response.sendRedirect("login.html");
			} else {
				if (session.getAttribute("cart") == null) {
					session.setAttribute("cart", cart);
				}
				cart = (ArrayList<Productorderdetail>) session
						.getAttribute("cart");
				if (update != null) {
					for (int i = 0; i < cart.size(); i++) {
						String sl = String.valueOf(request
								.getParameter("number"));
						int soluong = Integer.parseInt(sl);
						int productId = Integer.parseInt(request
								.getParameter("Id"));
						p = ProductBUS.getProducts(productId, lang);
						if (productId == cart.get(i).getProducts()
								.getProductId()) {
							cart.get(i).setProductNumber(soluong);
							cart.get(i).setTotalMoney(soluong * p.getPrice());
						}
					}
				} else if (action.equals("add") && cart.isEmpty()) {

					int Id = Integer.parseInt(request.getParameter("pId"));
					p = ProductBUS.getProducts(Id, lang);

					ct.setProducts(p);
					ct.setProductNumber(1);
					ct.setTotalMoney(p.getPrice());
					String oID = request.getParameter("selectOption");
					// System.out.println("oID1: "+oID);
					if (oID != null && !"0".equals(oID)) {
						Options option = OptionBUS.getOptionByID(
								Integer.parseInt(oID), lang);
						ct.setOption(option);
						// System.out.println("optionid1: "+option.getOptionId());
					}

					cart.add(ct);
				} else if (action.equals("add")) {
					String oID = request.getParameter("selectOption");
					int Id = Integer.parseInt(request.getParameter("pId"));
					int flag = 0;
					p = ProductBUS.getProducts(Id, lang);
					ct.setProducts(p);
					for (int i = 0; i < cart.size(); i++) {

						if (cart.get(i).getProducts().getProductId() == ct
								.getProducts().getProductId()) { // If a product
																	// has
																	// existed
																	// thus
																	// increases
																	// the
																	// amount to
																	// 1
							if (oID != null && !"0".equals(oID)) {
								if (cart.get(i).getOption() != null
										&& (cart.get(i).getOption()
												.getOptionId() + "")
												.equals(oID)) {
									cart.get(i).setProductNumber(
											cart.get(i).getProductNumber() + 1);
									cart.get(i)
											.setTotalMoney(
													(cart.get(i)
															.getProductNumber() + 1)
															* p.getPrice());
									flag = 1;
								}

							} else {
								if (cart.get(i).getOption() == null) {
									cart.get(i).setProductNumber(
											cart.get(i).getProductNumber() + 1);
									cart.get(i)
											.setTotalMoney(
													(cart.get(i)
															.getProductNumber() + 1)
															* p.getPrice());
									flag = 1;
								}
							}
						}
					}
					if (flag == 0) {
						ct.setProductNumber(1);
						ct.setTotalMoney(p.getPrice());

						// System.out.println("oID2: "+oID);
						if (oID != null && !"0".equals(oID)) {
							Options option = OptionBUS.getOptionByID(
									Integer.parseInt(oID), lang);
							ct.setOption(option);
							// System.out.println("optionid2: "+option.getOptionId());
						}
						cart.add(ct);
					}
				} else if (action.equals("remove")) {
					int productId = Integer.parseInt(request
							.getParameter("pId"));
					for (int i = 0; i < cart.size(); i++) {
						if (productId == cart.get(i).getProducts()
								.getProductId()) {
							cart.remove(i);
						}
					}
					if (cart.isEmpty()) {
						cart = null;
					}
				} else if (action.equals("removeall")) {
					cart = null;
				}
				session.setAttribute("cart", cart);
				response.sendRedirect("cart.html");
			}
		} catch (Exception ex) {
			out.println(ex.getMessage());

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
		processRequest(request, response);
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
		processRequest(request, response);
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
