package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ConfirmBUS;
import BUS.ExchangeOrderBUS;
import BUS.ExchangeStatusBUS;
import BUS.OrderdetailstatusBUS;
import BUS.ProductorderdetailBUS;
import POJO.Confirm;
import POJO.Exchangeorder;
import POJO.Exchangestatus;
import POJO.Orderdetailstatus;
import POJO.Productorderdetail;

/**
 * Servlet implementation class ExchangeOrderController
 */
@WebServlet("/ExchangeOrderController")
public class ExchangeOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExchangeOrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
			 {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		try {
	        ServletContext app=getServletContext();;
			String lang = (String) app.getAttribute("MALL_LA");
			String user = (String) session.getAttribute("username");
			if(user==null || user.isEmpty()){
					response.sendRedirect("../login.html");
			 }else{
				 String action = (String)request.getParameter("action");
					String idTemp = (String)request.getParameter("id");		
					int id=0;
					if(idTemp!=null)
						id = Integer.parseInt(idTemp);
					
					if(action!=null){
						update(id, lang, request, response);
					}
					
					List<Exchangeorder> lstExchangeOrder = ExchangeOrderBUS.lstExchangeOrder(user,lang);
					request.setAttribute("lstExchangeOrder", lstExchangeOrder!=null ?lstExchangeOrder :new ArrayList<Exchangeorder>());
					request.getRequestDispatcher("/sale/exchangeorder.html").forward(request,response);
			 }
			
		} finally {
			out.close();
		}
	}

	
	public void update(int id,String lang,HttpServletRequest request,HttpServletResponse response){
		String exchangeStatusID=request.getParameter("confirmStatus");
		String memoToBuyer=request.getParameter("memoToBuyer");
		//update exchangestatus
		Exchangestatus exchangeStatus = ExchangeStatusBUS.getExchangeStatus(Integer.parseInt(exchangeStatusID), lang);
		Exchangeorder exchangeOrder = ExchangeOrderBUS.getExchangeOrder(id, lang);
				
		exchangeOrder.setMoneyReturn(new Float(0));
		exchangeOrder.setSignedDate(new Date());		
		exchangeOrder.setExchangeStatus(exchangeStatus);
		
		ExchangeOrderBUS.update(exchangeOrder, lang);
		//save the confirm of the seller 
		Confirm confirm=new Confirm();
		confirm.setObjects("S");
		confirm.setUpdateDate(new Date());
		confirm.setExchangeOrderID(exchangeOrder.getExchangeId());
		confirm.setIsCurrent("true");
		confirm.setMemo(memoToBuyer);
		// get current confirm
		Confirm currentConfirm = ConfirmBUS.getCurrentReturnExchangeConfirm(
				id,lang);
		// update current confirm
		if (currentConfirm != null) {
			currentConfirm.setIsCurrent("false");
			ConfirmBUS.update(currentConfirm, lang);
		}
		//save
		ConfirmBUS.insert(confirm, lang);
		
	}
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
