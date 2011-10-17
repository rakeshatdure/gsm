package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.EmailConfigureBUS;
import POJO.Emailconfigure;
import UTIL.ServletUtils;
import UTIL.VuHong_MD5;

/**
 * Servlet implementation class UpdateEmailConfigureController
 */
@WebServlet("/UpdateEmailConfigureController")
public class UpdateEmailConfigureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEmailConfigureController() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ServletContext app=getServletContext();;
		String lang=(String)app.getAttribute("MALL_LA");
		String update=request.getParameter("update");
		if(update!=null){			
			Emailconfigure emailConfigure=EmailConfigureBUS.getEmailConfigure(1, lang);
			emailConfigure.setEmail(VuHong_MD5.encodeBase64(request.getParameter("email")));
			emailConfigure.setPassword(VuHong_MD5.encodeBase64(request.getParameter("password")));
			emailConfigure.setEmailName(request.getParameter("Name"));
			emailConfigure.setHostName(request.getParameter("hotname"));
			emailConfigure.setPortNumber(Integer.parseInt(request.getParameter("port")));
			if("on".equals(request.getParameter("ssl"))){
				emailConfigure.setSsl("true");
			}
			else{
				emailConfigure.setSsl("false");
			}
			
			emailConfigure.setDateLastUpdated(new Date());
			EmailConfigureBUS.update(emailConfigure, lang);
			
			response.sendRedirect("admin/emailconfigure.html");
		}
		else{
			Emailconfigure emailConfigure=EmailConfigureBUS.getEmailConfigure(1, lang);
			request.setAttribute("emailConfigure", emailConfigure);
			request.getRequestDispatcher("/admin/updateemailconfigure.jsp").forward(request, response);
		}
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
