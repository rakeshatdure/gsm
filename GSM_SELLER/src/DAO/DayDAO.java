package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import POJO.Category;
import POJO.Day;
import UTIL.MySqlDataAccessHelper;

public class DayDAO extends HibernateDAO{

	//Get all Day
	public static List<Day> lstDay(String lang) {
		MySqlDataAccessHelper heper = new MySqlDataAccessHelper();
		List<Day> lst = new ArrayList<Day>();
		try {
			heper.open(lang);
			String query = "select * from day";
			ResultSet rs = heper.executeQuery(query);
			while(rs.next()){
				Day day = new Day(rs.getInt("DayId"),rs.getInt("Time"));
				lst.add(day);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return lst;
	}

    public static int loadTimeByDayId(String lang, int id){
    	MySqlDataAccessHelper hel = new MySqlDataAccessHelper();
    	try {
			hel.open(lang);
			String query = "SELECT `day`.Time from `day` WHERE `day`.DayId = '"+id+"' ";
			ResultSet rs = hel.executeQuery(query);
			if(rs.next()){
				return rs.getInt("Time");
			}
			else 
				return -1;
		} catch (Exception e) {
			e.getMessage();
			return -1;
		}
    }
}
