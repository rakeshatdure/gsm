package POJO;
// Generated May 31, 2011 10:22:52 AM by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * News generated by hbm2java
 */
public class News  implements java.io.Serializable {


     private Integer newsId;
     private Newscategory newscategory;
     private String newsTitle;
     private String summary;
     private String content;
     private String author;
     private Date date;
     private Set newsphotoses = new HashSet(0);

    public News() {
    }

	
    public News(Newscategory newscategory, String newsTitle, String summary, String content, String author, Date date) {
        this.newscategory = newscategory;
        this.newsTitle = newsTitle;
        this.summary = summary;
        this.content = content;
        this.author = author;
        this.date = date;
    }
    public News(Newscategory newscategory, String newsTitle, String summary, String content, String author, Date date, Set newsphotoses) {
       this.newscategory = newscategory;
       this.newsTitle = newsTitle;
       this.summary = summary;
       this.content = content;
       this.author = author;
       this.date = date;
       this.newsphotoses = newsphotoses;
    }
   
    public Integer getNewsId() {
        return this.newsId;
    }
    
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
    public Newscategory getNewscategory() {
        return this.newscategory;
    }
    
    public void setNewscategory(Newscategory newscategory) {
        this.newscategory = newscategory;
    }
    public String getNewsTitle() {
        return this.newsTitle;
    }
    
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    public String getAuthor() {
        return this.author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Set getNewsphotoses() {
        return this.newsphotoses;
    }
    
    public void setNewsphotoses(Set newsphotoses) {
        this.newsphotoses = newsphotoses;
    }




}

