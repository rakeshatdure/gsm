package CONTROLLER; import javax.servlet.http.HttpSession;

import java.io.IOException;
import DAO.NoticeDAO;
import BUS.NoticeBUS;
import BUS.TypeNoticeBUS;

import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import BUS.NoticeBUS;
import POJO.*;
import java.util.List;

/**
 * Servlet implementation class sellerNoticeController
 */
@WebServlet("/sellerNoticeController")
public class sellerNoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public sellerNoticeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		try {
			/*
			 * TODO output your page here out.println("<html>");
			 * out.println("<head>");
			 * out.println("<title>Servlet logoutController</title>");
			 * out.println("</head>"); out.println("<body>");
			 * out.println("<h1>Servlet logoutController at " +
			 * request.getContextPath () + "</h1>"); out.println("</body>");
			 * out.println("</html>");
			 */
			
			//test login
	        String login = (String) session.getAttribute("username");
			if(login==null || login.isEmpty()){
					response.sendRedirect("../login.html");
			}else{
				ServletContext app=getServletContext();;
				String lang=(String)app.getAttribute("MALL_LA");
			
					List<Notice> lstNotice1=NoticeBUS.lstNotice(lang);
					request.setAttribute("lstNotice1", lstNotice1);  
				
					Typenotice tn2 = TypeNoticeBUS.getTypenotice(1,lang);
					List<Notice> lstNotice2=NoticeBUS.lstNotice(tn2,lang);
					request.setAttribute("lstNotice2", lstNotice2);
					
					Typenotice tn3 = TypeNoticeBUS.getTypenotice(2,lang);
					List<Notice> lstNotice3=NoticeBUS.lstNotice(tn3,lang);
					request.setAttribute("lstNotice3", lstNotice3);  
					
					Typenotice tn4 = TypeNoticeBUS.getTypenotice(3,lang);
					List<Notice> lstNotice4=NoticeBUS.lstNotice(tn4,lang);
					request.setAttribute("lstNotice4", lstNotice4);  
					
					Typenotice tn5 = TypeNoticeBUS.getTypenotice(4,lang);
					List<Notice> lstNotice5=NoticeBUS.lstNotice(tn5,lang);
					request.setAttribute("lstNotice5", lstNotice5);  
					
		
						request.getRequestDispatcher("/sale/Notice.jsp").forward(request,response);
		
			}
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
