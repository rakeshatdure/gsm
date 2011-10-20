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
import DAO.*;
import POJO.*;
import UTIL.*;

/**
 * Servlet implementation class manageProductController
 */
@WebServlet("/manageProductController")
public class manageProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private NavigationInfo navInfo = new NavigationInfo();
	private NavigationInfo navInfo2 = new NavigationInfo();
	public NavigationInfo getNavInfo2() {
		return navInfo2;
	}
	public void setNavInfo2(NavigationInfo navInfo2) {
		this.navInfo2 = navInfo2;
	}
	public NavigationInfo getNavInfo3() {
		return navInfo3;
	}
	public void setNavInfo3(NavigationInfo navInfo3) {
		this.navInfo3 = navInfo3;
	}

	private NavigationInfo navInfo3 = new NavigationInfo();
	   
    public NavigationInfo getNavInfo() {
        return navInfo;
    }
    public void setNavInfo(NavigationInfo navInfo) {
        
        this.navInfo = navInfo;
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageProductController() {
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
        ServletContext app=getServletContext();;
        
        try {
        	String url = "";
        	
        	if(session.getAttribute("username") == null || !session.getAttribute("Role").equals("Admin")){
        		request.setAttribute("Message","Please login to access system !");
        		url = "/admin/login.html";
        	}else{
        		String lang=(String)app.getAttribute("MALL_LA");
	        		int item = 10;
	                if(request.getParameter("item")!=null){
	                    item = Integer.parseInt((String)request.getParameter("item"));
	                }
	               
	            	navInfo.setPageSize(item);
	            	navInfo2.setPageSize(item);
	            	navInfo3.setPageSize(item);
	            	String page = (String)request.getParameter("page");
	                if (null == page){
	                	navInfo.setCurrentPage(0);
		                navInfo2.setCurrentPage(0);
		                navInfo3.setCurrentPage(0);
	                }else{
	                	navInfo.setCurrentPage(Integer.parseInt(page));
	                	navInfo2.setCurrentPage(Integer.parseInt(page));
	                	navInfo3.setCurrentPage(Integer.parseInt(page));
	                }
	                //List all products
	                navInfo.setRowCount(ProductBUS.lstProduct(lang).size());
	                List<Products> lst = ProductBUS.lstProduct(navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pageAll", navInfo);	        
        			request.setAttribute("lstProducts", lst);
        			System.out.println("lstProducts: "+lst);
        			
        			//List inventory 
        			navInfo2.setRowCount( InventoryDAO.lstInventoryByLimit(lang).size());
        			List<Inventory> lstStock = InventoryDAO.lstInventoryByLimit(navInfo2.getCurrentPage(),navInfo2.getPageSize(),lang) ;
        			request.setAttribute("lstStocks", lstStock );
        			session.setAttribute("pageStocks", navInfo2);
        			
        			//List Extension
        			navInfo3.setRowCount(ExtensionDAO.lstRequestExtension(lang).size());
        			List<Extension> lstExtend = ExtensionDAO.lstRequestExtension(navInfo3.getCurrentPage(),navInfo3.getPageSize(),lang) ;
        			request.setAttribute("lstExtends", lstExtend );
        			session.setAttribute("pageExtends", navInfo3);
        			
        			url = "/admin/product.html";
        		
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
		processRequest(request, response);
	}

}
