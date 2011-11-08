/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import UTIL.HibernateUtil;
/**
 *
 * @author Mr Long
 */
public abstract class HibernateDAO {
	
	public static Session loadSession(String lang){
		Session se;
        if(null==lang || lang.length()<=0 || lang.equals("MALL_VN")){
        	se =  HibernateUtil.getSessionFactoryVN().openSession();
        }else{
        	if(lang.equals("MALL_EN")){
        		se =  HibernateUtil.getSessionFactoryEN().openSession();
        	}else{
        		se =  HibernateUtil.getSessionFactoryKR().openSession();
        	}
        }
        return se;
	}

    /**Rút trích danh sách*/
    protected static List getList(String sql, String lang)
    {
    	//System.out.println("langHiberbategetlist: "+lang);
        List lst = null;
        Session se=loadSession(lang);
        
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            Query q = se.createQuery(sql);
            lst =  q.list();
            trans.commit();
        }
        catch(Exception ex)
        {
            trans.rollback();
            //ex.printStackTrace();
           System.err.print(ex);
        }
        finally
        {
            se.close();
        }

       return lst;
    }

    /**Rút trích danh sách (có phân trang)*/
    protected static List getList(String sql,int pageNumber, int pageSize, String lang)
    {
        List lst = null;
        Session se=loadSession(lang);
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            Query q = se.createQuery(sql);
            q.setFirstResult(pageSize * pageNumber); //Set trang bắt đầu
            q.setMaxResults(pageSize);  //Set kích thướt 1 trang
            lst =  q.list();
            trans.commit();
        }
        catch(Exception ex)
        {
            trans.rollback();
            //ex.printStackTrace();
           System.err.print(ex);
        }
        finally
        {
            se.close();
        }

       return lst;
    }
    
    /**Rút trích danh sách (có phân trang) and get listSize*/
    protected static List[] getListSize(String sql,int pageNumber, int pageSize, String lang)
    {
    	List[] arrays=new List[2];
    	
        List lst = null;
        Session se=loadSession(lang);
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            Query q = se.createQuery(sql);
            arrays[0]=q.list();
            q.setFirstResult(pageSize * pageNumber); //Set trang bắt đầu
            q.setMaxResults(pageSize);  //Set kích thướt 1 trang
            lst =  q.list();
            arrays[1]=lst;
            trans.commit();
        }
        catch(Exception ex)
        {
            trans.rollback();
            //ex.printStackTrace();
           System.err.print(ex);
        }
        finally
        {
            se.close();
        }

       return arrays;
    }

    /**Rút trích danh sách (có phân trang) and get ListMaps*/
    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected static Map getListMap(String sql,int pageNumber, int pageSize, String lang)
    {
    	Map maps=new HashMap();
    	
    	Session se=loadSession(lang);
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            Query q = se.createQuery(sql);
            maps.put("rows",q.list().size());
            q.setFirstResult(pageSize * pageNumber); //Set trang bắt đầu
            q.setMaxResults(pageSize);  //Set kích thướt 1 trang
            maps.put("list",q.list());
            trans.commit();
        }
        catch(Exception ex)
        {
            trans.rollback();
            //ex.printStackTrace();
           System.err.print(ex);
        }
        finally
        {
            se.close();
        }

       return maps;
    }

    /** Get Object in Id (String)
     *  Class<?> A : Object 
     *  Id: ObjectId
     */
    protected static Object getObject(Class<?> A, Serializable  Id, String lang)
    {
        Object ob = new Object();
        Session se=loadSession(lang);
        Transaction trans = null;
        try
        {

           trans = se.beginTransaction();
           ob = (Object)se.get(A,Id);
           trans.commit();
          }
        catch(Exception ex)
        {
            trans.rollback();
            //ex.printStackTrace();
            System.err.print(ex);
        }
        finally
        {
            se.close();
        }

        return ob;
    }
    
    /**Thêm đối tượng mới*/
    protected static boolean insert(Object ob, String lang)
    {
        boolean bol = true;
        Session se=loadSession(lang);
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            se.save(ob);
            trans.commit();
        }
        catch(Exception ex)
        {
            bol =  false;
            trans.rollback();
            System.err.print(ex);
            //ex.printStackTrace();
        }
        finally
        {
            se.close();
        }
        return bol;
    }

    /**Xóa đối tượng mới*/
    protected static boolean delete(Object ob, String lang)
    {
        boolean bol = true;
        Session se=loadSession(lang);
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            se.delete(ob);
            trans.commit();
        }
        catch(Exception ex)
        {
            bol = false;
            trans.rollback();
            //ex.printStackTrace();
            System.err.print(ex);
        }
        finally
        {
            se.close();
        }
        return bol;
    }

    /**Cập nhật đối tượng*/
    protected static boolean update(Object ob, String lang)
    {
        boolean bol = true;
        Session se=loadSession(lang);
        Transaction trans = null;
        try
        {
            trans = se.beginTransaction();
            se.update(ob);
            trans.commit();
        }
        catch(Exception ex)
        {
            bol = false;
            trans.rollback();
            System.err.print(ex);
            //ex.printStackTrace();
        }
        finally
        {
            se.close();
        }
        return bol;
    }
    
    public static void main(String[] args) {
		Map map=new HashMap();
		map.put("rows", 2);
		List<Object> lst=new  ArrayList<Object>();
		lst.add("luan");
		map.put("list", lst);
		System.out.println(((List) map.get("list")).get(0));
	}
}
