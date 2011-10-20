package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.HibernateDAO;
import DAO.NoticeDAO;
import POJO.Notice;
import POJO.Typenotice;

public class NoticeBUS {
	public static List<Notice> lstNotice(String lang) {
		return NoticeDAO.lstNotice(lang);
	}
	
	public static Notice getNotice(int id, String lang) {
		return NoticeDAO.getNotice(id, lang);
	}
	/**Get all Notices  in Typenotice **/
    public static List<Notice> lstNotice(Typenotice tn,String lang) {
    	return NoticeDAO.lstNotice(tn, lang);
    }
    
	public static ArrayList<Notice> lstNoticeType(String lang){
		return NoticeDAO.lstNoticeType(lang);
	}
	
	public static ArrayList<Notice> lstNoticeType2(String lang){
		return NoticeDAO.lstNoticeType(lang);
	}
	public static ArrayList<Notice> lstNoticeType1(String lang){
		return NoticeDAO.lstNoticeType1(lang);
	}
	public static ArrayList<Notice> lstNoticeType3(String lang){
		return NoticeDAO.lstNoticeType3(lang);
	}
}
