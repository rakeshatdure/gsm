package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UTIL.GenerateConfig;

/**
 * Servlet implementation class languageManageController
 */
@WebServlet("/languageManageController")
public class languageManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public languageManageController() {
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
//        	GenerateConfig cf = new GenerateConfig();
//     		cf.setOutputDir(getServletContext().getRealPath("/")+"WEB-INF/classes");
     		
            String type = (String)request.getParameter("lang");
            System.out.println(Integer.parseInt(type));
            switch(Integer.parseInt(type)){
	            case 1:
	            	//cf.execute_en();
	            	session.setAttribute("MALL_LA","MALL_EN");
	            	session.setAttribute("FLAG","flag_en.gif");
	                break;
	            case 2:
	            	//cf.execute_kr();
	            	session.setAttribute("MALL_LA","MALL_KR");
	            	session.setAttribute("FLAG","flag_kr.gif");
	                break;
	            case 3:
	            	//cf.execute_vn();
	            	session.setAttribute("MALL_LA","MALL_VN");
	            	session.setAttribute("FLAG","flag_vn.gif");
	                break;
            }
            
            response.sendRedirect("admin/index.html");
            
            	
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
