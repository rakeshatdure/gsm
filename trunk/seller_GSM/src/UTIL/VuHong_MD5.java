package UTIL;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.transaction.SystemException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class  VuHong_MD5 {
	
	public VuHong_MD5() {
		// TODO Auto-generated constructor stub
	}
	
	public static String encodeBase64(String text){
		try {
			return new BASE64Encoder().encode(toByteArray(text));
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String decodeBase64(String text) throws IOException{
		byte[] myBytes = new BASE64Decoder().decodeBuffer(text);
	
		String ss = new String(myBytes,0,myBytes.length,"UTF-8");
		return ss;
	}
	
	private static byte[] toByteArray(String s) throws SystemException {
	    try {
	        return s.getBytes("UTF-8");
	    } catch (UnsupportedEncodingException e) {
	        SystemException se = new SystemException();
	        throw se;
	    }
	}
	
	public String encrypt(String value){		
		try
		{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(value.getBytes());
		BigInteger dis = new BigInteger(1, md5.digest());
		value = dis.toString(16);
		}
		catch(NoSuchAlgorithmException e)
		{
		System.out.print("Cannot encrypt MD5"); 
		}
		return value;
	}
	
}


