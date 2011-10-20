/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;

import java.util.Date;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Out;
/**
 *
 * @author admin
 */
public class ExchangeStatusBUS {
    
    /** Get all products **/
    public static List<Exchangestatus> lstExchangeStatus(String lang) {
        return ExchangeStatusDAO.lstExchangeStatus(lang);
    }
    public static Exchangestatus getExchangeStatus(int id, String lang){
    	return ExchangeStatusDAO.getExchangestatus(id, lang);
    }
    
}
