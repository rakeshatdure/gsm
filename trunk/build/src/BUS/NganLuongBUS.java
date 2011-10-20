package BUS;

import java.util.List;

import DAO.HibernateDAO;
import DAO.NganLuongDAO;
import POJO.Nganluong;

public class NganLuongBUS {
	public static boolean insert(Nganluong nganluong, String lang) {
		return NganLuongDAO.insert(nganluong, lang);
	}

	public static boolean update(Nganluong nganluong, String lang) {
		return NganLuongDAO.update(nganluong, lang);
	}

	public static boolean delete(Nganluong nganluong, String lang) {
		return NganLuongDAO.delete(nganluong, lang);
	}

	public static Nganluong getNganLuong(String nganLuongID, String lang) {
		return NganLuongDAO.getNganLuong(nganLuongID, lang);
	}
	
	public static List<Nganluong> getListNganLuong(String lang) {
		return NganLuongDAO.getListNganLuong(lang);
	}
	
	public static Nganluong getNganLuongBySellerAccount(String sellerAccount,String lang){
		return NganLuongDAO.getNganLuongBySellerAccount(sellerAccount, lang);
	}
}
