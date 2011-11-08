package POJO;
// Generated Sep 6, 2011 3:12:21 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Payment generated by hbm2java
 */
public class Payment  implements java.io.Serializable {


     private Integer paymentId;
     private int orderDetailId;
     private int paymentMethodId;
     private Date paymentDate;

    public Payment() {
    }

    public Payment(int orderDetailId, int paymentMethodId, Date paymentDate) {
       this.orderDetailId = orderDetailId;
       this.paymentMethodId = paymentMethodId;
       this.paymentDate = paymentDate;
    }
   
    public int getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Integer getPaymentId() {
        return this.paymentId;
    }
    
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }
    public int getOrderDetailId() {
        return this.orderDetailId;
    }
    
    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
   
    public Date getPaymentDate() {
        return this.paymentDate;
    }
    
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }




}

