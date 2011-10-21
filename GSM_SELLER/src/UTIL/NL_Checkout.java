package UTIL;
import java.net.*;
import java.util.*;
import java.security.*;

//class support các hàm mã hóa MD5

public class NL_Checkout {

	// URL chheckout của nganluong.vn
	private static String nganluong_url = "https://www.nganluong.vn/checkout.php";
	
	// Mã merchante site
	private static String merchant_site_code = "20207";	// Biến này được nganluong.vn cung cấp khi bạn đăng ký merchant site
	
	// Mật khẩu bảo mật
	private static String secure_pass = "p123456";	// Biến này được nganluong.vn cung cấp khi bạn đăng ký merchant site
    public static final int MD5 = 1;
    public static final int SHA1 = 2;
    private int algorithm = 1;
    private static String algorithmName = "MD5";

  ///tinh ma bam cho data va tra ve base64 - mã hóa MD5
    public static String  hash(String messages) throws Exception{
        if(messages==null) throw new NullPointerException();

        StringBuffer result = null;
        byte[] data = messages.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance(algorithmName);
            md.reset();
            md.update(data);
            byte[] msgDigest = md.digest();
            result = new StringBuffer();
            for(int i=0; i<msgDigest.length; i++) {
                String hex = Integer.toHexString(0xff & msgDigest[i]);
                if(hex.length()==1) result.append('0');
                result.append(hex);
            }
        }
        catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.toString());
        }
        return result.toString();
    }
	
    //Hàm xây dựng url, trong đó có tham số mã hóa (còn gọi là public key)
	public static String buildCheckoutUrl(String return_url,String receiver,String order_code,String price,String transaction_info)
	{
		Hashtable arr_param = new Hashtable();
		arr_param.put("merchant_site_code",merchant_site_code.toString());
		arr_param.put("return_url",URLEncoder.encode(return_url).toString().toLowerCase());
		arr_param.put("receiver",receiver.toString());
		arr_param.put("transaction_info", transaction_info.toString());
		arr_param.put("order_code",order_code.toString());
		arr_param.put("price",price);

		
		/* b1. tao public key*/
		String secure_code = "";
		
		
		secure_code =merchant_site_code.toString()
					+" "+URLEncoder.encode(return_url).toLowerCase()
					+" "+receiver.toString()
					+" "+transaction_info.toString()
					+" "+order_code.toString()
					+" "+price.toString();
		
		//Thực hiện mã hóa secure_code
		try {
			String strText = secure_code+" "+secure_pass;
			//System.out.print("Trungtk:"+strText+"\n");
			//gọi hàm mã hóa MD5
			//String strEecode = md.hash(strText);
			String strEecode = hash(strText);
			
			arr_param.put("secure_code",strEecode);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		
		
		/* b2. kiem tra return_url xem có '?' ko, neu chua có the bo sung vào*/
		String  redirect_url = nganluong_url;
		if (redirect_url.indexOf("?") == -1)
		{
			redirect_url += "?";
		}
		else if (redirect_url.substring(redirect_url.length()-1,redirect_url.length())== "?" && redirect_url.indexOf("&") == -1)
		{
			//neu return_url có '?' nhung không ket thúc bang '?' và chua có dau '&' thi bo sung vào cuoi
			redirect_url+="&";			
		}
	    
		/* b3. tao url*/
		String url = "";

		for (Enumeration e = arr_param.keys (); e.hasMoreElements();)
		{
			String key = (String)e.nextElement();
			String val = (String)arr_param.get(key);
			if (url == "")
			{
				url += key+"="+val;
			}
			else
			{
				url += "&"+key+"="+val;
			}
		}
		
		return redirect_url+url;
	}
	  
	public static Boolean verifyPaymentUrl(String transaction_info,String order_code,String price,String payment_id,String payment_type,String error_text,String secure_code)
	{
		Boolean result = false;
	
		/* 1. tao public key bang url param và secure_pass: */
		String verify_secure_code = "";
		verify_secure_code =" "+transaction_info.toString()
							+" "+order_code.toString()
							+" "+price.toString()
							+" "+payment_id.toString()
							+" "+payment_type.toString()
							+" "+error_text.toString();


		//thực hiện mã hóa verify_secure_code
		try
		{
			verify_secure_code = hash(verify_secure_code+" "+merchant_site_code+" "+secure_pass);
		}
	    catch (Exception e) {
	    	System.err.println(e.getMessage());
		}
	
		//System.out.println("verify_secure_code: "+verify_secure_code);
		//System.out.println("secure_code: "+secure_code);
		/* 2. kiem tra security code*/
		if (verify_secure_code.equals(secure_code))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	 
	public static Boolean verifyUpdatePaymentUrl(String transaction_info,String order_code,String payment_id,String payment_type,String secure_code)
	{
		Boolean result = false;
	
		/* 1. tao public key bang url param và secure_pass: */
		String verify_secure_code = "";
		verify_secure_code =" "+transaction_info.toString()
							+" "+order_code.toString()
							+" "+payment_id.toString()
							+" "+payment_type.toString();


		//thực hiện mã hóa verify_secure_code
		try
		{
			verify_secure_code = hash(verify_secure_code+" "+secure_pass);
		}
	    catch (Exception e) {
	    	System.err.println(e.getMessage());
		}
	
		//System.out.println("verify_secure_code: "+verify_secure_code);
		//System.out.println("secure_code: "+secure_code);
		/* 2. kiem tra security code*/
		if (verify_secure_code.equals(secure_code))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	public static Boolean verifyRefundPaymentUrl(String transaction_info,String order_code,String payment_id,String refund_payment,String secure_code)
	{
		Boolean result = false;
	
		/* 1. tao public key bang url param và secure_pass: */
		String verify_secure_code = "";
		verify_secure_code =" "+transaction_info.toString()
							+" "+order_code.toString()
							+" "+payment_id.toString()
							+" "+refund_payment.toString();


		//thực hiện mã hóa verify_secure_code
		try
		{
			verify_secure_code = hash(verify_secure_code+" "+secure_pass);
		}
	    catch (Exception e) {
	    	System.err.println(e.getMessage());
		}
	
		//System.out.println("verify_secure_code: "+verify_secure_code);
		//System.out.println("secure_code: "+secure_code);
		/* 2. kiem tra security code*/
		if (verify_secure_code.equals(secure_code))
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;
	}
	
	
	
	public static void main(String[] args) {
		verifyUpdatePaymentUrl("", "", "", "", "");
	}

}
