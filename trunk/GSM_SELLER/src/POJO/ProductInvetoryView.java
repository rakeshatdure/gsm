package POJO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;

@ManagedBean
@SessionScoped
@Entity
public class ProductInvetoryView implements Serializable{
	private static final long serialVersionUID = 7155539652007397752L;
	
	private int productCode;
	private String productName;
	private String producer;
	private int invetoryCode;
	private String stateIventory;
	private int amount;
	private Date expired;
	
	public ProductInvetoryView(int productCode, String productName,
			String producer, int invetoryCode, String stateIventory,
			int amount, Date expired) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.producer = producer;
		this.invetoryCode = invetoryCode;
		this.stateIventory = stateIventory;
		this.amount = amount;
		this.expired = expired;
	}

	public ProductInvetoryView() {
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getInvetoryCode() {
		return invetoryCode;
	}

	public void setInvetoryCode(int invetoryCode) {
		this.invetoryCode = invetoryCode;
	}

	public String getStateIventory() {
		return stateIventory;
	}

	public void setStateIventory(String stateIventory) {
		this.stateIventory = stateIventory;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getExpired() {
		SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
		return fo.format(expired);
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	

}
