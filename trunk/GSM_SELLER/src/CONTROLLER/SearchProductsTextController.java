/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER; import javax.servlet.http.HttpSession;

import BUS.ProductBUS;
import POJO.Products;
import UTIL.StringUtils;
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
@WebServlet(name = "SearchProductsTextController", urlPatterns = {"/SearchProductsTextController"})
public class SearchProductsTextController extends HttpServlet {

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
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchProductsController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchProductsController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
            String key = String.valueOf(request.getParameter("txtWord"));
            String mf = String.valueOf(request.getParameter("selectWord"));
            String p = String.valueOf(request.getParameter("selectPrice"));
            Double priMin=0.0;
            Double priMax=0.0;
            int manuId = 0;
            if(!StringUtils.equals(p, "0")){
                String price[]=p.split(" ");
                priMin=Double.parseDouble(price[0]);
                priMax=Double.parseDouble(price[1]);
            }
            if(!StringUtils.equals(mf,"0")){
                manuId = Integer.parseInt(mf);
            }
            System.out.println("manuId:"+key);
            System.out.println("manuId:"+manuId);
            System.out.println("MIN:"+priMin);
            System.out.println("MAX:"+priMax);
            List<Products> lstpd=ProductBUS.SearchProductkey(key, manuId, priMin, priMax,(String)app.getAttribute("MALL_LA"));
             request.setAttribute("searchProducts", lstpd);
            if (lstpd == null) {
                request.setAttribute("Message", "Product not exist");//err: là giá trị của attribute request Message
            } else {
                request.getRequestDispatcher("SearchProducts.html").forward(request, response);
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
