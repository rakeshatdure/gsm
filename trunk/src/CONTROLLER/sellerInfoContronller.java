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

import org.apache.catalina.startup.SetContextPropertiesRule;

import BUS.*;
import POJO.*;

/**
 * Servlet implementation class sellerInfoContronller
 */
@WebServlet("/sellerInfoContronller")
public class sellerInfoContronller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellerInfoContronller() {
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
        ServletContext app=getServletContext();;
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
        	String comId = request.getParameter("comId");
        	String userId = request.getParameter("userId");
        	String user_submit = request.getParameter("user_submit");
        	String co_submit = request.getParameter("co_submit");
        	//test login
            String login = (String) session.getAttribute("username");
	 		if(login==null || login.isEmpty()){
	 				response.sendRedirect("../login.html");
	 		}
	 		String lang=(String)app.getAttribute("MALL_LA");
            if(null != userId && null!=user_submit){
            			
		            	String fullname = request.getParameter("fullname");
		                String sex = request.getParameter("sex");
		                String birthday = request.getParameter("birth");
		                String address = request.getParameter("address");
		                String phone = request.getParameter("phone");
		                String email = request.getParameter("email");
		                //String holder = request.getParameter("holder");
		                //String banking = request.getParameter("banking");
		                //String acc_number = request.getParameter("acc_number");
		                
                
			            User  user = UserBUS.getUser(userId,lang);
			            Bank b = BankBUS.getBank(user, lang);
			            user.setFullName(fullname);
			            user.setSex(sex);
			            System.out.println(birthday);
			           
			            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			            user.setBirthday(dFormat.parse(birthday));
			            
			            user.setAddress(address);
			            user.setPhone(phone);
			            user.setEmail(email);
			            //b.setAccountholders(holder);
			            //b.setAccountNumber(acc_number);
			            //b.setBanking(banking);
			            boolean kq = UserBUS.updateUser(user,lang);
			            BankBUS.updateBank(b, lang);
			            if(kq){
			            	response.sendRedirect("sale/sellerinfo.html");
			            }else{
			            	
			            	request.setAttribute("Message","Update information of seller Unsuccessfull !");
			            	url="/sale/sellerinfo.html";	
			            	ServletContext sc = getServletContext();
			                RequestDispatcher rd = sc.getRequestDispatcher(url);
			                rd.forward(request, response);
			            }
            }
            if(null != comId && null!=co_submit){
            		
	            	//String co_name = request.getParameter("co_name");
	                //String buss_number = request.getParameter("buss_number");
	                String co_address = request.getParameter("co_address");
	                String co_fax = request.getParameter("co_fax");
	                String co_phone = request.getParameter("co_phone");
	                //String representative = request.getParameter("representative");
	                String co_established = request.getParameter("co_established");
	                String co_bussinesstype = request.getParameter("co_bussinesstype");
	                String co_employees = request.getParameter("co_employees");
	                String co_mainproduct = request.getParameter("co_mainproduct");
	                String co_web = request.getParameter("co_web");
	                String co_capital = request.getParameter("co_capital");
	                String co_settlement = request.getParameter("co_settlement");
	               
	                Company co = CompanyBUS.getCompany(Integer.parseInt(comId), lang);
	                //co.setCompanyName(co_name);
	                //co.setBusinessNumber(buss_number);
	                //co.setRepresentative(representative);
	                co.setAddress(co_address);
	                co.setPhone(co_phone);
	                co.setFax(co_fax);
	                SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
	                co.setEstablished(dFormat.parse(co_established));
	                co.setBusinessType(co_bussinesstype);
	                co.setEmployees(Integer.parseInt(co_employees));
	                co.setMainProduct(co_mainproduct);
	                co.setWeb(co_web);
	                co.setCapital(co_capital);
	                co.setSettlement(Integer.parseInt(co_settlement));
	                boolean kq = CompanyBUS.updateCompany(co,lang);
		            if(kq){
		            	response.sendRedirect("sale/sellerinfo.html");
		            }else{
		            	
		            	request.setAttribute("Message","Update company information of seller Unsuccessfull !");
		            	url="/sale/sellerinfo.html";	
		            	ServletContext sc = getServletContext();
		                RequestDispatcher rd = sc.getRequestDispatcher(url);
		                rd.forward(request, response);
		            }
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
