/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package CONTROLLER; import javax.servlet.http.HttpSession;



import POJO.*;
import UTIL.VuHong_MD5;
import BUS.*;
 

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
 * @author E6400
 */
@WebServlet(name = "loginControler", urlPatterns = {"/loginControler"})
public class loginControler extends HttpServlet {
   
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ServletContext app=getServletContext();;
        String lang=(String)app.getAttribute("MALL_LA");
        try {
            String url = null;
            	VuHong_MD5 v = new VuHong_MD5();
                String user = request.getParameter("username");
                String pass = v.encrypt(request.getParameter("password"));
                User kq = UserBUS.testLogin(user, pass,lang);                
                if(kq != null && kq.getState().equals("unlock")){
                    request.setAttribute("Message","Login successfull !");
                    session.setAttribute("username", kq.getAccount());
                    Role role = (Role)RoleBUS.getRole(kq.getRole().getRoleId(),lang); 
                    session.setAttribute("Role", role.getRoleName());
                    String str = (String)session.getAttribute("lastpage");
                    if(kq.getRole().getRoleId()==2 || kq.getRole().getRoleId()==3 || kq.getRole().getRoleId()==5){
                    	out.print("user");                    	
                    }else if(kq.getRole().getRoleId()==4){
                    	out.print("seller");
                    }else if(kq.getRole().getRoleId()==1){
                    	out.print("failed");
                    }                   
                }else if(kq != null && kq.getState().equals("lock")){
	                	out.print("lock");
                }else{
                        out.print("failed");
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
