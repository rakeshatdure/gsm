package CONTROLLER.OrderManager;

import java.io.IOException;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import BUS.ExchangeOrderBUS;
import BUS.ExchangeStatusBUS;
import BUS.OrdercancelBUS;
import BUS.ProductorderdetailBUS;
import POJO.Exchangeorder;
import POJO.Exchangestatus;
import POJO.Ordercancel;
import POJO.Productorderdetail;
import UTIL.ResourcesDefault;
import UTIL.TransactionMethod;

/**
 * Servlet implementation class ReturnExchangeController
 */
@WebServlet("/ReturnExchangeController")
public class ReturnExchangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnExchangeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();
		
		String lang = (String) app.getAttribute("MALL_LA");
		System.out.println("langl: "+lang);
		try {
			final String reasonSelect = request.getParameter("reasonSelect");
			final String reasonText = request.getParameter("reasonText");
			final String orderDetailID = request.getParameter("orderDetailID");
			// save cancel order
			if (orderDetailID != null) {
				TransactionMethod tr = new TransactionMethod() {
					@Override
					protected void doMethod(Session session, String lang) {

						Productorderdetail productorderdetail = ProductorderdetailBUS
								.getProductorderdetail(
										Integer.parseInt(orderDetailID), lang);
						System.out.println("lang: "+lang);
						//System.out.println("productorderdetail: "+productorderdetail.getOrderDetailStatusId());
						productorderdetail
								.setOrderDetailStatusId(ResourcesDefault.ODS_RETURN_EXCHANGE);
						//System.out.println("productorderdetail: "+productorderdetail.getOrderDetailStatusId());
						update(productorderdetail, session);

						Exchangeorder exchangeorder = new Exchangeorder();
						exchangeorder.setMemoToSeller(reasonText);
						exchangeorder.setReason(reasonSelect);
						exchangeorder.setOrderDetail(productorderdetail);
						Exchangestatus exchangStatus = ExchangeStatusBUS
								.getExchangeStatus(1, lang);
						exchangeorder.setExchangeStatus(exchangStatus);
						exchangeorder.setRequiredDate(new Date());

						save(exchangeorder, session);
					}
				};
				System.out.println("langL "+lang);
				boolean result = tr.executeTransaction(lang);
				if (result) {
					request.setAttribute("sucessfull", "successfull");
				}
				request.getRequestDispatcher("returnexchangesuccessfull.html")
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
