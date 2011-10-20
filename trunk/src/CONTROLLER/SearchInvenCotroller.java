package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.InventoryDAO;
import POJO.Inventory;

/**
 * Servlet implementation class SearchInvenCotroller
 */
@WebServlet("/SearchInvenCotroller")
public class SearchInvenCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchInvenCotroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			
			/*
			 * TODO output your page here out.println("<html>");
			 * out.println("<head>");
			 * out.println("<title>Servlet SearchInvenController</title>");
			 * out.println("</head>"); out.println("<body>");
			 * out.println("<h1>Servlet SearchInvenController at " +
			 * request.getContextPath () + "</h1>"); out.println("</body>");
			 * out.println("</html>");
			 */
			HttpSession session = request.getSession();
			ServletContext app=getServletContext();;
			String lang=(String) app.getAttribute("MALL_LA");
			
			String datefrom = (String) request.getParameter("datepickerdateFrom");	
			String dateto = (String) request.getParameter("datepickerdateTo");
			String keyname = (String) request.getParameter("txtkeyName");
			int manufactureId = Integer.parseInt(request.getParameter("optionManuId"));
		/*	int InvenStateId = Integer.parseInt(request.getParameter("optionStateInvenId"));*/
			//System.out.print(datefrom);
			//System.out.print(dateto);
			//System.out.print(keyname);
			//System.out.print(manufactureId);
			//System.out.print(InvenStateId);
			
			String btnSearch = (String) request.getParameter("Submit");
			
			if (btnSearch != null ) {
				if(datefrom != null && dateto != null && keyname!= null && manufactureId != 0  ){
				List<Inventory> lstInven = InventoryDAO.lstSearchInventory((String) session.getAttribute("username"),lang, datefrom,dateto, manufactureId, keyname);
				request.setAttribute("lstInven", lstInven);
					if (lstInven == null || lstInven.size() == 0) {
						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("sale/inventory.jsp").forward(request,
								response);
						}
						
					}
				else if(datefrom != null && dateto != null && keyname!= null && ( manufactureId == 0 ) ){
					List<Inventory> lstInvenKey = InventoryDAO.lstSearchInventoryDateKey((String) session.getAttribute("username"),lang, datefrom,dateto, keyname);
					request.setAttribute("lstInven", lstInvenKey);
					if (lstInvenKey == null || lstInvenKey.size() == 0) {
						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("sale/inventory.jsp").forward(request,
								response);
					}
					
				}
				else if((datefrom != null && dateto != null) &&  manufactureId == 0  &&  keyname== null){	
					List<Inventory> lstInvendate = InventoryDAO.lstSearchInventoryDate((String) session.getAttribute("username"),lang, datefrom,dateto);
					request.setAttribute("lstInven", lstInvendate);
					if (lstInvendate == null || lstInvendate.size() == 0) {
						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("sale/inventory.jsp").forward(request,
								response);
					}
					
					
				}
				else if((datefrom != null && dateto != null && manufactureId != 0) ){
					List<Inventory> lstInvenManuId = InventoryDAO.lstSearchInventoryManuId((String) session.getAttribute("username"),lang, datefrom,dateto, manufactureId);
					request.setAttribute("lstInven", lstInvenManuId);
					if (lstInvenManuId == null || lstInvenManuId.size() == 0) {
						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("sale/inventory.jsp").forward(request,
								response);
					}
					
				}
				else if((datefrom != null && dateto != null )){
					List<Inventory> lstInvenState = InventoryDAO.lstSearchInventoryState((String) session.getAttribute("username"),lang, datefrom,dateto);
					request.setAttribute("lstInven", lstInvenState);
					if (lstInvenState == null || lstInvenState.size() == 0) {
						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("sale/inventory.jsp").forward(request,
								response);
					}
					
				}
				else if( (datefrom == null && dateto == null && manufactureId == 0) && keyname!=null ){
					List<Inventory> lstInvenKey = InventoryDAO.lstSearchInventoryKey((String) session.getAttribute("username"),lang,keyname);
					request.setAttribute("lstInven", lstInvenKey);
					if (lstInvenKey == null || lstInvenKey.size() == 0) {
						request.setAttribute("Message", "Product not exist");
						request.getRequestDispatcher("sale/inventory.jsp").forward(request,
								response);
					}
					
				}
				request.getRequestDispatcher("sale/inventory.jsp").forward(request,
						response);		
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
