package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.UserBUS;
import POJO.User;

/**
 * Servlet implementation class lockUserController
 */
@WebServlet("/lockUserController")
public class lockUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lockUserController() {
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
        HttpSession session=request.getSession();
        ServletContext app=getServletContext();;
        String lang=(String)app.getAttribute("MALL_LA");
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet logoutController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet logoutController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        	String userId = request.getParameter("Id");
            User  user = UserBUS.getUser(userId,lang);
            if(user.getState().equals("unlock")){
            	user.setState("lock");
            }else{
            	user.setState("unlock");
            }
            UserBUS.updateUser(user,lang);
            response.sendRedirect("user.html");
            
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
