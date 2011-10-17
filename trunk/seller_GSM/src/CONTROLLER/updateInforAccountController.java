package CONTROLLER; import javax.servlet.http.HttpSession;

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

import BUS.CompanyBUS;
import BUS.UserBUS;
import DAO.BankDAO;
import DAO.CompanyDAO;
import POJO.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Servlet implementation class updateInforAccountController
 */
@WebServlet("/updateInforAccountController")
public class updateInforAccountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateInforAccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @throws ParseException 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ServletContext app=getServletContext();;
        String lang=(String) app.getAttribute("MALL_LA");
        String sellername = (String) session.getAttribute("username");
        User user = UserBUS.getUser(sellername, lang);
        try {
        	String getSubmit = request.getParameter("btnUpdate");
        	Company getcompany = CompanyDAO.getCompany(user, lang);
        	Bank getBank = BankDAO.getBank(user, lang);
        	if (null==getSubmit){		            
		            request.setAttribute("getcompany", getcompany);		
		            
		            request.setAttribute("getBank", getBank);
		
		            request.getRequestDispatcher("/sale/updateinforaccount.jsp").forward(request, response);
            
        	}else {
                String txtCompanyname = request.getParameter("txtCompanyname");
                String txtCompanyAddress = request.getParameter("txtCompanyAddress");
                // String cbBTaxtype=request.getParameter("cbBTaxtype");
                String txtRepresen = request.getParameter("txtRepresen");
                String txtIndentityCard = request.getParameter("txtIndentityCard");
                String rdoInternational = request.getParameter("rdoInternational");
               
                
                String txtHolder = request.getParameter("txtHolder");
                String cbBBank = request.getParameter("cbBBank");
                String txtNumber = request.getParameter("txtNumber");


                getcompany.setCompanyName(txtCompanyname);
                getcompany.setAddress(txtCompanyAddress);
                getcompany.setRepresentative(txtRepresen);
                getcompany.setIdentityCard(txtIndentityCard);
                getcompany.setNationality(Integer.parseInt(rdoInternational));
               
                
                getBank.setBanking(cbBBank);
                getBank.setAccountNumber(txtNumber);
                getBank.setAccountholders(txtHolder);


                boolean kq1 = CompanyDAO.updateCompany(getcompany, lang);
                boolean kq2 = BankDAO.updateBank(getBank, lang);
                
                if (kq1 & kq2) {
                    
                	response.sendRedirect("sale/updateinforaccount.jsp");
                    System.out.println("Update information of seller successfull !");
                }else {
                    request.setAttribute("Message", "Update information of seller Unsuccessfull !");
                    request.getRequestDispatcher("/sale/updateinforaccount.jsp").forward(request, response);
                    System.out.println("Update information of seller Unsuccessfull !");
                }
                
            }

        } finally {
            out.close();
        }
    }

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
