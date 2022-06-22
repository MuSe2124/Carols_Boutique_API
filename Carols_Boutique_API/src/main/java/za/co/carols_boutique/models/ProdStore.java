package za.co.carols_boutique.models;

import java.io.Serializable;

public class ProdStore implements Serializable{
   
    private String storeID;
    private String productID;
	private String productName;
    private Integer amount;

	public ProdStore(String storeID, String productID, String productName, Integer amount) {
		this.storeID = storeID;
		this.productID = productID;
		this.productName = productName;
		this.amount = amount;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "ProdStore{" + "storeID=" + storeID + ", productID=" + productID + ", productName=" + productName + ", amount=" + amount + '}';
	}

}
