/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import BUS.*;
import POJO.*;
import UTIL.DateUtil;
import UTIL.VuHong_MD5;

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

/**
 * 
 * @author wwe
 */
@WebServlet(name = "changePasswordController", urlPatterns = { "/changePasswordController" })
public class changePasswordController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		VuHong_MD5 v = new VuHong_MD5();
		try {
			String url = null;
			String account = session.getAttribute("username").toString();

			String pass = v.encrypt(request.getParameter("password_old"));
			User kq = UserBUS.testLogin(account, pass, lang);

			if (kq != null) {

				String pass_new = v.encrypt(request.getParameter("password"));

				// Role r = new Role();
				// r = RoleBUS.getRole(1);
				User newUser = UserBUS.getUser(account, lang);

				// newUser.setAccount(account);
				// newUser.setRole(r);
				newUser.setPass(pass_new);

				boolean test = UserBUS.updateUser(newUser, lang);
				if (test) {
					request.setAttribute("Message",
							"Changed password successfull !");
					// session.setAttribute("username", newUser.getAccount());
					// Role roleCurrent =
					// (Role)RoleBUS.getRole(newUser.getRole().getRoleId());
					// session.setAttribute("Role", roleCurrent.getRoleName());
					// String str = (String)session.getAttribute("lastpage");
					// if(str==null){
					// url= "/index.jsp";
					// }else{
					// url = "/"+str;
					// }
					url = "/changepass.html";
				} else {
					request.setAttribute("Message", "Changed fail!");
					url = "/changepass.html";
				}

			} else {
				request.setAttribute("Message", "Incorrect old password!");
				url = "/changepass.html";
			}
			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher(url);
			rd.forward(request, response);

		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
