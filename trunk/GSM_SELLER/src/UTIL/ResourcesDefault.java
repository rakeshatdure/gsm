package UTIL;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.mail.Email;

import BUS.CategoryChildBUS;
import BUS.EmailConfigureBUS;
import POJO.CategoryChild;
import POJO.Emailconfigure;
import POJO.Products;

public class ResourcesDefault {
	// Deliver status:
	public static final int PENDING_DELIVER = 1;
	public static final int PROCESS_DELIVER = 3;
	public static final int DELAY_DELIVER = 4;
	public static final int FINISHED_DELIVER = 6;
	public static final int CANCEL_DELIVER = 6;

	public static final String PENDING_DELIVER_STATUS = "Pending";
	public static final String PROCESS_DELIVER_STATUS = "Process deliver";
	public static final String DELAY_DELIVER_STATUS = "Delay";
	public static final String FINISHED_DELIVER_STATUS = "Finished deliver";
	public static final String CANCEL_DELIVER_STATUS = "Cancel";

	public static final String TRUE = "true";
	public static final String FALSE = "false";

	public static final String YES = "Y";
	public static final String NO = "N";

	public static final String FREE = "Free";
	public static final String HAS_FEE = "Has fee";
	public static final String FEE_HAS_CONDITION = "Free has condition";
	public static final String PREPAY = "Prepay";
	// order cancel
	public static final String CANCEL_BUYER = "B";
	public static final String CANCEL_SELLER = "S";
	// order_detail_status_id
	public static final int ODS_PAYMENT_PENDING = 1;
	public static final int ODS_SHIPPING = 2;
	public static final int ODS_CANCEL = 3;
	public static final int ODS_RETURN_EXCHANGE = 4;

	// nganluong
	public static String RECEIVER = "lmlnl19@yahoo.com";
	public static String SECURE_PASS = "p123456";
	public static String MERCHANT_SITE_CODE = "20207";
	public static String RETURN_ULR = "http://14.63.212.204:8080/mallshopping/nl_completed.jsp";

	// round the float number
	public static float Round(float Rval, int Rpl) {
		float p = (float) Math.pow(10, Rpl);
		Rval = Rval * p;
		float tmp = Math.round(Rval);
		return (float) tmp / p;
	}

	// get total money
	public static float getTotalMoney(float price, int quantity, float option,
			float shipping) {

		float total = 0;
		total = price * quantity;
		if (option != 0) {
			total += option;
		}
		if (shipping != 0) {
			total += shipping;
		}
		return Round(total, 2);
	}

	// get total money
	public static int getTotalMoneyForVN_Language(float price, int quantity,
			float option, float shipping) {
		float total = getTotalMoney(price, quantity, option, shipping);
		return Float.valueOf(total).intValue();
	}

	// scroll to the window bottom
	public static String loadCrollBottom() {
		return "var bottomPosition = $(document).height(); $('body,html').animate({scrollTop:bottomPosition},800);";
	}

	// send th simple mail
	public static boolean sendSimpleEMail(String receiver, String receiverName,
			String content, String subject, String lang) throws Exception {
		EmailService es = new EmailService();
		Emailconfigure ec = EmailConfigureBUS.getEmailConfigure(1, lang);
		if (ec != null) {
			es.setSender(VuHong_MD5.decodeBase64(ec.getEmail()));
			es.setPassword(VuHong_MD5.decodeBase64(ec.getPassword()));
			es.setHostName(ec.getHostName());
			es.setPort(ec.getPortNumber());
			es.setSenderName(ec.getEmailName());
			es.setSsl(Boolean.parseBoolean(ec.getSsl()));
		}
		if (subject != null)
			es.setSubject(subject);

		return es.sendMail(receiver, receiverName, content);

	}

	public static void main(String[] args) {
		System.out.println(Math.round(1.4));
		System.out.println(Round(1.144f, 2));
	}

	/** sort list products by category **/
	public static List<Products> sortListProductByCategory(List<Products> list,final String lang) {
		Comparator<Products> c = new Comparator<Products>() {
			@Override
			public int compare(Products p1, Products p2) {
				CategoryChild child1 = CategoryChildBUS.getCategoryChild(
						p1.getCategoryChildId(), lang);
				CategoryChild child2 = CategoryChildBUS.getCategoryChild(
						p2.getCategoryChildId(), lang);
				return child1.getCategoryId().compareTo(child2.getCategoryId());
			}
		};

		Collections.sort(list, c);
		return list;
	}

}
