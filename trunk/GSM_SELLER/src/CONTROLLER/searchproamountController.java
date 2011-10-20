package CONTROLLER; import javax.servlet.http.HttpSession;

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

import BUS.ProductBUS;
import BUS.UserBUS;
import DAO.ProductDAO;
import POJO.Products;
import POJO.User;

/**
 * Servlet implementation class searchproamountController
 */
@WebServlet("/searchproamountController")
public class searchproamountController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchproamountController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        ServletContext app=getServletContext();;
        try {
           String lang=(String) app.getAttribute("MALL_LA");
            String username=(String)session.getAttribute("username");
            String categorychildId = (String)request.getParameter("selectCatogoryChild");
            String categorysubId =(String) request.getParameter("selectCatogorySub");
            String btnSearch = request.getParameter("btnSearch");
            if (btnSearch != null) {
                ArrayList<Products> lstProduct2=(ArrayList<Products>) ProductDAO.lstProduct2(username,categorychildId,lang);   
                ArrayList<Products> lstProduct1 = (ArrayList<Products>) ProductDAO.lstProduct1(username,categorychildId, categorysubId,lang);   
                request.setAttribute("lstProduct", lstProduct1);
                request.setAttribute("lstProduct", lstProduct2);
                if (lstProduct1 == null || lstProduct2 ==null) {
                    request.setAttribute("Message1", "Product not exist");
                    System.out.println("Product not exist");
                }
            }
            request.getRequestDispatcher("/sale/amountprice.jsp").forward(
                    request, response);
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
