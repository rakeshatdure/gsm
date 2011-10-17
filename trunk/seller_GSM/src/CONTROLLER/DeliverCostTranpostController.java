package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ProductBUS;
import DAO.DeliverCostDAO;
import POJO.Delivercost;
import POJO.Productorderdetail;
import POJO.Products;

/**
 * Servlet implementation class DeliverCostTranpostController
 */
@WebServlet("/DeliverCostTranpostController")
public class DeliverCostTranpostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliverCostTranpostController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
request.setCharacterEncoding("UTF-8");
PrintWriter out = response.getWriter();
HttpSession session=request.getSession();
try {
    /* TODO output your page here
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Servlet cartController</title>");  
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Servlet cartController at " + request.getContextPath () + "</h1>");
    out.println("</body>");
    out.println("</html>");vfgrhythjuy7kiftr5u7i8lo9;p
     */
	
	
	
    
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
