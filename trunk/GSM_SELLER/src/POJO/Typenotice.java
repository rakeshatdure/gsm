package POJO;

import java.util.HashSet;
import java.util.Set;
// Generated Jul 26, 2011 2:51:41 PM by Hibernate Tools 3.2.1.GA



/**
 * Typenotice generated by hbm2java
 */
public class Typenotice  implements java.io.Serializable {


     private int typeNoticeId;
     private String nameTypeNotice;
     private Set notices = new HashSet(0);
     
  
	public Typenotice() {
    }

    public Typenotice(int typeNoticeId, String nameTypeNotice, Set notices) {
       this.typeNoticeId = typeNoticeId;
       this.nameTypeNotice = nameTypeNotice;
       this.notices = notices;
    }
   
    public int getTypeNoticeId() {
        return this.typeNoticeId;
    }
    
    public void setTypeNoticeId(int typeNoticeId) {
        this.typeNoticeId = typeNoticeId;
    }
    public String getNameTypeNotice() {
        return this.nameTypeNotice;
    }
    
    public void setNameTypeNotice(String nameTypeNotice) {
        this.nameTypeNotice = nameTypeNotice;
    }

    public Set getNotices() {
		return notices;
	}

	public void setNotices(Set notices) {
		this.notices = notices;
	}



}


