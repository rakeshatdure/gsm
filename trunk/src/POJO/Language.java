/**
 * 
 */
package POJO;

/**
 * @author admin
 *
 */
public class Language implements java.io.Serializable{

	 private String Key;
     private String value;
 

     public Language() {
     }

     public Language(String value) {
        this.value = value;
     }
     
	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
   
  

}
