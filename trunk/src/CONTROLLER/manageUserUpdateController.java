package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import UTIL.DateUtil;

/**
 * Servlet implementation class manageUserUpdateController
 */
@WebServlet("/manageUserUpdateController")
public class manageUserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageUserUpdateController() {
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
        request.setCharacterEncoding("UTF-8");
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
        	String url = "";
        	String userId = request.getParameter("Id");
        	String act = request.getParameter("act");
        	boolean kq=false;
        	ServletContext app=getServletContext();;
        	String lang=(String)app.getAttribute("MALL_LA");
        	User  user = UserBUS.getUser(userId,lang);
        	if(act==null || act.isEmpty()){
	            String fullname = request.getParameter("fullname");
	            String sex = request.getParameter("sex");
	            String birthday = request.getParameter("birthday");
	            String address = request.getParameter("address");
	            String phone = request.getParameter("phone");
	            String email = request.getParameter("email");
	            
	            
	            user.setFullName(fullname);
	            user.setSex(sex);
	           
	            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
	            user.setBirthday(dFormat.parse(birthday));
	            
	            user.setAddress(address);
	            user.setPhone(phone);
	            user.setEmail(email);
	            
        	}else{
        		Role r = RoleBUS.getRole(4, lang);
        		 user.setRole(r);
        	}
        	kq = UserBUS.updateUser(user,lang);
            if(kq){
            	response.sendRedirect("admin/userdetail.html?Id="+userId);
            }else{
            	
            	request.setAttribute("Message","Update user Unsuccessfull !");
            	url="/userform1.html?Id="+userId;	
            	ServletContext sc = getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            	
            
            
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
