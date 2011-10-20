package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.UserBUS;
import POJO.User;

/**
 * Servlet implementation class manageAccountController
 */
@WebServlet("/manageAccountController")
public class manageAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void processRequest(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
    	response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();
        try {
            String url = null;
            String account= session.getAttribute("username").toString();

            String pass = request.getParameter("passOld");
            ServletContext app=getServletContext();;
            String lang=(String)app.getAttribute("MALL_LA");
             User kq = UserBUS.testLogin(account, pass,lang);
           
              if(kq != null){
                  
                   
                    String pass_new = request.getParameter("passNew");

                    //Role r = new Role();
                    //r = RoleBUS.getRole(1);
                    User newUser = UserBUS.getUser(account,lang);    
                   
                    //newUser.setAccount(account);
                    //newUser.setRole(r);
                    newUser.setPass(pass_new);
                 
                   boolean test = UserBUS.updateUser(newUser,lang);
                   if(test){
                        request.setAttribute("Message","Changed password successfull !");
                     
                        url = "/admin/changepass.html";
                    }else{
                            request.setAttribute("Message","Changed fail!");
                            url = "/admin/changepass.html";
                }           
             
              }else{
                            request.setAttribute("Message","Incorrect old password!");
                            url = "/admin/changepass.html";
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
