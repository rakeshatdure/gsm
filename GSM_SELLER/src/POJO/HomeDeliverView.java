package POJO;

import java.util.Date;

public class HomeDeliverView {
private String productID;
private String shippingCompanyName;
private String deliverID;
private String deliverType;
private String finishedDate;
private String shippingCost;

public HomeDeliverView() {
	// TODO Auto-generated constructor stub
}



public HomeDeliverView(String productID, String shippingCompanyName,
		String deliverID, String deliverType, String finishedDate,String shippingCost) {
	this.productID = productID;
	this.shippingCompanyName = shippingCompanyName;
	this.deliverID = deliverID;
	this.deliverType = deliverType;
	this.finishedDate = finishedDate;
	this.shippingCost=shippingCost;
}



public String getProductID() {
	return productID;
}

public void setProductID(String productID) {
	this.productID = productID;
}

public String getShippingCompanyName() {
	return shippingCompanyName;
}

public void setShippingCompanyName(String shippingCompanyName) {
	this.shippingCompanyName = shippingCompanyName;
}

public String getDeliverID() {
	return deliverID;
}

public void setDeliverID(String deliverID) {
	this.deliverID = deliverID;
}

public String getDeliverType() {
	return deliverType;
}

public void setDeliverType(String deliverType) {
	this.deliverType = deliverType;
}

public String getFinishedDate() {
	return finishedDate;
}

public void setFinishedDate(String finishedDate) {
	this.finishedDate = finishedDate;
}



public String getShippingCost() {
	return shippingCost;
}



public void setShippingCost(String shippingCost) {
	this.shippingCost = shippingCost;
}

}
