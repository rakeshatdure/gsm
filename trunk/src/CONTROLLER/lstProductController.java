/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import BUS.*;
import POJO.*;
import UTIL.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * @author
 */
@WebServlet(name = "lstProductController", urlPatterns = {"/lstProductController"})
public class lstProductController extends HttpServlet {

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
        ServletContext app=getServletContext();
        String lang=(String)app.getAttribute("MALL_LA");
        try {
        	int item = 12;
            if(request.getParameter("item")!=null){
                item = Integer.parseInt((String)request.getParameter("item"));
            }
           
        	navInfo.setPageSize(item);
        	navInfo.setMaxIndices(5);
        	String page = (String)request.getParameter("page");
            if (null == page)
            	navInfo.setCurrentPage(0);
            else
            	navInfo.setCurrentPage(Integer.parseInt(page));
            
            List<Products> lst = null;
            String categoryChildId = request.getParameter("Id");
            String subId = request.getParameter("subId");
            if(null==categoryChildId && null==subId){
            	navInfo.setRowCount(ProductBUS.lstProductPaid(lang).size());
                lst = ProductBUS.lstProductPaid(navInfo.getCurrentPage(),navInfo.getPageSize(),lang) ;
            
            }else{
            	if(null!=categoryChildId){
	                CategoryChild cc = CategoryChildBUS.getCategoryChild(categoryChildId,lang);
	                navInfo.setRowCount(ProductBUS.lstProductPaid(cc,lang).size());
	              //  System.out.println(cc.getCategoryId()+"-"+navInfo.getCurrentPage()+"-"+navInfo.getPageSize()+"-1");
	                lst = ProductBUS.lstProductPaid(cc,navInfo.getCurrentPage(),navInfo.getPageSize(),lang);
            	}
            	if(null!=subId){
            		 CategorySub cb = CategorySubBUS.getCategorySub(subId, lang);
            		 navInfo.setRowCount(ProductBUS.lstProductPaid(cb,lang).size());
            		// System.out.println(cb.getCategorySubId()+"-"+navInfo.getCurrentPage()+"-"+navInfo.getPageSize()+"-2");
            		 lst = ProductBUS.lstProductPaid(cb,navInfo.getCurrentPage(),navInfo.getPageSize(),lang);
            	}
           
            }      
            session.setAttribute("pagedcust", navInfo);
            request.setAttribute("ProductPOJOs", lst);
            session.setAttribute("lastpage", "product.html");
            String url = "/product.html";

            ServletContext sc = getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(url);
            rd.forward(request, response);
        }catch(Exception ex){
             out.println(ex.getMessage() );
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
