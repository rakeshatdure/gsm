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

import BUS.CategoryChildBUS;
import BUS.ProductBUS;
import BUS.UserBUS;
import POJO.*;
import UTIL.NavigationInfo;

/**
 * Servlet implementation class sellerController
 */
@WebServlet("/sellerController")
public class sellerController extends HttpServlet {
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
    public sellerController() {
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
        	if(session.getAttribute("username") == null){
        		request.setAttribute("Message","Please login to access system !");
        		url = "/login.html";
        	}else{
        		ServletContext app=getServletContext();;
        		String lang=(String)app.getAttribute("MALL_LA");
        		
        		navInfo.setPageSize(12);
            	String page = (String)request.getParameter("page");
                if (null == page)
                	navInfo.setCurrentPage(0);
                else
                	navInfo.setCurrentPage(Integer.parseInt(page));
                
        		if(session.getAttribute("Role").equals("Seller")){
        			
        			User user = (User)UserBUS.getUser((String)session.getAttribute("username"),lang);
        			navInfo.setRowCount(ProductBUS.lstProduct(user,lang).size());
        			List<Products> lst = ProductBUS.lstProduct(user,navInfo.getCurrentPage(),navInfo.getPageSize(),lang);
        			
        			request.setAttribute("ProductPOJOs", lst);
        			session.setAttribute("pagedcust", navInfo);
        			
        			url = "/sale/manage.jsp";
        		}else{
        			request.setAttribute("Message","Accounts are not allowed to access!");
            		url = "/login.html";
        		}
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
