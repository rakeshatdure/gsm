/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UTIL;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import POJO.*;
/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 *
 * @author admin
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory_en;
    private static final SessionFactory sessionFactory_vn;
    private static final SessionFactory sessionFactory_kr;
    
  
	static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory_en = new Configuration().configure("hibernate_en.cfg.xml").buildSessionFactory();
            sessionFactory_vn = new Configuration().configure("hibernate_vn.cfg.xml").buildSessionFactory();
            sessionFactory_kr = new Configuration().configure("hibernate_kr.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactoryEN() {
        return sessionFactory_en;
    }
    
    public static SessionFactory getSessionFactoryVN() {
        return sessionFactory_vn;
    }
    
    public static SessionFactory getSessionFactoryKR() {
        return sessionFactory_kr;
    }
    
    
   
}
