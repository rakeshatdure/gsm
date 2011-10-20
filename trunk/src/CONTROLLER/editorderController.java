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
import java.util.ArrayList;
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
 * @author wwe
 */
@WebServlet(name = "editorderController", urlPatterns = {"/editorderController"})
public class editorderController extends HttpServlet {

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
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editorderController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editorderController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            String url=null;
         
            Products p = new Products();

            String update = request.getParameter("update");
            String remove = request.getParameter("remove");
            String id_order_test = request.getParameter("Id_Page");
            String update_info = request.getParameter("updateInfo");
            
            int id_order = Integer.parseInt(id_order_test);
              List<Productorderdetail> nList = ProductorderdetailBUS.getListProductorderdetail(id_order,lang) ;
                    float TotalM=0.0f;
                       if(session.getAttribute("username")==null){
                response.sendRedirect("login.html");
            }else{
                           if(update_info !=null){
                               int transport = Integer.parseInt(request.getParameter("transport"));
                               Transport newtrans = TransportBUS.getTransport(transport,lang);
                               String receiver = request.getParameter("name_receiver");
                               String address_rec = request.getParameter("address_receiver");
                               String phone_rec = request.getParameter("phone_receiver");
                               Productorder mypro_order = (Productorder) ProductorderBUS.getProductorder(id_order,lang);
                               Productorder neworder = new Productorder();
                               neworder.setProductOrderId(id_order);
                               neworder.setStateorder(mypro_order.getStateorder());
                               neworder.setProductorderdetails(mypro_order.getProductorderdetails());
                               neworder.setTransport(newtrans);
                               neworder.setOrderDate(mypro_order.getOrderDate());
                               neworder.setReciever(receiver);
                               neworder.setUser(mypro_order.getUser());
                               neworder.setPhone(phone_rec);
                               neworder.setAddress(address_rec);
                               neworder.setTotalMoney(mypro_order.getTotalMoney());
                               ProductorderBUS.updateProductorder(neworder,lang);
                               
                           }
                        if (update != null) {
                            for(int i=0;i<nList.size();i++){
                         
                            String sl = String.valueOf(request.getParameter("number"));
                            int soluong = Integer.parseInt(sl);
                            int productId = Integer.parseInt(request.getParameter("Id_Product"));
                            p = ProductBUS.getProducts(productId,lang);
                            if (productId == nList.get(i).getProducts().getProductId()) {
                                  nList.get(i).setProductNumber(soluong);
                                  nList.get(i).setTotalMoney(soluong*p.getPrice());
                                 
                                    ProductorderdetailBUS.updateProductorderdetail(nList.get(i),lang);
                                    
                                }
                             TotalM +=(float)soluong*p.getPrice();
                              }
                       
                        } 
                    }
                     if (remove!=null) {
                        int productId = Integer.parseInt(request.getParameter("Id_Product"));
                        for (int i = 0; i < nList.size(); i++) {
                            if (productId == nList.get(i).getProducts().getProductId()) {
                               // nList.remove(i);
                                ProductorderdetailBUS.deleteProductorderdetail(nList.get(i),lang);
                                  //  url ="editorderproduct.jsp?Id="+id_order;
                                   // response.sendRedirect(url);
                            }
                        }
                        if (nList.isEmpty()) {
                            nList = null;
                        }
                    } 
                  
                  session.setAttribute("Tmoney", TotalM);
                   url ="editorder.html?Id="+id_order;
                    response.sendRedirect(url);
            
                       // url ="editorderproduct.jsp?Id="+id_order;
                    //response.sendRedirect(url);
        } catch(Exception ex){
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
