package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BUS.*;
import POJO.*;

/**
 * Servlet implementation class manageCategoryDeleteController
 */
@WebServlet("/manageCategoryDeleteController")
public class manageCategoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public manageCategoryDeleteController() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			String url = "";

			if (session.getAttribute("username") == null
					|| !session.getAttribute("Role").equals("Admin")) {
				request.setAttribute("Message",
						"Please login to access system !");
				url = "/admin/login.html";
			} else {
				ServletContext app = getServletContext();;
				String lang = (String) app.getAttribute("MALL_LA");

				String act = (String) request.getParameter("act");
				String cateId = (String) request.getParameter("Id");
				String childId = (String) request.getParameter("childId");
				String subId = (String) request.getParameter("subId");
				if (null != cateId && null != act && act.equals("delete")) {
					Category cate = CategoryBUS.getCategory(cateId, lang);
					boolean test = CategoryBUS.deleteCategory(cate, lang);
					if (test) {
						response.sendRedirect("category.html");
					} else {

					}

				}
				if (null != childId && null != act && act.equals("delete")) {
					CategoryChild cc = CategoryChildBUS.getCategoryChild(
							childId, lang);
					boolean test = CategoryChildBUS.deleteCategoryChild(cc,
							lang);
					if (test) {
						response.sendRedirect("category.html");
					} else {

					}

				}
				if (null != subId && null != act && act.equals("delete")) {
					CategorySub cb = CategorySubBUS.getCategorySub(subId, lang);
					boolean test = CategorySubBUS.deleteCategorySub(cb, lang);
					if (test) {
						response.sendRedirect("category.html");
					} else {

					}

				}

			}

		} catch (Exception ex) {
			out.println(ex.getMessage());
		} finally {
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
