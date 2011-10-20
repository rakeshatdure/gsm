package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.*;
import POJO.*;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class manageOrderController
 */
@WebServlet("/manageOrderController")
public class manageOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int item = 12;
	private NavigationInfo navInfo = new NavigationInfo();
	   
    public NavigationInfo getNavInfo() {
        return navInfo;
    }
    public void setNavInfo(NavigationInfo navInfo) {
        
        this.navInfo = navInfo;
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
        	String url = "";
        	
        	if(session.getAttribute("username") == null || !session.getAttribute("Role").equals("Admin")){
        		request.setAttribute("Message","Please login to access system !");
        		url = "/admin/login.html";
        	}else{
        		ServletContext app=getServletContext();;
        		String lang=(String)app.getAttribute("MALL_LA");
	        		
	                if(request.getParameter("item")!=null){
	                    item = Integer.parseInt((String)request.getParameter("item"));
	                }
	               
	            	navInfo.setPageSize(item);
	            	navInfo.setMaxIndices(5);
	            	String page = (String)request.getParameter("page");
	                if (null == page)
	                	navInfo.setCurrentPage(0);
	                else
	                	navInfo.setCurrentPage(Integer.parseInt(page));
        		
	              //List has delivered
	                Stateorder s1 = StateorderBUS.getStateorder(1,lang); 
	                navInfo.setRowCount(ProductorderBUS.lstProductorder(s1,lang).size());
	                List<Productorder> lstOder1 = ProductorderBUS.lstProductorder(s1,navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust1", navInfo);
        			request.setAttribute("lstOrders1", lstOder1);
        			
        			
        			
        			//List unpaid
        			Stateorder s2 = StateorderBUS.getStateorder(2,lang); 
	                navInfo.setRowCount(ProductorderBUS.lstProductorder(s2,lang).size());
	                List<Productorder> lstOder2 = ProductorderBUS.lstProductorder(s2,navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust2", navInfo);
        			request.setAttribute("lstOrders2", lstOder2);
	                
	                
        			//List has payment
        			Stateorder s3 = StateorderBUS.getStateorder(3,lang); 
	                navInfo.setRowCount(ProductorderBUS.lstOrderPayment(lang).size());
	                List<Productorder> lstOder3 = ProductorderBUS.lstOrderPayment(navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust3", navInfo);
        			request.setAttribute("lstOrders3", lstOder3);
	                
        			url = "/admin/order.html";
        		
        	}
        	
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(url);
            rd.forward(request, response);
            
        }catch(Exception ex){
             out.println(ex.getMessage() );
        } finally {
            out.close();
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
