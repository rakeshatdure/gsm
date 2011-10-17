/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import javax.servlet.http.HttpSession;

import POJO.*;
import BUS.*;
import DAO.*;

import UTIL.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
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

import org.apache.tomcat.util.http.fileupload.DiskFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
//import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.hibernate.Session;

/**
 * 
 * @author wwe
 */
@WebServlet(name = "uploadController", urlPatterns = { "/uploadController" })
public class uploadController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			ParseException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		ServletContext app = getServletContext();

		try {
			String url = null;
			// test login
			String login = (String) session.getAttribute("username");
			if (login == null || login.isEmpty()) {
				response.sendRedirect("../login.html");
			}
			// boolean isMultiPart =
			// ServletFileUpload.isMultipartContent(request);
			boolean isMultiPart = DiskFileUpload.isMultipartContent(request);
			if (!isMultiPart) {

			} else {
				// FileItemFactory factory = new DiskFileItemFactory();
				// ServletFileUpload upload = new ServletFileUpload(factory);
				DiskFileUpload upload = new DiskFileUpload();
				List items = null;
				try {
					items = upload.parseRequest(request);
				} catch (FileUploadException e) {
					e.printStackTrace();
				}

				Iterator iter = items.iterator();
				final Hashtable params = new Hashtable();

				final ArrayList<String> fileName = new ArrayList<String>();
				int inde = 0;
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					// System.out.println("item: "+item);
					if (item.isFormField()) {
						// System.out.println("item.getFieldName(): "+item.getFieldName());
						params.put(item.getFieldName(), item.getString("UTF-8"));
					} else {
						try {
							String itemName = item.getName();
							if (!"".equals(itemName)) {
								// System.out.println("itemName: "+itemName);
								String filename = itemName.substring(itemName
										.lastIndexOf("\\") + 1);
								fileName.add(filename);
								String RealPath = getServletContext()
										.getRealPath("/")
										+ "images/fashion/"
										+ fileName.get(inde);

								inde++;
								File savedFile = new File(RealPath);
								System.out.println("RealPath: "+RealPath);
								item.write(savedFile);
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				String lang = (String) app.getAttribute("MALL_LA");
				final String pid = (String) params.get("pId");
				final String productname = (String) params.get("product_name");

				final String amount = (String) params.get("product_amount");
				String prePrice = (String) params.get("product_price");
				final float price = Float.parseFloat(prePrice);

				final String categoryChild = (String) params
						.get("selectCategoryChild");
				final String categorySub = (String) params
						.get("selectCategorySub");

				final String selectManufactural = (String) params.get("selectManufactural");
				System.out.println("selectManufactural: "+selectManufactural);

				final Date currentday = new Date();
				SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");

				final String detail = (String) params.get("shortDescription");

				final String color = (String) params.get("color");

				final String size = (String) params.get("size");
				final String origin = (String) params.get("origin");
				final String pType = (String) params.get("productType");
				final String vPeriod = (String) params.get("validityPeriod");
				final String de_CostID = (String) params.get("de_CostID");

				final String unit = LanguegeBUS.getValue("dvtDef", lang);
				String account = session.getAttribute("username").toString();

				final User newUser = UserBUS.getUser(account, lang);

				TransactionMethod tr = new TransactionMethod() {
					@Override
					protected void doMethod(Session session, String lang) {

						if (pid == null || pid.isEmpty()) {
							Products newProduct = new Products();
							newProduct.setUser(newUser);

							newProduct.setCategoryChildId(categoryChild);
							if (!categorySub.equals("0")) {
								newProduct.setCategorySubId(categorySub);
							}
							int manufacturer=-1;
							Manufacturer manu=ManufacturerBUS.getManufacturerByName(selectManufactural, lang);
							if(manu==null){
								manu=new Manufacturer(selectManufactural);
								save(manu, session);								
							}
							manufacturer=manu.getManufacturerId();
							
							newProduct.setManufacturerId(manufacturer);
							newProduct.setColor(color);
							newProduct.setDetail(detail);
							newProduct.setProductName(productname);
							newProduct.setPrice(price);
							newProduct.setSize(size);
							newProduct.setAmount(Integer.parseInt(amount));
							newProduct.setUnit(unit);
							newProduct.setOriginId(Integer.parseInt(origin));
							newProduct
									.setProductTypeId(Integer.parseInt(pType));
							newProduct.setUploadDate(currentday);
							if (de_CostID != null
									&& !("null".equals(de_CostID))) {
								Delivercost deliverCost = DeliverCostBUS
										.getDeliverCostById(
												Integer.parseInt(de_CostID),
												lang);
								newProduct.setDeliverCost(deliverCost);
							}

							// boolean kqPro =
							// ProductBUS.insertProducts(newProduct,lang);
							// if(kqPro){
							save(newProduct, session);
							// insert images of this product
							int productid = newProduct.getProductId();
							for (int i = 0; i < fileName.size(); i++) {
								Productphotos newProductphoto = new Productphotos();
								newProductphoto.setProductPhotoName(fileName
										.get(i));
								newProductphoto.setProductId(productid);

								// ProductPhotoBUS.insertProductPhotos(newProductphoto,lang);
								save(newProductphoto, session);
							}
							// insert inventory
							Inventory iv = new Inventory();
							Date d = new Date();
							Calendar c = Calendar.getInstance();
							c.setTime(d);
							c.add(Calendar.DATE, Integer.parseInt(vPeriod)); // number
																				// of
																				// days
																				// to
																				// add
							d = c.getTime();
							iv.setLimitDate(d);
							iv.setProductId(newProduct.getProductId());
							if (Integer.parseInt(vPeriod) == 0) {
								iv.setSateId(1);
							} else {
								iv.setSateId(2);
							}
							// InventoryDAO.insertInventory(iv, lang);
							save(iv, session);
							// }

						} else {
							Products p = ProductBUS.getProducts(
									Integer.parseInt(pid), lang);
							String expDate = (String) params.get("expDate");

							p.setCategoryChildId(categoryChild);
							if (!categorySub.equals("0")) {
								p.setCategorySubId(categorySub);
							}
							int manufacturer=-1;
							Manufacturer manu=ManufacturerBUS.getManufacturerByName(selectManufactural, lang);
							if(manu==null){
								manu=new Manufacturer(selectManufactural);
								save(manu, session);								
							}
							manufacturer=manu.getManufacturerId();
							p.setManufacturerId(manufacturer);
							p.setColor(color);
							p.setDetail(detail);
							p.setProductName(productname);
							p.setPrice(price);
							p.setSize(size);
							p.setAmount(Integer.parseInt(amount));
							p.setUnit(unit);
							p.setOriginId(Integer.parseInt(origin));
							p.setProductTypeId(Integer.parseInt(pType));
							p.setUploadDate(currentday);
							if (de_CostID != null
									&& !("null".equals(de_CostID))) {
								Delivercost deliverCost = DeliverCostBUS
										.getDeliverCostById(
												Integer.parseInt(de_CostID),
												lang);
								p.setDeliverCost(deliverCost);
							}
							// /ProductBUS.updateProducts(p,lang);
							update(p, session);
							if (Integer.parseInt(vPeriod) > 0) {

								// Insert Limit Date
								Extension ext = new Extension();
								ext.setProductId(p.getProductId());
								// insert inventory
								List<Inventory> lst = InventoryDAO
										.lstInvenProductId(p, lang);
								if (null == lst || lst.isEmpty()) {
									Inventory iv = new Inventory();
									Date d = new Date();

									iv.setLimitDate(d);
									iv.setProductId(p.getProductId());
									iv.setSateId(1);
									// InventoryDAO.insertInventory(iv, lang);
									save(iv, session);
									ext.setLimitDateOld(d);
								} else {
									Inventory in = (Inventory) lst.get(0);
									ext.setLimitDateOld(in.getLimitDate());
								}
								// ----end insert inventory
								ext.setLimitDateNum(Integer.parseInt(vPeriod));
								ext.setCharge(Float.valueOf(0));
								ext.setStatus("N");
								// ExtensionDAO.insertExtension(ext, lang);
								save(ext, session);
								// ---end Insert Limit Date
							}
						}
					}
				};
				boolean result = tr.executeTransaction(lang);
				if (result) {
					request.setAttribute("Message", "Upload complete !!!");
				} else {
					request.setAttribute("Message", "Upload unsuccessful !!!");
				}
				url = "/sale/upload.html";
				RequestDispatcher rd = getServletContext()
						.getRequestDispatcher(url);
				rd.forward(request, response);
			}

		} finally {
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ParseException ex) {
			Logger.getLogger(uploadController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ParseException ex) {
			Logger.getLogger(uploadController.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
