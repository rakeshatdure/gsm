package BUS;

import java.util.List;

import DAO.DayDAO;
import POJO.Day;

public class DayBUS {

	 /** Get all Bank **/
   public static List<Day> lstDay(String lang) {
       return DayDAO.lstDay(lang);
   }
   public static int loadDayById(String lang, int id){
	   return DayDAO.loadTimeByDayId(lang,id);
   }
}
