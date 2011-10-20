package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.EmailConfigureBUS;
import POJO.Emailconfigure;
import UTIL.EmailService;

/**
 * Servlet implementation class TestEmailConfigureController
 */
@WebServlet("/TestEmailConfigureController")
public class TestEmailConfigureController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestEmailConfigureController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		 response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		EmailService es = new EmailService();
		es.setSender(request.getParameter("email"));
		es.setPassword(request.getParameter("password"));
		es.setSenderName(request.getParameter("name"));
		es.setHostName(request.getParameter("hotname"));
		es.setPort(Integer.parseInt(request.getParameter("port")));
//		System.out.println("ssl "+request.getParameter("ssl"));
//		System.out.println("email: "+request.getParameter("email"));
//		System.out.println("name: "+request.getParameter("name"));
//		System.out.println("password: "+request.getParameter("password"));
//		System.out.println("hotmail: "+request.getParameter("hotname"));
//		System.out.println("port: "+request.getParameter("port"));
		
		if ("true".equals(request.getParameter("ssl"))) {
			//System.out.println("on");
			es.setSsl(true);
		} else {
			System.out.println("off");
			es.setSsl(false);
		}
		boolean send=es.sendMail(request.getParameter("email"), request.getParameter("Name"), "test");
		System.out.println("send: "+send);
		if(send){
			//System.out.println("successful");
			out.print("successful"); 			
		}else{
			//System.out.println("fault");
			out.print("fault"); 
		}
		

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
