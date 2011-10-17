package CONTROLLER;

import java.io.IOException;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import BUS.LanguegeBUS;
import BUS.OrdercancelBUS;
import BUS.ProductorderdetailBUS;
import POJO.Ordercancel;
import POJO.Productorderdetail;
import UTIL.ResourcesDefault;
import UTIL.TransactionMethod;

/**
 * Servlet implementation class CancelOrderController
 */
@WebServlet("/CancelOrderController")
public class CancelOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CancelOrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		try {
			final String reasonCancelSelect = request
					.getParameter("reasonCancelSelect");
			final String reasonCancelText = request
					.getParameter("reasonCancelText");
			final String orderDetailID = request.getParameter("orderDetailID");
			// save cancel order
			if (orderDetailID != null) {
				TransactionMethod tr = new TransactionMethod() {
					@Override
					protected void doMethod(Session session, String lang) {
						Productorderdetail productorderdetail = ProductorderdetailBUS
								.getProductorderdetail(
										Integer.parseInt(orderDetailID), lang);
						productorderdetail
								.setOrderDetailStatusId(ResourcesDefault.ODS_CANCEL);
						update(productorderdetail, session);

						Ordercancel orderCancel = new Ordercancel();
						orderCancel.setOrderDetaiId(Integer
								.parseInt(orderDetailID));
						orderCancel.setObject(ResourcesDefault.CANCEL_BUYER);
						orderCancel.setRequestDate(new Date());
						orderCancel.setCancelCause(reasonCancelSelect);
						orderCancel.setMemo(reasonCancelText);
						orderCancel.setCancelStatusId(1);
						save(orderCancel, session);
					}
				};
				boolean result = tr.executeTransaction(lang);
				if (result) {
					request.setAttribute("cancelSuccessfull",
							"cancelSuccessfull");

				}
				request.getRequestDispatcher(
						"/order_cancel_success.jsp?podid=" + orderDetailID)
						.forward(request, response);
			}

		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
