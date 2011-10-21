package POJO;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Delivercost implements Serializable {
	private int deliverCostId;
	private String deliverCostName;
	private Float cost;
	private Date updateDate;
	private String typeFee;
	private Float feeExtra;
	private Float conditionForFree;
	private User seller;
	private String deliverCostDefault;
	private String isUser;
	private Set products=new HashSet(0);
	
	


	public Delivercost() {
	}

	public Delivercost(int deliverCostId) {
		this.deliverCostId = deliverCostId;
	}

	public Delivercost(String deliverName, Float cost, Date updateDate,
			String typeFee) {
		this.deliverCostName = deliverName;
		this.cost = cost;
		this.updateDate = updateDate;
		this.typeFee = typeFee;
	}
	
	public Delivercost(String deliverName, Float cost, Date updateDate,
			String typeFee,User seller) {
		this.deliverCostName = deliverName;
		this.cost = cost;
		this.updateDate = updateDate;
		this.typeFee = typeFee;
		this.seller=seller;
	}
	
	public Delivercost(String deliverCostName, Float cost, Date updateDate,
			String typeFee,Float feeExtra,Float conditionForFree,User seller,String isUse,String deliverCostDefault,Set products) {
		this.deliverCostName = deliverCostName;
		this.cost = cost;
		this.updateDate = updateDate;
		this.typeFee = typeFee;
		this.feeExtra=feeExtra;
		this.conditionForFree=conditionForFree;
		this.seller=seller;
		this.isUser=isUse;
		this.deliverCostDefault=deliverCostDefault;
		this.products=products;
	}
	
	public Delivercost(String deliverCostName, Float cost, Date updateDate,
			String typeFee,Float feeExtra,Float conditionForFree,User seller,String isUse,String deliverCostDefault) {
		this.deliverCostName = deliverCostName;
		this.cost = cost;
		this.updateDate = updateDate;
		this.typeFee = typeFee;
		this.feeExtra=feeExtra;
		this.conditionForFree=conditionForFree;
		this.seller=seller;
		this.isUser=isUse;
		this.deliverCostDefault=deliverCostDefault;
		
	}


	public int getDeliverCostId() {
		return this.deliverCostId;
	}

	public void setDeliverCostId(int deliverCostId) {
		this.deliverCostId = deliverCostId;
	}

	public String getDeliverCostName() {
		return deliverCostName;
	}

	public void setDeliverCostName(String deliverCostName) {
		this.deliverCostName = deliverCostName;
	}

	public Float getCost() {
		return this.cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getTypeFee() {
		return typeFee;
	}

	public void setTypeFee(String typeFee) {
		this.typeFee = typeFee;
	}
	
	

	public Float getFeeExtra() {
		return feeExtra;
	}

	public void setFeeExtra(Float feeExtra) {
		this.feeExtra = feeExtra;
	}

	public Float getConditionForFree() {
		return conditionForFree;
	}

	public void setConditionForFree(Float conditionForFree) {
		this.conditionForFree = conditionForFree;
	}
	
	

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public String getDeliverCostDefault() {
		return deliverCostDefault;
	}

	public void setDeliverCostDefault(String deliverCostDefault) {
		this.deliverCostDefault = deliverCostDefault;
	}

	
	public String getIsUser() {
		return isUser;
	}
	
	
	public void setIsUser(String isUser) {
		this.isUser = isUser;
	}

	public Set getProducts() {
		return products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

	
}
