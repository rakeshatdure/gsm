package POJO;

import java.io.Serializable;

public class market implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6333630978406604203L;

	private int marketId;
	private String nameMarket;
	
	public int getMarketId() {
		return marketId;
	}
	public void setMarketId(int marketId) {
		this.marketId = marketId;
	}
	public String getNameMarket() {
		return nameMarket;
	}
	public void setNameMarket(String nameMarket) {
		this.nameMarket = nameMarket;
	}
	
	
}
