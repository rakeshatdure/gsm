package POJO;

import java.util.HashSet;
import java.util.Set;
// Generated May 31, 2011 10:22:52 AM by Hibernate Tools 3.2.1.GA



/**
 * Productorderdetail generated by hbm2java
 */
public class Productorderdetail  implements java.io.Serializable {
     private Integer productOrderDetailId;
     private Productorder productorder;
     private Products products;
     private int productNumber;
     private float totalMoney;     
     private int orderDetailStatusId;
     private Options option;
     private Set delivers = new HashSet(0);
     private Set ordercancels = new HashSet(0);
     private Set payments = new HashSet(0);
     private Set exchangeorders = new HashSet(0);

	public Productorderdetail() {
    }

    public Productorderdetail(Integer productOrderDetailId,
			Productorder productorder, Products products, int productNumber,
			float totalMoney, int orderDetailStatusId, Options option) {
		this.productOrderDetailId = productOrderDetailId;
		this.productorder = productorder;
		this.products = products;
		this.productNumber = productNumber;
		this.totalMoney = totalMoney;
		this.orderDetailStatusId = orderDetailStatusId;
		this.option = option;
	}

	public Productorderdetail(Productorder productorder, Products products, int productNumber, float totalMoney, int orderDetailStatusId) {
       this.productorder = productorder;
       this.products = products;
       this.productNumber = productNumber;
       this.totalMoney = totalMoney;
       this.orderDetailStatusId = orderDetailStatusId; 
    }
    
    public Productorderdetail(Productorder productorder, Products products, int productNumber, float totalMoney, int orderDetailStatusId,Set delivers, Set ordercancels, Set payments, Set exchangeorders) {
        this.productorder = productorder;
        this.products = products;
        this.productNumber = productNumber;
        this.totalMoney = totalMoney;
        this.orderDetailStatusId = orderDetailStatusId; 
        this.ordercancels = ordercancels;
        this.delivers=delivers;
        this.payments = payments;
        this.exchangeorders = exchangeorders;
     }
   
    public Set getExchangeorders() {
		return exchangeorders;
	}

	public void setExchangeorders(Set exchangeorders) {
		this.exchangeorders = exchangeorders;
	}

	public Integer getProductOrderDetailId() {
        return this.productOrderDetailId;
    }
    
    public void setProductOrderDetailId(Integer productOrderDetailId) {
        this.productOrderDetailId = productOrderDetailId;
    }
    public Productorder getProductorder() {
        return this.productorder;
    }
    
    public void setProductorder(Productorder productorder) {
        this.productorder = productorder;
    }
    public Products getProducts() {
        return this.products;
    }
    
    public void setProducts(Products products) {
        this.products = products;
    }
    public int getProductNumber() {
        return this.productNumber;
    }
    
    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
    public float getTotalMoney() {
        return this.totalMoney;
    }
    
    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
    public int getProductId(){
        return this.products.getProductId();
    }
    

	public int getOrderDetailStatusId() {
		return orderDetailStatusId;
	}

	public void setOrderDetailStatusId(int orderDetailStatusId) {
		this.orderDetailStatusId = orderDetailStatusId;
	}

	public Options getOption() {
		return option;
	}

	public void setOption(Options option) {
		this.option = option;
	}

	public Set getOrdercancels() {
		return ordercancels;
	}

	public void setOrdercancels(Set ordercancels) {
		this.ordercancels = ordercancels;
	}

	public Set getDelivers() {
		return delivers;
	}

	public void setDelivers(Set delivers) {
		this.delivers = delivers;
	}
	
	public Set getPayments() {
		return payments;
	}

	public void setPayments(Set payments) {
		this.payments = payments;
	}
	
	
}


