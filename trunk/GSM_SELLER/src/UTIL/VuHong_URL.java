package UTIL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class VuHong_URL {
	public String encode(String url) throws UnsupportedEncodingException{
		return URLEncoder.encode(url,"UTF-8");
	}
	public String decode(String url) throws UnsupportedEncodingException{
		return URLDecoder.decode(url,"UTF-8");
	}
}
