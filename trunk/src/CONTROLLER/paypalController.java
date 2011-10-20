/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import POJO.*;
import UTIL.*;
import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.exceptions.PayPalException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author admin
 */
@WebServlet(name = "paypalController", urlPatterns = {"/paypalController"})
public class paypalController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
          
            ArrayList<Productorderdetail> cart = (ArrayList<Productorderdetail>)session.getAttribute("cart");
            double total = 0;
            for(int i=0;i<cart.size();i++){
                total +=  cart.get(i).getTotalMoney();
            }
            String amount = String.valueOf(total);
            String creditCardType = request.getParameter("ccType");
            String creditCardNumber =  request.getParameter("ccNumber");
            String expYear = request.getParameter("expYear");
            String expMonth = request.getParameter("expMonth");
            String expDate = expMonth + expYear;
            String cvv2Number = request.getParameter("cvvNumber");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");
            String country = request.getParameter("countryCode");
           
            
            NVPDecoder resultValues = PaypalUtils.DoDirectPaymentCode("Sale",amount, creditCardType, creditCardNumber, expDate, cvv2Number,
                 																firstName, lastName, address, city, state, zip, country); // make money transfers
            // Get information on recently completed transactions
            String transactionId = (String) resultValues.get("TRANSACTIONID");
            String amount1 = (String) resultValues.get("AMT");
            String avsCode = (String) resultValues.get("AVSCODE");
            String cvv2Match = (String) resultValues.get("CVV2MATCH");
            // Check for errors not
            //if(resultValues == null){
            //		request.setAttribute("Message","Papal connection error or system error connecting to the internet!!");
            if (MyPaypal.isError(resultValues)) // If there are errors in detail
            {
                System.out.println("ERROR:");
                System.out.println("CORRELATIONID: " + resultValues.get("CORRELATIONID"));
                System.out.println("VERSION: " + resultValues.get("VERSION"));

                // Browse through the list of errors
                int i = 0;
                while (resultValues.get("L_LONGMESSAGE" + i) != null)
                {
                    System.out.println("Error number: " + i);
                    System.out.println("Error code: " + resultValues.get("L_ERRORCODE" + i));
                    System.out.println("Short message: " + resultValues.get("L_SHORTMESSAGE" + i));
                    System.out.println("Long message: " + resultValues.get("L_LONGMESSAGE" + i));
                    i++;
                }
                request.setAttribute("Message","Payment failed. Please check your credit card information!!!");
            }else{
            	
            	request.setAttribute("Message","Thank you for your payment! We will deliver goods to the customers in the shortest time!!!");
            }
            
            
            //request.setAttribute("Message"," a:" + amount + " CT:" +creditCardType + "CN:"+creditCardNumber+"Date:"+expDate +"cvv:"+cvv2Number +"firstName:"+firstName
            //		+"lastName:"+lastName+"address:"+address+"city:"+city+"state:"+state+"zip:"+zip+"country:"+country);
            String url = "/payment1.html";
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception ex) {
            System.err.print("payment error: "+ex.getMessage());
      
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
