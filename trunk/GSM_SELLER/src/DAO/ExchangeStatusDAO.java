/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.ResultSet;
import java.util.List;
import POJO.*;
import UTIL.MySqlDataAccessHelper;

import java.util.ArrayList;


import org.hibernate.Query;
/**
 *
 * @author admin
 */

public class ExchangeStatusDAO extends HibernateDAO{
    
    //Get all Exchange Order
    public static List<Exchangestatus> lstExchangeStatus(String lang){
        return HibernateDAO.getList("from Exchangestatus order by exchangeStatusId", lang);
    }
    
    //Get ExchangeStatus by ID
    public static Exchangestatus getExchangestatus(int id,String lang){
    	return (Exchangestatus) HibernateDAO.getObject(Exchangestatus.class, id, lang);
    }
           
}
