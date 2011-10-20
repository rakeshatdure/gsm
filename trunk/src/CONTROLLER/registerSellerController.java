/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import BUS.*;
import POJO.*;
import UTIL.DateUtil;
import UTIL.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@WebServlet(name = "registerSellerController", urlPatterns = {"/registerSellerController"})
public class registerSellerController extends HttpServlet {

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
        try {
        	Date d = new Date();
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            String url = null;
            String account= session.getAttribute("username").toString();
            ServletContext app=getServletContext();;
            String lang = (String) app.getAttribute("MALL_LA");
            String companyName = (String) request.getParameter("companyName");
        	String representative = (String) request.getParameter("representative");
        	String businessNumber = (String) request.getParameter("businessNumber");
        	String address = (String) request.getParameter("address");
        	String phone = (String) request.getParameter("phone");
        	String identityCard = (String) request.getParameter("identityCard");
        	String fax = (String) request.getParameter("fax");
        	String nganluong = (String) request.getParameter("nganLuongAccount");
        	String businessType = (String) request.getParameter("businessType");
        	int nationality = Integer.parseInt((String)request.getParameter("nationality"));
        	String day = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");        	
        	int employees = 0;
        	if((String)request.getParameter("employees")!=""){
        		employees = Integer.parseInt((String)request.getParameter("employees"));
        	}
        	String mainProduct = (String) request.getParameter("mainProduct");
        	String capital = (String) request.getParameter("capital");
        	
        	int settlement=0;        	
        	if ((String) request.getParameter("settlement")!="")
        		settlement = Integer.parseInt((String) request.getParameter("settlement"));
        	
        	String web = (String) request.getParameter("website");
        	
        	
        	
        	String accountNumber = (String) request.getParameter("accountNumber");
        	String accountHolder = (String) request.getParameter("accountHolder");
        	String banking = (String) request.getParameter("banking");
        	
        	Company com = new Company();
        	User user = UserBUS.getUser(account, lang);
        	Bank bank = new Bank();
        	Role role = RoleBUS.getRole(5, lang);
            if(account!=null){
            	
            	user.setRole(role);
            	UserBUS.updateUser(user, lang);
            	
            	//insert Company table
            	 com.setCompanyName(companyName);
            	 com.setRepresentative(representative);
            	 com.setBusinessNumber(businessNumber);
            	 com.setAddress(address);
            	 com.setPhone(phone);
            	 com.setFax(fax);
            	 com.setUser(user);
            	 com.setIdentityCard(identityCard);
            	 com.setBusinessType(businessType);
            	 com.setNationality(nationality);
            	 try {
					com.setEstablished(dFormat.parse(day));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 com.setEmployees(employees);
            	 com.setMainProduct(mainProduct);
            	 com.setCapital(capital);
            	 com.setSettlement(settlement);
            	 com.setWeb(web);
            	 CompanyBUS.insertCompany(com, lang);
            	 
            	 //insert Bank table
            	 bank.setUser(user);
            	 bank.setAccountNumber(accountNumber);
            	 bank.setAccountholders(accountHolder);
            	 bank.setBanking(banking);
            	 BankBUS.insertBank(bank, lang);
            	 
            	 //insert Ngan Luong 
            	 if("on".equals((String)request.getParameter("isNganLuong"))){
            		 Nganluong nl = new Nganluong();
            		 nl.setReceiver((String)request.getParameter("nganLuongAccount"));
            		 //nl.setSecureCode((String)request.getParameter("nganLuongSecureCode"));
            		// nl.setMerchantSiteCode((String)request.getParameter("nganLuongMerchantSiteCode"));
            		 nl.setSellerAccount(account);
            		 nl.setCreateDate(new Date());
            		 nl.setUpdateDate(new Date());
            		 NganLuongBUS.insert(nl, lang);
            	 }
            	 
            	 
            	 session.setAttribute("Role",user.getRole().getRoleName());
            	 request.setAttribute("Message","Registed successfull !"); 
                 url = ServletUtils.getBaseUrl(request)+"/RegisterSeller/RegisterResult.jsp";
                 response.sendRedirect(url);
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
