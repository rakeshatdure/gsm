/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import POJO.*;
import UTIL.TransactionMethod;
import BUS.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

/**
 * 
 * @author admin
 */
@WebServlet(name = "checkoutController", urlPatterns = { "/checkoutController" })
public class checkoutController extends HttpServlet {

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
	public checkoutController() {
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		try {
			/*
			 * TODO output your page here out.println("<html>");
			 * out.println("<head>");
			 * out.println("<title>Servlet checkoutController</title>");
			 * out.println("</head>"); out.println("<body>");
			 * out.println("<h1>Servlet checkoutController at " +
			 * request.getContextPath () + "</h1>"); out.println("</body>");
			 * out.println("</html>");
			 */

			String url = "";
			String reciever = (String) request.getParameter("reciever");
			String phone = (String) request.getParameter("phone");
			String address = (String) request.getParameter("address");

			String transType = (String) request.getParameter("select");
			final ArrayList<Productorderdetail> cart = (ArrayList<Productorderdetail>) session
					.getAttribute("cart");
			if (session.getAttribute("username") == null) {
				response.sendRedirect("login.html");
			} else {
				if (cart != null) {
					float total = 0;
					for (int i = 0; i < cart.size(); i++) {
						total = total + cart.get(i).getTotalMoney();
					}
					Date d = new Date();
					final Productorder pOrder = new Productorder();
					String account = (String) session.getAttribute("username");
					User user = (User) UserBUS.getUser(account, lang);
					pOrder.setUser(user);
					pOrder.setTotalMoney(total);
					pOrder.setOrderDate(d);
					pOrder.setReciever(reciever);
					pOrder.setAddress(address);
					pOrder.setPhone(phone);

					Stateorder sOrder = (Stateorder) StateorderBUS
							.getStateorder(1, lang);
					pOrder.setStateorder(sOrder);

					Transport tp = (Transport) TransportBUS.getTransport(
							Integer.parseInt(transType), lang);
					pOrder.setTransport(tp);

					// final Productorder pd =
					// (Productorder)ProductorderBUS.getProductorderNew(lang);
					TransactionMethod tr = new TransactionMethod() {
						@Override
						protected void doMethod(Session session, String lang) {
							save(pOrder, session);
							for (int j = 0; j < cart.size(); j++) {
								Productorderdetail pdt = new Productorderdetail();
								// System.out.println("carsize: " +
								// cart.size());
								pdt.setProductorder(pOrder);
								pdt.setProducts(cart.get(j).getProducts());
								pdt.setProductNumber(cart.get(j)
										.getProductNumber());
								pdt.setTotalMoney(cart.get(j).getTotalMoney());
								pdt.setOrderDetailStatusId(1);
								if (cart.get(j).getOption() != null) {
									pdt.setOption(cart.get(j).getOption());
								}
								save(pdt, session);
								Deliver deliver = new Deliver();
								deliver.setProductOrderDetail(pdt);
								deliver.setDeliverstatusID(1);
								save(deliver, session);
							}
						}
					};
					boolean result = tr.executeTransaction(lang);
					if (result) {
						session.removeAttribute("cart");
						request.setAttribute("Message",
								"Please payment, we will deliver to your customers in the shortest time !");
						url = "/paymentmanage.html?id="
								+ pOrder.getProductOrderId();
					} else {
						request.setAttribute("Message",
								"Checkout Unsuccessfull !");
						url = "/checkout.html";
					}

				} else {
					url = "/cart.html";
				}
			}
//System.out.print("url: "+url);
			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher(url);
			rd.forward(request, response);

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
