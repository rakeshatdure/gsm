package POJO;
// Generated Aug 2, 2011 4:30:55 PM by Hibernate Tools 3.2.1.GA



/**
 * Bank generated by hbm2java
 */
public class Bank  implements java.io.Serializable {


     private Integer bankId;
     private User user;
     private String accountNumber;
     private String banking;
     private String accountholders;
    public Bank() {
    }

    public Bank(User user, String accountNumber, String banking,String accountholders) {
       this.user = user;
       this.accountNumber = accountNumber;
       this.banking = banking;
       this.accountholders=accountholders;
       
    }
    
   
    public Integer getBankId() {
        return this.bankId;
    }
    
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getAccountNumber() {
        return this.accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getBanking() {
        return this.banking;
    }
    
    public void setBanking(String banking) {
        this.banking = banking;
    }
    public String getAccountholders(){
    	return this.accountholders;
    }
    public void setAccountholders(String accountholders){
    	this.accountholders=accountholders;
    }

}


