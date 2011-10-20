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
import POJO.CategoryChild;
import POJO.Products;
import UTIL.StringUtils;

/**
 * Servlet implementation class searchController
 */
@WebServlet("/searchController")
public class searchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchController() {
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
        	String lang=(String)app.getAttribute("MALL_LA");
        	String key = String.valueOf(request.getParameter("txtWord"));
            String mf = String.valueOf(request.getParameter("selectWord"));
            String p = String.valueOf(request.getParameter("selectPrice"));
            Double priMin=0.0;
            Double priMax=0.0;
            int manuId = 0;
            if(!StringUtils.equals(p, "0")){
                String price[]=p.split(" ");
                priMin=Double.parseDouble(price[0]);
                priMax=Double.parseDouble(price[1]);
            }
            if(!StringUtils.equals(mf,"0")){
                manuId = Integer.parseInt(mf);
            }
            //System.out.println("manuId:"+key);
            //System.out.println("manuId:"+manuId);
            //System.out.println("MIN:"+priMin);
            //System.out.println("MAX:"+priMax);
            List<Products> lst=ProductBUS.SearchProductkey(key, manuId, priMin, priMax,lang);
            
            
            request.setAttribute("ProductPOJOs", lst);
            session.setAttribute("lastpage", "product.html");
            String url = "/product.html";

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
