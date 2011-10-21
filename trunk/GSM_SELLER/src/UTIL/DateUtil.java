/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package UTIL;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *Purpose: this class is used for convert a java.util.Date to a date string like: dd/MM/yyyy and in the date string to Date.
 * @author Tran Phuoc Nguyen
 */
public class DateUtil {
    /**Lấy chuỗi ngày theo định dạng dd/mm/yyy*/
    public static String getDateString(Date d)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(d);
    }
    /**Lấy chuỗi ngày theo định dạng mm/yyy*/
    public static String getMonthYear(Date d)
    {
        DateFormat df = new SimpleDateFormat("MMyyyy");
        return df.format(d);
    }
    /**Lấy ngày từ chuỗi theo định dạng yyyy-MM-dd*/
    public static Date getDateFromString(String s) throws ParseException
    {
        Date d = new Date();
        try {
            if(s == null || s.equals(""))
            {
                return null;
            }else{
                String[] temp = s.split("-");
                int iDay = Integer.parseInt(temp[0].toString());
                int iMonth = Integer.parseInt(temp[1].toString());
                int iYear = Integer.parseInt(temp[2].toString());

                String DateString = iYear + "-" + iMonth + "-" + iDay;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                d = df.parse(DateString);

            }
        } catch (ParseException e) {
	        System.err.println("Format date error: "+e.getMessage());
	}
        return d;

    }
        /**Chuyển date thành String*/
    	public static String convertDateToString(Date date, String format) {
	    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
	    return mySimpleDateFormat.format(date);
	}
        /**Chuyển String thành date*/
	public static Date convertStringToDate(String dateStr, String format) {
	    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
	    try {
	        return mySimpleDateFormat.parse(dateStr);
            } catch (ParseException e) {
	        return null;
	    }
	    
	}

}
