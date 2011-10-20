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

import BUS.RoleBUS;
import BUS.UserBUS;
import POJO.Role;
import POJO.User;
import UTIL.*;

/**
 * Servlet implementation class mangeLoginController
 */
@WebServlet("/mangeLoginController")
public class mangeLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mangeLoginController() {
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
        String lang=(String)app.getAttribute("MALL_LA");
        try {
            String url = null;
            	VuHong_MD5 v = new VuHong_MD5();
                String user = request.getParameter("username");
                String pass = v.encrypt(request.getParameter("password"));
                User kq = UserBUS.testLogin(user, pass,lang);
                if(kq != null &&  kq.getRole().getRoleId()==1 && kq.getState().equals("unlock")){
                    request.setAttribute("Message","Login successfull !");
                    session.setAttribute("username", kq.getAccount());
                    Role role = (Role)RoleBUS.getRole(kq.getRole().getRoleId(),lang); 
                    session.setAttribute("Role", role.getRoleName());
                    String str = (String)session.getAttribute("lastpage");
                   
//                    	if(str==null){
//	                        url= "/admin/index.jsp";
//	                    }else{
//	                        url = "/admin/"+str;
//	                    }
                    	response.sendRedirect("admin/user.html");	
                                       
                }else if(kq != null &&  kq.getRole().getRoleId()==1 && kq.getState().equals("lock")) {
	                	request.setAttribute("Message","This account has been locked!!!");
	                    url = "/admin/login.html";
	                    RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
	                    rd.forward(request, response);
                }else{
                            request.setAttribute("Message","Incorrect username or password!!!");
                            url = "/admin/login.html";
                            RequestDispatcher rd =  getServletContext().getRequestDispatcher(url);
                            rd.forward(request, response);
                }
              session.setAttribute("URL", ServletUtils.getBaseUrl(request));
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
