package DAO;

import java.util.Date;
import java.util.List;


import POJO.Nganluong;

public class NganLuongDAO extends HibernateDAO {
	public static boolean insert(Nganluong nganluong, String lang) {
		return HibernateDAO.insert(nganluong, lang);
	}

	public static boolean update(Nganluong nganluong, String lang) {
		return HibernateDAO.update(nganluong, lang);
	}

	public static boolean delete(Nganluong nganluong, String lang) {
		return HibernateDAO.delete(nganluong, lang);
	}

	@SuppressWarnings("unchecked")
	public static Nganluong getNganLuong(String nganLuongID, String lang) {
		List<Nganluong> list = HibernateDAO.getList("from Nganluong", lang);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public static List<Nganluong> getListNganLuong(String lang) {
		return HibernateDAO.getList("from Nganluong", lang);
	}
	
	@SuppressWarnings("unchecked")
	public static Nganluong getNganLuongBySellerAccount(String sellerAccount,String lang){
		List<Nganluong> list = HibernateDAO.getList("from Nganluong where sellerAccount='"+sellerAccount+"'", lang);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public static void main(String[] args) {
		String lang="MALL_EN";
		//Nganluong nl=new Nganluong("adfa", "adf", "adf", "sell01", new Date(), new Date());
		//insert(nl, lang);
		System.out.println(getNganLuongBySellerAccount("sell01", lang).getReceiver());
	}
}
