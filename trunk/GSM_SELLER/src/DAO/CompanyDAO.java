package DAO;

import java.sql.ResultSet;
import java.util.List;

import POJO.*;
import UTIL.MySqlDataAccessHelper;

public class CompanyDAO extends HibernateDAO{

	 //Get all Company
    public static List<Company> lstCompany(String lang) {
        return HibernateDAO.getList("from Company", lang);
    }
     
    //Get Company in user
    public static Company getCompany(User user, String lang){
        List kq = HibernateDAO.getList("from Company where user.account='" + user.getAccount()+"'", lang);
        if(kq.size() > 0)
            return (Company)kq.get(0);
        return null;
    }
    
    public static Company getCompanysell( String user , String lang){
        List kq = HibernateDAO.getList("from Company where user.account='" + user+"'", lang);
        if(kq.size() > 0)
            return (Company)kq.get(0);
        return null;
    }
    
    //Get Company in ID
    public static Company getCompany( int id,String lang) {
        return (Company)HibernateDAO.getObject(Company.class, id, lang);
    }

    //Insert Company
    public static boolean insertCompany(Company p,String lang) {
        return HibernateDAO.insert(p, lang);
    }

    //Update Company
    public static boolean updateCompany(Company p,String lang) {
        return HibernateDAO.update(p, lang);
    }

    //Delete Company
    public static boolean deleteCompany(Company p,String lang) {
        return HibernateDAO.delete(p, lang);
    }
    public static Company getcompany(String sellername,String lang){
    	Company company=new Company();
    	MySqlDataAccessHelper helper=new MySqlDataAccessHelper();
    	
    	try{
    		String sql="select company.Fax,company.Established,company.Phone,company.BusinessType, company.CompanyId, company.BusinessNumber, `user`.Account,`user`.FullName," 
    			    +"company.CompanyName,company.Representative,company.Nationality," 
    		        +"company.Address,company.IdentityCard " 
    				+"from company ,`user` ,bank " 
    				+"where company.Account=`user`.Account "
    				+"and company.Account='"+sellername+"'";
    		
    		helper.open(lang);
    		ResultSet rs=helper.executeQuery(sql);
    		while(rs.next()){
    			
    			User user =new User();
                        company.setFax(rs.getString("Fax"));
                        company.setPhone(rs.getString("Phone"));
                        company.setCompanyId(rs.getInt("CompanyId"));
                        company.setBusinessType(rs.getString("BusinessType"));
    			company.setBusinessNumber(rs.getString("BusinessNumber"));
    			company.setEstablished(rs.getDate("Established"));
    			user.setAccount(rs.getString("Account"));
    			user.setFullName(rs.getString("FullName"));
    			company.setCompanyName(rs.getString("CompanyName"));
    			company.setRepresentative(rs.getString("Representative"));
    			company.setNationality(rs.getInt("Nationality"));
    			company.setAddress(rs.getString("Address"));
    			company.setIdentityCard(rs.getString("IdentityCard"));
    			company.setUser(user);
    			
    		}
    	}catch(Exception ex){
    		ex.getMessage();
    	}
    	return company;
    	
    
    }
    
}
