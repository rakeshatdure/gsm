package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import BUS.DeliverBUS;
import BUS.DeliverCostBUS;
import BUS.DeliverStatusBUS;
import BUS.LanguegeBUS;
import BUS.ManufacturerBUS;
import BUS.OptionBUS;
import BUS.ProductBUS;
import BUS.ProductorderBUS;
import BUS.ProductorderdetailBUS;
import BUS.ShippingCompanyBUS;
import BUS.UserBUS;
import POJO.Deliver;
import POJO.Deliverstatus;
import POJO.Manufacturer;
import POJO.NLPayment;
import POJO.Options;
import POJO.Payment;
import POJO.Productorder;
import POJO.Productorderdetail;
import POJO.Products;
import POJO.User;
import UTIL.NL_Checkout;
import UTIL.ResourcesDefault;
import UTIL.ServletUtils;
import UTIL.TransactionMethod;

/**
 * Servlet implementation class NLCompletedController
 */
@WebServlet("/NLCompletedController")
public class NLCompletedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NLCompletedController() {
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
    	
    	final String transactionInfo = request.getParameter("transaction_info");
		final String paymentID = request.getParameter("payment_id");
		final String paymentType = request.getParameter("payment_type");

		final String orderCode = request.getParameter("order_code");
		String price = request.getParameter("price");
		String errorText = request.getParameter("error_text");
		String secureCode = request.getParameter("secure_code");	
		System.out.println("valid: "+transactionInfo+paymentID+paymentType+orderCode+price+errorText+secureCode);
		boolean valid=true;
		if(transactionInfo==null||paymentID==null||paymentType==null||orderCode==null||
				price==null||errorText==null||secureCode==null){
			System.out.println("invalid");
			valid=false;
		}
		if(!valid){
			 response.sendRedirect("unavailable.html");
		}
		else{
			
			boolean payment = NL_Checkout.verifyPaymentUrl(transactionInfo,
					orderCode, price, paymentID, paymentType, errorText,
					secureCode);
			if(payment){
				//save payment order (update the orderdetailstatus is shipping and the deliverstatus is on request)
				TransactionMethod transaction=new TransactionMethod(){
					@Override
					protected void doMethod(Session session, String lang) {
						//update orderstatus
						Productorderdetail pODetail= ProductorderdetailBUS.getProductorderdetail(Integer.parseInt(orderCode), lang);
						pODetail.setOrderDetailStatusId(2);
						update(pODetail, session);
						
						Deliver deliver=DeliverBUS.getDeliverByPODetail(Integer.parseInt(orderCode), lang);
						deliver.setDeliverstatusID(2);
						update(deliver, session);
						//save payment
						//Payment payment=new Payment();
						//payment.setOrderDetailId(pODetail.getProductOrderDetailId());
						//payment.setPaymentMethodId(5);
						//payment.setPaymentDate(new Date());
						//save(payment, session);
						//save nlpayment
						NLPayment nLPayment=new NLPayment();
						nLPayment.setCreateDate(new Date());
						nLPayment.setOrderCode(orderCode);
						nLPayment.setPaymentId(paymentID);
						nLPayment.setPaymentType(paymentType);
						nLPayment.setTransactionInfo(transactionInfo);
						nLPayment.setOrderDetailId(pODetail.getProductOrderDetailId());
						save(nLPayment, session);
						
						Productorder pOrder=ProductorderBUS.getProductorder(pODetail.getProductorder().getProductOrderId(), lang);
						Products product=ProductBUS.getProducts(pODetail.getProductId(), lang);
						Manufacturer manufacturer=ManufacturerBUS.getManufacturer(product.getManufacturerId(), lang);
						Options options=null;
						float optionPrice=0;
						String optionDescription="";
						if(pODetail.getOption()!=null){
							options=OptionBUS.getOptionByID(pODetail.getOption().getOptionId() , lang);
							optionPrice=options.getOptionPrice();
							optionDescription=options.getDescription();
						}						
						
						float price=product.getPrice();
						int quantity=pODetail.getProductNumber();						
						float shippingCost=DeliverCostBUS.getDeliverCostByProduct(product, pODetail.getTotalMoney(), pOrder.getTransportId(), lang);
						float total=ResourcesDefault.getTotalMoneyForVN_Language(price, quantity, optionPrice, shippingCost);
						Deliverstatus deliverStatus=DeliverStatusBUS.getDeliverStatus(deliver.getDeliverstatusID(), lang);
						String orderStatust=deliverStatus.getDeliverStatus();
						SimpleDateFormat sDFormat=new SimpleDateFormat("dd/MM/yyyy");
						//send mail to buyer
						
						User buyer=UserBUS.getUser(pOrder.getAccount(), lang);						
						
						try {
							//System.out.println("buyermail: "+buyer.getEmail());
							
							String messageToBuyer="<a style=\"font-size:20px\">"+LanguegeBUS.getValue("thanks_order", lang)+" <br> "+LanguegeBUS.getValue("your_order", lang)+" </a>";
							messageToBuyer+="<table width=\"100%\">";
							
							messageToBuyer+="<tr><td colspan=\"8\" style=\"padding:5px 5px; font-size:20px\">"+LanguegeBUS.getValue("orderInfo", lang)+" ("+sDFormat.format(new Date())+")</td></tr>";
							messageToBuyer+="<tr><td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("deli_order_id", lang)+"</td> <td width=\"25%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("productname", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("price", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("deli_quantity", lang)+"</td>" +
									" <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("option", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("shipping", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("total", lang)+"</td> <td width=\"15%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("deli_order_status", lang)+"</td></tr>";
							messageToBuyer+="<tr><td style=\"text-align:center; padding:5px 5px\">"+pODetail.getProductOrderDetailId()+"</td> <td style=\"text-align:center; padding:5px 5px\">"+product.getProductName()+"</td> <td style=\"text-align:center; padding:5px 5px\">"+price+"</td> <td style=\"text-align:center; padding:5px 5px\">"+quantity+"</td>" +
									" <td style=\"text-align:center; padding:5px 5px\">"+optionPrice+"</td> <td style=\"text-align:center; padding:5px 5px\">"+shippingCost+"</td> <td style=\"text-align:center; padding:5px 5px\">"+total+"</td> <td style=\"text-align:center; padding:5px 5px\">"+orderStatust+"</td></tr></table>";
							
							messageToBuyer+="<table width=\"60%\"><tr><td colspan=\"3\" style=\"padding:5px 5px; font-size:20px\">"+LanguegeBUS.getValue("productinfo", lang)+"</td></tr>";
							messageToBuyer+="<tr><td width=\"20%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("size", lang)+" / "+LanguegeBUS.getValue("color", lang)+" </td> <td width=\"20%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\"> "+LanguegeBUS.getValue("option", lang)+" </td> <td width=\"20%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\"> "+LanguegeBUS.getValue("manufactural", lang)+" </td></tr>";
							messageToBuyer+="<tr><td style=\"text-align:center; padding:5px 5px\">"+product.getSize()+" / "+product.getColor() +"</td> <td style=\"text-align:center; padding:5px 5px\">"+optionDescription+"</td> <td style=\"text-align:center; padding:5px 5px\">"+manufacturer.getManufacturerName()+"</td></tr>";
							messageToBuyer+="</table>";
							messageToBuyer+=LanguegeBUS.getValue("comeback", lang)+" <a href=\""+basicURL+"\">"+LanguegeBUS.getValue("baseURL", lang)+"</a>";	
							
							sendMailToBuyer(buyer.getEmail(), buyer.getFullName(), messageToBuyer , "Order", lang);
						} catch (Exception e) {
							e.printStackTrace();
						}
						//send mail to seller
						User seller=UserBUS.getUser(product.getUser().getAccount(), lang);
						
						try {
							//System.out.println("sellermail: "+seller.getEmail());
							String messageToSeller="<a style=\"font-size:20px\">"+LanguegeBUS.getValue("youhaveorder", lang)+" </a>";
							messageToSeller+="<table width=\"100%\">";
							
							messageToSeller+="<tr><td colspan=\"8\" style=\"padding:5px 5px; font-size:20px\">"+LanguegeBUS.getValue("orderInfo", lang)+" ("+sDFormat.format(new Date())+")</td></tr>";
							messageToSeller+="<tr><td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("deli_order_id", lang)+"</td> <td width=\"25%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("productname", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("price", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("deli_quantity", lang)+"</td>" +
									" <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("option", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("shipping", lang)+"</td> <td width=\"10%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("total", lang)+"</td> <td width=\"15%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("deli_order_status", lang)+"</td></tr>";
							messageToSeller+="<tr><td style=\"text-align:center; padding:5px 5px\">"+pODetail.getProductOrderDetailId()+"</td> <td style=\"text-align:center; padding:5px 5px\">"+product.getProductName()+"</td> <td style=\"text-align:center; padding:5px 5px\">"+price+"</td> <td style=\"text-align:center; padding:5px 5px\">"+quantity+"</td>" +
									" <td style=\"text-align:center; padding:5px 5px\">"+optionPrice+"</td> <td style=\"text-align:center; padding:5px 5px\">"+shippingCost+"</td> <td style=\"text-align:center; padding:5px 5px\">"+total+"</td> <td style=\"text-align:center; padding:5px 5px\">"+orderStatust+"</td></tr></table>";
							
							messageToSeller+="<table width=\"60%\"><tr><td colspan=\"3\" style=\"padding:5px 5px; font-size:20px\">"+LanguegeBUS.getValue("productinfo", lang)+"</td></tr>";
							messageToSeller+="<tr><td width=\"20%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\">"+LanguegeBUS.getValue("size", lang)+" / "+LanguegeBUS.getValue("color", lang)+" </td> <td width=\"20%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\"> "+LanguegeBUS.getValue("option", lang)+" </td> <td width=\"20%\" style=\"text-align:center; padding:5px 5px; font-weight:bold\"> "+LanguegeBUS.getValue("manufactural", lang)+" </td></tr>";
							messageToSeller+="<tr><td style=\"text-align:center; padding:5px 5px\">"+product.getSize()+" / "+product.getColor() +"</td> <td style=\"text-align:center; padding:5px 5px\">"+optionDescription+"</td> <td style=\"text-align:center; padding:5px 5px\">"+manufacturer.getManufacturerName()+"</td></tr>";
							messageToSeller+="</table>";
							messageToSeller+=LanguegeBUS.getValue("pleaseconfirm", lang)+" <a href=\""+basicURL+"\">"+LanguegeBUS.getValue("baseURL", lang)+"</a>";	
							sendMailToSeller(seller.getEmail(), seller.getFullName(), messageToSeller, "Order", lang);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				};
				boolean result=transaction.executeTransaction(lang);
				request.setAttribute("", "");
			}
			request.setAttribute("payment", payment);
			//System.out.println("payment: "+payment);
			request.getRequestDispatcher("nl_completed.jsp").forward(request, response);
		}    	    	
    }

	protected boolean sendMailToSeller(String receiver,String receiverName,String content,String subject,String lang) throws Exception {
	 return	ResourcesDefault.sendSimpleEMail(receiver, receiverName, content, subject, lang);
		
	}

	protected boolean sendMailToBuyer(String receiver,String receiverName,String content,String subject,String lang) throws Exception {
		return	ResourcesDefault.sendSimpleEMail(receiver, receiverName, content, subject, lang);
		
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
