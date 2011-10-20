package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import BUS.DeliverBUS;
import BUS.NLPaymentBUS;
import BUS.ProductorderdetailBUS;
import POJO.Deliver;
import POJO.NLPayment;
import POJO.Productorderdetail;
import UTIL.NL_Checkout;
import UTIL.ServletUtils;
import UTIL.TransactionMethod;

/**
 * Servlet implementation class NLUpdateOrderController
 */
@WebServlet("/NLUpdateOrderController")
public class NLUpdateOrderController extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NLUpdateOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void process(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	HttpSession session=request.getSession();
    	final String basicURL=ServletUtils.getBaseUrl(request);
    	ServletContext app=getServletContext();;
    	String lang=(String) app.getAttribute("MALL_LA");
    	String login = (String) session.getAttribute("username");
		   if(login==null || login.isEmpty()){
				response.sendRedirect("../login.html");
		   } 
    	
    	String transactionInfo = request.getParameter("transaction_info");
		String paymentID = request.getParameter("payment_id");
		String paymentType = request.getParameter("payment_type");

		final String orderCode = request.getParameter("order_code");
		String secureCode = request.getParameter("secure_code");
		String refundPaymentId=request.getParameter("refund_payment_id");
		boolean valid=true;
//		if(transactionInfo==null||paymentID==null||paymentType==null||orderCode==null||
//				price==null||errorText==null||secureCode==null){
//			valid=false;
//		}
//		if(!valid){
//			 response.sendRedirect("unavailable.html");
//		}
//		else{
			
			boolean updateOrder = NL_Checkout.verifyUpdatePaymentUrl(transactionInfo,
					orderCode, paymentID, paymentType,	secureCode);
			if(updateOrder){
				System.out.println("transactionInfo: "+transactionInfo);
				System.out.println("orderCode: "+orderCode);
				if(refundPaymentId!=null){
					boolean refundPayment=NL_Checkout.verifyRefundPaymentUrl(transactionInfo, orderCode, paymentID, refundPaymentId, secureCode);
					if(refundPayment) updateOrder(paymentID,refundPaymentId,orderCode,lang);
				}
			}			
//		}
    }
	private void updateOrder(final String paymentID,final String refundPaymentId,final String orderDetailID,String lang) {
		TransactionMethod transactionMethod=new TransactionMethod(){
			@Override
			protected void doMethod(Session session, String lang) {
				NLPayment nlPayment=NLPaymentBUS.getNLPayment(Integer.parseInt(paymentID), lang);
				if(nlPayment!=null){
					//update nlpayment
					nlPayment.setRefundPayment(refundPaymentId);
					nlPayment.setUpdateDate(new Date());
					update(nlPayment,session);
					//update order
					Productorderdetail pODetail= ProductorderdetailBUS.getProductorderdetail(Integer.parseInt(orderDetailID), lang);
					pODetail.setOrderDetailStatusId(1);
					update(pODetail, session);
					
					Deliver deliver=DeliverBUS.getDeliverByPODetail(Integer.parseInt(orderDetailID), lang);
					deliver.setDeliverstatusID(1);
					update(deliver, session);
				}
			}
		};
		transactionMethod.executeTransaction(lang);
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
