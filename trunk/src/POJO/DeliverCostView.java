package POJO;

import java.util.Date;

public class DeliverCostView {
private int productID;
private String productName;
private String manufacturerName;
private int deliverID;
private String deliverAddress;
private String receiver;

private int deliverCostID;
private String deliverFeeType;
private float conditionFree;
private float deliverFee;
private float deliverFeeExtra;
private Date handleDate;
private Date handleUser;

public DeliverCostView() {
	// TODO Auto-generated constructor stub
}

public DeliverCostView(int productID, String productName,
		String manufacturerName, int deliverID, String deliverAddress,
		String receiver, int deliverCostID, String deliverFeeType,
		float conditionFree, float deliverFee, float deliverFeeExtra,
		Date handleDate, Date handleUser) {
	super();
	this.productID = productID;
	this.productName = productName;
	this.manufacturerName = manufacturerName;
	this.deliverID = deliverID;
	this.deliverAddress = deliverAddress;
	this.receiver = receiver;
	this.deliverCostID = deliverCostID;
	this.deliverFeeType = deliverFeeType;
	this.conditionFree = conditionFree;
	this.deliverFee = deliverFee;
	this.deliverFeeExtra = deliverFeeExtra;
	this.handleDate = handleDate;
	this.handleUser = handleUser;
}

public int getProductID() {
	return productID;
}

public void setProductID(int productID) {
	this.productID = productID;
}

public String getProductName() {
	return productName;
}

public void setProductName(String productName) {
	this.productName = productName;
}

public String getManufacturerName() {
	return manufacturerName;
}

public void setManufacturerName(String manufacturerName) {
	this.manufacturerName = manufacturerName;
}

public int getDeliverID() {
	return deliverID;
}

public void setDeliverID(int deliverID) {
	this.deliverID = deliverID;
}

public String getDeliverAddress() {
	return deliverAddress;
}

public void setDeliverAddress(String deliverAddress) {
	this.deliverAddress = deliverAddress;
}

public String getReceiver() {
	return receiver;
}

public void setReceiver(String receiver) {
	this.receiver = receiver;
}

public int getDeliverCostID() {
	return deliverCostID;
}

public void setDeliverCostID(int deliverCostID) {
	this.deliverCostID = deliverCostID;
}

public String getDeliverFeeType() {
	return deliverFeeType;
}

public void setDeliverFeeType(String deliverFeeType) {
	this.deliverFeeType = deliverFeeType;
}

public float getConditionFree() {
	return conditionFree;
}

public void setConditionFree(float conditionFree) {
	this.conditionFree = conditionFree;
}

public float getDeliverFee() {
	return deliverFee;
}

public void setDeliverFee(float deliverFee) {
	this.deliverFee = deliverFee;
}

public float getDeliverFeeExtra() {
	return deliverFeeExtra;
}

public void setDeliverFeeExtra(float deliverFeeExtra) {
	this.deliverFeeExtra = deliverFeeExtra;
}

public Date getHandleDate() {
	return handleDate;
}

public void setHandleDate(Date handleDate) {
	this.handleDate = handleDate;
}

public Date getHandleUser() {
	return handleUser;
}

public void setHandleUser(Date handleUser) {
	this.handleUser = handleUser;
}

}
