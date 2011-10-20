package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
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

import BUS.*;
import POJO.*;
import UTIL.TransactionMethod;

/**
 * Servlet implementation class paymentControler
 */
@WebServlet("/paymentControler")
public class paymentControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public paymentControler() {
		super();
		// TODO Auto-generated constructor stub
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
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			/*
			 * TODO output your page here out.println("<html>");
			 * out.println("<head>");
			 * out.println("<title>Servlet logoutController</title>");
			 * out.println("</head>"); out.println("<body>");
			 * out.println("<h1>Servlet logoutController at " +
			 * request.getContextPath () + "</h1>"); out.println("</body>");
			 * out.println("</html>");
			 */
			String url = "";
			String act = request.getParameter("act");
			String orderId = request.getParameter("orderId");
			final String pMethod = request.getParameter("pType");
			// System.out.println("pMethod"+pMethod);
			ServletContext app = getServletContext();;
			String lang = (String) app.getAttribute("MALL_LA");
			if (null != orderId) {

				final Productorderdetail pDetail = ProductorderdetailBUS
						.getProductorderdetail(Integer.parseInt(orderId), lang);
				if (null != act && act.equals("CK")) {

					TransactionMethod tr = new TransactionMethod() {
						@Override
						protected void doMethod(Session session, String lang) {

							// /Set orderdetail status is shipping
							pDetail.setOrderDetailStatusId(2);
							// boolean kqCK =
							// ProductorderdetailBUS.updateProductorderdetail(pDetail,lang);
							update(pDetail, session);

							Payment p = new Payment();
							p.setOrderDetailId(pDetail
									.getProductOrderDetailId());
							// Set payment method
							p.setPaymentMethodId(Integer.parseInt(pMethod));
							Date d = new Date();
							p.setPaymentDate(d);
							// boolean kqPM = PaymentBUS.insertPayment(p,
							// (String)session.getAttribute("MALL_LA"));
							save(p, session);

							Deliver deliver = DeliverBUS.getDeliverByPODetail(
									pDetail.getProductOrderDetailId(), lang);
							deliver.setDeliverstatusID(2);
							// DeliverBUS.update(deliver,
							// (String)session.getAttribute("MALL_LA"));
							update(deliver, session);
						}
					};
					boolean result = tr.executeTransaction(lang);
					if (result) {
						request.setAttribute("Message",
								"Payment with tranfer Successful!");
					} else {
						request.setAttribute("Message",
								"Payment with tranfer Unsuccessful!");
					}
					url = "/paymentmanage.html?id="
							+ pDetail.getProductorder().getProductOrderId();

				}
			} else {

			}

			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher(url);
			rd.forward(request, response);

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
