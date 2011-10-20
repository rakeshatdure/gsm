package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import POJO.*;
import UTIL.MySqlDataAccessHelper;

public class NoticeDAO extends HibernateDAO {
	
	// get list notice
	public static List<Notice> lstNotice(String lang) {
		return HibernateDAO.getList("from Notice", lang);
	}

	public static Notice getNotice(int id, String lang) {
		return (Notice) HibernateDAO.getObject(Notice.class, id, lang);
	}
	
	//Get all Notices  in Typenotice
    public static List<Notice> lstNotice(Typenotice tn,String lang) {
        return HibernateDAO.getList("from Notice where typenotice.typeNoticeId="+tn.getTypeNoticeId(),lang);
    }

	public static ArrayList<Notice> lstNoticeType(String lang) {
		ArrayList<Notice> lstNotice = new ArrayList<Notice>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try {
			String sql = "select notice.noticeId,typenotice.NameTypeNotice,notice.Title,notice.DateNotice,notice.Account from typenotice, notice where typenotice.TypeNoticeId=1 and  notice.TypeNoticeId=1";
			helper.open(lang);
			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				Notice notice = new Notice();
				Typenotice typenotice = new Typenotice();
				User user = new User();
				notice.setNoticeId(rs.getInt("noticeId"));
				notice.setTitle(rs.getString("Title"));
				typenotice.setNameTypeNotice(rs.getString("nameTypeNotice"));
				notice.setDateNotice(rs.getDate("dateNotice"));
				notice.setTypenotice(typenotice);
				lstNotice.add(notice);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return lstNotice;
	}

	public static ArrayList<Notice> lstNoticeType2(String lang) {
		ArrayList<Notice> lstNotice = new ArrayList<Notice>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try {
			String sql = "select  notice.NoticeId,TypeNotice.NameTypeNotice,notice.Title,notice.DateNotice,notice.Account from TypeNotice, notice where TypeNotice.TypeNoticeId=notice.TypeNoticeId and TypeNotice.TypeNoticeId=1";
			helper.open(lang);
			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				Notice notice = new Notice();
				Typenotice typenotice = new Typenotice();
				User user = new User();
				notice.setNoticeId(rs.getInt("noticeId"));
				notice.setTitle(rs.getString("Title"));
				typenotice.setNameTypeNotice(rs.getString("nameTypeNotice"));
				notice.setDateNotice(rs.getDate("dateNotice"));
				notice.setTypenotice(typenotice);
				lstNotice.add(notice);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return lstNotice;
	}

	public static ArrayList<Notice> lstNoticeType1(String lang) {
		ArrayList<Notice> lstNotice = new ArrayList<Notice>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try {
			String sql = "SELECT notice.ContentNotice,notice.DateNotice,notice.NoticeId,notice.Title,TypeNotice.NameTypeNotice from notice,TypeNotice where notice.TypeNoticeId=TypeNotice.TypeNoticeId";
			helper.open(lang);
			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				Notice notice = new Notice();
				Typenotice typenotice = new Typenotice();
				User user = new User();
				notice.setNoticeId(rs.getInt("noticeId"));
				notice.setContentNotice(rs.getString("ContentNotice"));
				notice.setTitle(rs.getString("Title"));
				typenotice.setNameTypeNotice(rs.getString("nameTypeNotice"));
				notice.setDateNotice(rs.getDate("dateNotice"));
				notice.setTypenotice(typenotice);
				lstNotice.add(notice);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return lstNotice;
	}
	
	public static ArrayList<Notice> lstNoticeType3(String lang) {
		ArrayList<Notice> lstNotice = new ArrayList<Notice>();
		MySqlDataAccessHelper helper = new MySqlDataAccessHelper();
		try {
			String sql = "select notice.NoticeId,TypeNotice.NameTypeNotice,notice.Title,notice.DateNotice,notice.Account from TypeNotice, notice where TypeNotice.TypeNoticeId=notice.TypeNoticeId  and  TypeNotice.TypeNoticeId=2";
			helper.open(lang);
			ResultSet rs = helper.executeQuery(sql);
			while (rs.next()) {
				Notice notice = new Notice();
				Typenotice typenotice = new Typenotice();
				User user = new User();
				notice.setNoticeId(rs.getInt("noticeId"));
				notice.setTitle(rs.getString("Title"));
				typenotice.setNameTypeNotice(rs.getString("nameTypeNotice"));
				notice.setDateNotice(rs.getDate("dateNotice"));
				notice.setTypenotice(typenotice);
				lstNotice.add(notice);
			}
		} catch (Exception ex) {
			ex.getMessage();
		}

		return lstNotice;
	}
}
