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

import BUS.*;
import POJO.*;

/**
 * Servlet implementation class manageCategoryActionController
 */
@WebServlet("/manageCategoryActionController")
public class manageCategoryActionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public manageCategoryActionController() {
        super();
        // TODO Auto-generated constructor stub
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        try {
        	String url = "";
        	
        	if(session.getAttribute("username") == null || !session.getAttribute("Role").equals("Admin")){
        		request.setAttribute("Message","Please login to access system !");
        		url = "/admin/login.html";
        	}else{
                ServletContext app=getServletContext();;
                String lang=(String)app.getAttribute("MALL_LA");
        			String act = (String)request.getParameter("act");
        			String catename = (String)request.getParameter("catename");
        			String childname = (String)request.getParameter("childname");
        			String subname = (String)request.getParameter("subname");
        			
        			
        		 	if(null != act && act.equals("add")){
        		 			String cateId = (String)request.getParameter("cateId");
        		 			String childId = (String)request.getParameter("childId");
        		 			String subId = (String)request.getParameter("subId");
        		 			String cateId_new = (String)request.getParameter("cateId_new");
        		 			String childId_new = (String)request.getParameter("childId_new");
        		 			String subId_new = (String)request.getParameter("subId_new");
		        		 	if(null!=catename && catename.length() > 0 && null!=cateId_new){
		        		 		 Category cate = new Category();
		        		 		 cate.setCategoryId(cateId_new);
						         cate.setCategoryName(catename);
						        
		        	         	 boolean test = CategoryBUS.insertCategory(cate,lang);
		        	             if(test){
		        	            	   response.sendRedirect("admin/categoryadd.html");
		        	             }else{
		        	            	 	request.setAttribute("Message","Id already exists");
				     	            	url = "/admin/categoryadd.html";
			                            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			                            rd.forward(request, response);
		        	             }
		        		 		
		        		 	}
		        		 	if(null!=childname && childname.length() > 0 && null!=childId_new){
		        		 		CategoryChild cc = new CategoryChild();
		        		 		cc.setCategoryChildId(childId_new);
						        cc.setCategoryChildName(childname);
						       
		     		 		 	 cc.setCategoryId(cateId);
		     		 		 	 System.out.println("cateID:"+cateId);
		     		 		 	 System.out.println("childname:"+cc.getCategoryChildName());
			     	         	 boolean test = CategoryChildBUS.insertCategoryChild(cc,lang);
			     	             if(test){
			     	            	 	response.sendRedirect("admin/categoryadd.html");
			     	             }else{
				     	            	//response.sendRedirect("admin/categoryadd.html");
				     	            	request.setAttribute("Message","Id already exists");
				     	            	url = "/admin/categoryadd.html";
			                            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			                            rd.forward(request, response);
			     	             }
		        		 	}
		        		 	if(null!=subname && subname.length() > 0 && null!=subId_new){
		        		 		CategorySub cb = new CategorySub();
		        		 		cb.setCategorySubId(subId_new);
						        cb.setCategorySubName(subname);
						       
		     		 		 	 cb.setCategoryChildId(childId);
		     		 		 	 System.out.println("childID:"+childId);
		     		 		 	 System.out.println("subname:"+cb.getCategorySubName());
			     	         	 boolean test3 = CategorySubBUS.insertCategorySub(cb,lang);
			     	             if(test3){
			     	            	  	response.sendRedirect("admin/category.html");
			     	             }else{
			     	            	 	
				     	            	//response.sendRedirect("admin/categoryadd.html");
			     	            	 	request.setAttribute("Message","Id already exists");
				     	            	url = "/admin/categoryadd.html";
			                            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			                            rd.forward(request, response);
			     	            	
			     	             }
		        		 	}
		     	    }
		            if(null != act && act.equals("update")){	 
		            	
		               String cateId = (String)request.getParameter("Id");
	        		   String childId = (String)request.getParameter("childId");
	        		   String subId = (String)request.getParameter("subId");
	        		 	
		        	   if(null!=cateId){
		        		   
        		 		   Category cate = CategoryBUS.getCategory(cateId,lang);
	        		 		 
					       cate.setCategoryName(catename);
					       
        	         	   boolean test = CategoryBUS.updateCategory(cate,lang);
        	               if(test){
        	            	   response.sendRedirect("admin/category.html");
        	               }else{
        	             	   
        	               }
        	         	   
	        	        }
	        		 	if(null!=childId){
	        		 		
	     		 		   CategoryChild cc = CategoryChildBUS.getCategoryChild(childId,lang);
		     		 		 	
						   cc.setCategoryChildName(childname);
						        
	     	         	   boolean test = CategoryChildBUS.updateCategoryChild(cc,lang);
	     	               if(test){
	     	            	  response.sendRedirect("admin/category.html");
	     	               }else{
	     	             	   
	     	               }
	        		 	}
	        		 	if(null!=subId){
	        		 		
		     		 		   CategorySub cb = CategorySubBUS.getCategorySub(subId,lang);
			     		 		 	
							   cb.setCategorySubName(subname);
							        
		     	         	   boolean test3 = CategorySubBUS.updateCategorySub(cb,lang);
		     	               if(test3){
		     	            	  response.sendRedirect("admin/category.html");
		     	               }else{
		     	             	   
		     	               }
		        		 }
     	        }         
        			
        	}
        	
           
        }catch(Exception ex){
             out.println(ex.getMessage() );
        } finally {
            out.close();
        }
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
