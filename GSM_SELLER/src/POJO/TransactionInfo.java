package POJO;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean (name="trans")
public class TransactionInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2567649483681679999L;

	private int transactionInfoId;
	private String transactionInfo;
	
	public int getTransactionInfoId() {
		return transactionInfoId;
	}
	public void setTransactionInfoId(int transactionInfoId) {
		this.transactionInfoId = transactionInfoId;
	}
	public String getTransactionInfo() {
		return transactionInfo;
	}
	public void setTransactionInfo(String transactionInfo) {
		this.transactionInfo = transactionInfo;
	}
	
	
}
