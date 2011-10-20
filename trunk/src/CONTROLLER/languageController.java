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

import BUS.*;
import POJO.*;
import UTIL.*;

/**
 * Servlet implementation class languageController
 */
@WebServlet("/languageController")
public class languageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public languageController() {
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
        	
        	 String type = (String)request.getParameter("lang");
        	 if(type==null){
        		 type="3";
        	 }
            // System.out.println(Integer.parseInt(type));
             switch(Integer.parseInt(type)){
 	            case 1:
 	            	//cf.execute_en();
 	            	app.setAttribute("MALL_LA","MALL_EN");
 	            	app.setAttribute("FLAG","flag_en.gif");
 	                break;
 	            case 2:
 	            	//cf.execute_kr();
 	            	app.setAttribute("MALL_LA","MALL_KR");
 	            	app.setAttribute("FLAG","flag_kr.gif");
 	                break;
 	            case 3:
 	            	//cf.execute_vn();
 	            	app.setAttribute("MALL_LA","MALL_VN");
 	            	app.setAttribute("FLAG","flag_vn.gif");
 	                break;
             }
             session.removeAttribute("cart");
             session.removeAttribute("username");
	         session.removeAttribute("Role");
	         session.removeAttribute("lastpage");
             
             response.sendRedirect("index.html");
            
            
            
            
            	
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
