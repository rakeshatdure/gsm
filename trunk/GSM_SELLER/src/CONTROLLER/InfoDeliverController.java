package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.ProductBUS;
import BUS.ProductorderBUS;
import BUS.ProductorderdetailBUS;
import BUS.UserBUS;
import POJO.Productorder;
import POJO.Productorderdetail;
import POJO.Products;
import POJO.User;

/**
 * Servlet implementation class InfoDeliverController
 */
@WebServlet("/InfoDeliverController")
public class InfoDeliverController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoDeliverController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
PrintWriter out = response.getWriter();
HttpSession session = request.getSession();
try {
    /* TODO output your page here
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Servlet checkoutController</title>");  
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Servlet checkoutController at " + request.getContextPath () + "</h1>");
    out.println("</body>");
    out.println("</html>");
     */
	 
	
     ArrayList<Productorderdetail> cart = new ArrayList<Productorderdetail>();
     Productorderdetail ct = new Productorderdetail();
     Products p = new Products();
     String action = request.getParameter("action");
     String update = request.getParameter("update");
     if(session.getAttribute("username")==null){
         response.sendRedirect("login.html");
     }else{
         ServletContext app=getServletContext();;
         String lang=(String)app.getAttribute("MALL_LA");
             if (session.getAttribute("cart") == null) {
                 session.setAttribute("cart", cart);
             }
             cart = (ArrayList<Productorderdetail>) session.getAttribute("cart");
             if (update != null) {
                 for (int i = 0; i < cart.size(); i++) {
                     String sl = String.valueOf(request.getParameter("number"));
                     int soluong = Integer.parseInt(sl);
                     int productId = Integer.parseInt(request.getParameter("Id"));
                     p = ProductBUS.getProducts(productId,lang);
                   
                     if (productId == cart.get(i).getProducts().getProductId()) {
                         cart.get(i).setProductNumber(soluong);
                         cart.get(i).setTotalMoney(soluong*p.getPrice());
                     }
                 }
             } else if (action.equals("add") && cart.isEmpty() ) {
                 int Id = Integer.parseInt(request.getParameter("pId"));
                 p = ProductBUS.getProducts(Id,lang);
               //  Deliver deliver=DeliverBUS.getDeliverByID(p.getDeliverCost().getDeliverCostId(), lang);
                // Delivercost cost=DeliverCostBUS.getDeliverCostById(p.getDeliverCost().getDeliverCostId(), lang);
              //   p.setDeliverCost(cost);
                 ct.setProducts(p);
                 ct.setProductNumber(1);
                 ct.setTotalMoney(p.getPrice());
                 
                 cart.add(ct);
             } else if (action.equals("add")) {
                 int Id = Integer.parseInt(request.getParameter("pId"));
                 int flag = 0;
                 p = ProductBUS.getProducts(Id,lang);
                 ct.setProducts(p);
                 for (int i = 0; i < cart.size(); i++) {
                     if (cart.get(i).getProducts().getProductId() == ct.getProducts().getProductId()) {       //If a product has existed thus increases the amount to 1
                         cart.get(i).setProductNumber(cart.get(i).getProductNumber() + 1);
                         cart.get(i).setTotalMoney((cart.get(i).getProductNumber() + 1)*p.getPrice());
                         flag = 1;
                     }
                 }
                 if (flag == 0) {
                     ct.setProductNumber(1);
                     ct.setTotalMoney(p.getPrice());
                     cart.add(ct);
                 }
             } 
     }
	
   
    
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
