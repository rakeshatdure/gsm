package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import BUS.*;
import DAO.ExtensionDAO;
import DAO.InventoryDAO;
import DAO.ProductDAO;
import POJO.*;
import UTIL.ServletUtils;
import UTIL.TransactionMethod;

/**
 * Servlet implementation class manageProductUpdateController
 */
@WebServlet("/manageProductUpdateController")
public class manageProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageProductUpdateController() {
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
        	ServletContext app=getServletContext();;
        	
        	String url = "";
        	String lang = (String)app.getAttribute("MALL_LA");
        	final String Id = request.getParameter("Id");
        	if(Id != null && !Id.isEmpty()){
        		TransactionMethod tr=new TransactionMethod(){
        			@Override
        			protected void doMethod(Session session, String lang) {
        				
		        		Extension  ext = ExtensionDAO.getExtension(Integer.parseInt(Id),lang);
		        		Products p = ProductDAO.getProducts(ext.getProductId(), lang);
		        		List<Inventory> lstiv = (List<Inventory>)InventoryDAO.lstInvenProductId(p, lang);
		        		System.out.println("lstiv: "+lstiv.size());
		        		for(int i=0;i<lstiv.size();i++){
		        			Inventory iven = (Inventory)lstiv.get(i);
		        			Calendar c = Calendar.getInstance();
		        			Date today =  new Date(); 
		        			//Compare with current date 
		        			if(today.after(ext.getLimitDateOld())){
				                c.setTime(ext.getLimitDateOld()); 
		        			}else{
		        				c.setTime(today); 
		        			}
		        			c.add(Calendar.DATE, ext.getLimitDateNum());  // number of days to add
		        			iven.setLimitDate(c.getTime());
		        			iven.setSateId(2);
		        			///Update Inventory
		        			update(iven, session);	
		        		}
		        		//Update Extension
		        		ext.setStatus("Y");
		        		System.out.println("ext: "+ext);
        				update(ext, session);
        			}
            	};
            	boolean result= tr.executeTransaction(lang);
            	System.out.println("result: "+result);
            	if(result){
		        				
		        		request.setAttribute("Message",LanguegeBUS.getValue("mUpload", lang));
		        }else{
		        		request.setAttribute("Message",LanguegeBUS.getValue("eUpload", lang));
		        }
		        	
        	}	
        	url="/admin/product.html";	
        	response.sendRedirect(ServletUtils.getBaseUrl(request)+url);
        	//ServletContext sc = getServletContext();
            //RequestDispatcher rd = sc.getRequestDispatcher(url);
            //rd.forward(request, response);
            
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
