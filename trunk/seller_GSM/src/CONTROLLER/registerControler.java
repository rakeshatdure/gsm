/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import POJO.*;
import BUS.*;
 
import UTIL.*;


import java.awt.Window;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.sun.java.swing.plaf.windows.resources.windows;

/**
 *
 * @author wwe
 */
@WebServlet(name = "registerControler", urlPatterns = {"/registerControler"})
public class registerControler extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
         HttpSession session = request.getSession();
         try {
        	ServletContext app=getServletContext();;
        	String lang=(String)app.getAttribute("MALL_LA");
        	 
        	VuHong_MD5 v = new VuHong_MD5();
            String url = null;
            
            String account = request.getParameter("username");
            String pass = v.encrypt(request.getParameter("password"));
            String fullname=request.getParameter("fullname");
            String day = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day") ;
       
            Date d = new Date();
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            
            String sex = request.getParameter("sex");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
              
            	   Role role = new Role();
            	   role = RoleBUS.getRole(2,lang);            	  
                                     
                   User newUser = new User();    
                   newUser.setAccount(account);
                   newUser.setRole(role);
                   newUser.setPass(pass);
                   newUser.setFullName(fullname);
                   newUser.setBirthday(dFormat.parse(day));
                   newUser.setState("unlock");
                   newUser.setRegisterDate(d);
                   newUser.setSex(sex);
                   newUser.setAddress(address);
                   newUser.setPhone(phone);
                   newUser.setEmail(email);
             
                   boolean test = UserBUS.insertUser(newUser,lang);
                   if(test)
                    {
                            request.setAttribute("Message","Registed successfull !"); 
	                        url = ServletUtils.getBaseUrl(request)+"/RegisterUser/RegisterResult.jsp";
	                        activateEmail(account, email, lang);
	                        response.sendRedirect(url);              	             	        
                     }else{
                	 		request.setAttribute("Message","Register fail!");		                    	
	                        url = "/register.html";		             	        
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
    @SuppressWarnings("unused")
	private boolean activateEmail(String username,String email, String lang){
    	EmailService es = new EmailService();
    	Emailconfigure ec=EmailConfigureBUS.getEmailConfigure(1, lang);
    	if(ec!=null){
    		try {
    			
			es.setSender(VuHong_MD5.decodeBase64(ec.getEmail()));
			es.setPassword(VuHong_MD5.decodeBase64(ec.getPassword()));
			
    		es.setPort(ec.getPortNumber());
    		es.setHostName(ec.getHostName());
    		es.setSsl(("true".endsWith(ec.getSsl()))?true:false);
    		es.setSenderName(ec.getEmailName());
    		es.setSubject("Activation your account at khongmac.com");
    		es.sendMail(email, username, "Thanks for registered at khongmac.com! Click <a href='http://www.khongmac.com/ActivationAccountController?lang="+lang+"&username="+VuHong_MD5.encodeBase64(username)+"'>here<a/> to activation your account!");
    		} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
    	}
    	
    	return true;
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registerControler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(registerControler.class.getName()).log(Level.SEVERE, null, ex);
        }
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
