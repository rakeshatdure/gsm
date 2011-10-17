/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import BUS.*;
import POJO.*;
import UTIL.DateUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
@WebServlet(name = "accountController", urlPatterns = { "/accountController" })
public class accountController extends HttpServlet {

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
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ServletContext app = getServletContext();;
		String lang = (String) app.getAttribute("MALL_LA");
		try {
			String url = null;
			String account = session.getAttribute("username").toString();
			User newUser = UserBUS.getUser(account, lang);

			User kq = UserBUS.testLogin(account, newUser.getPass(), lang);

			if (kq != null) {

				String fullname = request.getParameter("fullname");
				String day = request.getParameter("birthday");

				Date birthday = DateUtil.getDateFromString(day);

				String sex = request.getParameter("sex");
				String address = request.getParameter("address");
				String email = request.getParameter("email");
				String phone = request.getParameter("phone");

				// Role r = new Role();
				// r = RoleBUS.getRole(1);

				// newUser.setRole(r);

				newUser.setFullName(fullname);
				newUser.setBirthday(birthday);
				newUser.setSex(sex);
				newUser.setAddress(address);
				newUser.setPhone(phone);
				newUser.setEmail(email);

				boolean test = UserBUS.updateUser(newUser, lang);
				if (test) {
					request.setAttribute("Message", "Updated successfull !");
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
					url = "/accountdetail.html";
				} else {
					request.setAttribute("Message", "Updated fail!");
					url = "/accountform.html";
				}

			} else {
				request.setAttribute("Message",
						"Updated fail! You need login to website before");
				url = "/login.html";
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
		try {
			processRequest(request, response);
		} catch (ParseException ex) {
			Logger.getLogger(accountController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
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
		try {
			processRequest(request, response);
		} catch (ParseException ex) {
			Logger.getLogger(accountController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
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
