package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.DeliverBUS;
import POJO.HomeDeliverView;

/**
 * Servlet implementation class HomeDeliverController
 */
@WebServlet("/HomeDeliverController")
public class HomeDeliverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeDeliverController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void process(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	HttpSession session = request.getSession();
		String seller = (String) session.getAttribute("username");
        ServletContext app=getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		if (seller == null) {
			response.sendRedirect("../login.html");
			
		}else{
			List<HomeDeliverView> homeDelivers=null;
			if(request.getParameter("search")!=null){
				String from=request.getParameter("from");
				String to=request.getParameter("to");
				homeDelivers=DeliverBUS.getListHomeDeliverViewSearch(seller, from, to, lang);
				request.setAttribute("homeDelivers", homeDelivers);
				request.getRequestDispatcher("/sale/homedeliver.html").forward(request, response);
			}
				
			else{
				if(request.getParameter("update")!=null){
					
				}
			homeDelivers=DeliverBUS.getListHomeDeliverView(seller, "", lang);
			//System.out.println("homeDeliverssize: "+homeDelivers.size());
			request.setAttribute("homeDelivers", homeDelivers);
			request.getRequestDispatcher("/sale/homedeliver.html").forward(request, response);
			}
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process( request, response);
	}

}
