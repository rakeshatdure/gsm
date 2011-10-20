package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ProductorderBUS;
import BUS.RoleBUS;
import BUS.UserBUS;
import POJO.Productorder;
import POJO.Role;
import POJO.User;

/**
 * Servlet implementation class manageOrderDetailController
 */
@WebServlet("/manageOrderDetailController")
public class manageOrderDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageOrderDetailController() {
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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet productDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productDetailController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        	ServletContext app=getServletContext();;
        	String lang=(String)app.getAttribute("MALL_LA");
        	
        	String orderId = request.getParameter("Id");
        	int or_id = Integer.parseInt(orderId);
        	Productorder pro_order = (Productorder)ProductorderBUS.getProductorder(or_id,lang);
        	String acc_name = pro_order.getAccount();
        	User myUser = UserBUS.getUser(acc_name,lang);
            
        	request.setAttribute("userPOJO", myUser);
          
        	Role r = RoleBUS.getRole(myUser.getRole().getRoleId(),lang);
            request.setAttribute("rolePOJO", r);
                                    
            String url="/admin/orderdetail.html";
            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(url);
            rd.forward(request, response);
            
        }catch(Exception ex){
            out.println(ex.getMessage());
        }
        finally {            
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
