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

import BUS.EmailConfigureBUS;
import POJO.Emailconfigure;

/**
 * Servlet implementation class EmailConfigureController
 */
@WebServlet("/EmailConfigureController")
public class EmailConfigureController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailConfigureController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
        ServletContext app=getServletContext();;
		String lang=(String)app.getAttribute("MALL_LA");
		Emailconfigure emailConfigure=EmailConfigureBUS.getEmailConfigure(1, lang);
		request.setAttribute("emailConfigure", emailConfigure);
		request.getRequestDispatcher("/admin/emailconfigure.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
