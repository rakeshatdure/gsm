/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author LyHao
 */
public class MySqlDataAccessHelper {

    
    private Connection connection;

   
    public Connection getConnection() {
        return connection;
    }

    public void open(String lang) {
    	String connectionString = "jdbc:mysql://localhost:3306/";
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            //Set language
            if(null==lang || lang.length()<=0 || lang.equals("MALL_EN")){
            	 connectionString += "seller_en";
            }else{
             	if(lang.equals("MALL_VN")){
             		connectionString += "seller_vi";
             	}else{
             		connectionString += "seller_kr";
             	}
             }

            Properties pros = new Properties();
            pros.setProperty("characterEncoding", "utf8");
            //Set username & password 
            pros.setProperty("user", "root");
            //pros.setProperty("password", "5678");
            this.connection = DriverManager.getConnection(connectionString, pros);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    
    public void close() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {

            Statement sm = this.connection.createStatement();
            rs = sm.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int num = -1;
        try {
            Statement sm = this.connection.createStatement();
            num = sm.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return num;
    }
}
