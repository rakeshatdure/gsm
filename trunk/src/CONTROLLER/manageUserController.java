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
 * Servlet implementation class manageUserController
 */
@WebServlet("/manageUserController")
public class manageUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
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
    public manageUserController() {
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
        		
	        		int item = 12;
	                if(request.getParameter("item")!=null){
	                    item = Integer.parseInt((String)request.getParameter("item"));
	                }
	               
	            	navInfo.setPageSize(item);
	            	String page = (String)request.getParameter("page");
	                if (null == page)
	                	navInfo.setCurrentPage(0);
	                else
	                	navInfo.setCurrentPage(Integer.parseInt(page));
        		
	                //List Users
	                Role rUser= RoleBUS.getRole(2,lang); 
	                navInfo.setRowCount(UserBUS.lstUser(rUser,lang).size());
	                List<User> lstUser = UserBUS.lstUser(rUser,navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust", navInfo);
        			request.setAttribute("lstUsers", lstUser);
        			
        			//List Sellers
	                Role rSeller = RoleBUS.getRole(4,lang); 
	                navInfo.setRowCount(UserBUS.lstUser(rSeller,lang).size());
	                List<User> lstSeller = UserBUS.lstUser(rSeller,navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust2", navInfo);
	                request.setAttribute("lstSellers", lstSeller);
	                	                
	                //List Admins
	                Role rAdmin = RoleBUS.getRole(1,lang); 
	                navInfo.setRowCount(UserBUS.lstUser(rAdmin,lang).size());
	                List<User> lstAdmin = UserBUS.lstUser(rAdmin,navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust3", navInfo);
	                request.setAttribute("lstAdmins", lstAdmin);
	                
	               //List Users request
	                Role rRequest = RoleBUS.getRole(5,lang); 
	                navInfo.setRowCount(UserBUS.lstUser(rRequest,lang).size());
	                List<User> lstRequest = UserBUS.lstUser(rRequest,navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
	                session.setAttribute("pagedcust4", navInfo);
	                request.setAttribute("lstRequests", lstRequest);
	                
        			url = "/admin/user.html";
        		
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
