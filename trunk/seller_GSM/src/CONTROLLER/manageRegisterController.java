package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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

/**
 * Servlet implementation class manageRegisterController
 */
@WebServlet("/manageRegisterController")
public class manageRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageRegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws ParseException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
         HttpSession session = request.getSession();
         ServletContext app=getServletContext();;
         String lang=(String)app.getAttribute("MALL_LA");
         try {
            String url = null;
            
            String account = request.getParameter("username");
            String pass = request.getParameter("password");
            String fullname=request.getParameter("fullname");
            String day = request.getParameter("birthday") ;
       
            Date d = new Date();
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            
            String sex = request.getParameter("sex");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String roleId = request.getParameter("role");
            
            String Repass =request.getParameter("password_confirm");
            if(pass.equals(Repass))
            {
            	   
            	   Role role = new Role();
            	   role = RoleBUS.getRole(Integer.parseInt(roleId),lang);
                   
                   System.out.println("role:"+role.getRoleName());
                   
                   User newUser = new User();    
                   newUser.setAccount(account);
                   newUser.setRole(role);
                   newUser.setPass(pass);
                   newUser.setFullName(fullname);
                   newUser.setBirthday(dFormat.parse(day));
                   newUser.setState("unlock");
                   newUser.setRegisterDate(d);
                   newUser.setSex(sex);
                   newUser.setAddress(address);
                   newUser.setPhone(phone);
                   newUser.setEmail(email);
             
                   boolean test = UserBUS.insertUser(newUser,lang);
                   if(test)
                    {
                            request.setAttribute("Message","Registed successfull !");
                            //session.setAttribute("username", newUser.getAccount());
                            //Role roleCurrent = (Role)RoleBUS.getRole(newUser.getRole().getRoleId()); 
                            //session.setAttribute("Role", roleCurrent.getRoleName());
                            //String str = (String)session.getAttribute("lastpage");
                            //if(str==null){
                            //    url= "/index.jsp";
                            //}else{
                            //    url = "/"+str;
                            //}
                              
	             	            
                            url = "/admin/user.html";
	                       
                     }else{
                    	 		request.setAttribute("Message","Register fail!");
		                    	 
		                        url = "/admin/userform.html";
		                       
        
                     }

            }
            else{
                request.setAttribute("Message","Incorrect password comfirm!");
               	 url = "/admin/userform.html";
              
                
            }
                
          RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                        rd.forward(request, response);
        } finally {            
            out.close();
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
			processRequest(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {
			processRequest(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
