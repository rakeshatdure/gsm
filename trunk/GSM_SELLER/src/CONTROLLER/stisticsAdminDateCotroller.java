/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import BUS.ProductorderdetailBUS;
import POJO.Productorderdetail;
import UTIL.NavigationInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "stisticsAdminDateCotroller", urlPatterns = {"/stisticsAdminDateCotroller"})
public class stisticsAdminDateCotroller extends HttpServlet {

	private NavigationInfo navInfo = new NavigationInfo();
	   
    public NavigationInfo getNavInfo() {
        return navInfo;
    }
    public void setNavInfo(NavigationInfo navInfo) {
        
        this.navInfo = navInfo;
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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ServletContext app=getServletContext();;
        String lang=(String)app.getAttribute("MALL_LA");
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet stisticsAdminDateCotroller</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet stisticsAdminDateCotroller at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        	if(session.getAttribute("username") == null || !session.getAttribute("Role").equals("Admin")){
        		request.setAttribute("Message","Please login to access system !");
        		request.getRequestDispatcher("admin/login.html").forward(request, response);
        		
        	}else{
	        		navInfo.setPageSize(6);
		        	String page = (String)request.getParameter("page");
		            if (null == page)
		            	navInfo.setCurrentPage(0);
		            else
		            	navInfo.setCurrentPage(Integer.parseInt(page));
	            
		            String fromD = String.valueOf(request.getParameter("datepickerdateFrom"));
		            String toD = String.valueOf(request.getParameter("datepickerdateTo"));
		            if (request.getParameter("SearchSeller") != null) {
		
		                
		                List<Productorderdetail> lstProductDetail = ProductorderdetailBUS.listStisticsAdmin(fromD, toD,navInfo.getCurrentPage(),navInfo.getPageSize(),lang);
		                request.setAttribute("flag", 1);
		                request.setAttribute("lstProductAdmin", lstProductDetail);
		                if (lstProductDetail == null || lstProductDetail.size() == 0) {
		                    request.setAttribute("Message", "Product not exist");
		                    request.getRequestDispatcher("admin/statisticsAdmin.jsp").forward(request, response);
		                } else {
		                	navInfo.setRowCount(ProductorderdetailBUS.listStisticsAdmin(fromD, toD,lang).size());    
		                	session.setAttribute("pagedcust", navInfo);
		                	session.setAttribute("date","datepickerdateFrom="+fromD+"&datepickerdateTo="+toD+"&");
		                    request.getRequestDispatcher("./admin/statisticsAdmin.jsp").forward(request, response);
		                }
		            }
        	}
        	
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
