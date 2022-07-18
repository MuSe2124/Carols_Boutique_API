package za.co.carols_boutique.models;

import java.io.Serializable;

public class IBT implements Serializable {

	private String id;
	private String productID;
	private Integer amount;
	private String customerPhone;
	private String size;
	private String storeID;

	public IBT(String id, String productID, Integer amount, String customerPhoneString, String size, String storeID) {
		this.id = id;
		this.productID = productID;
		this.amount = amount;
		this.customerPhone = customerPhoneString;
		this.size = size;
		this.storeID = storeID;
	}

	public IBT(String productID, Integer amount, String customerPhone, String size, String storeID) {
		this.productID = productID;
		this.amount = amount;
		this.customerPhone = customerPhone;
		this.size = size;
		this.storeID = storeID;
	}

	public IBT() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerEmail(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	@Override
	public String toString() {
		return "IBT{" + "id=" + id + ", productID=" + productID + ", amount=" + amount + ", customerPhone=" + customerPhone + ", size=" + size + ", storeID=" + storeID + '}';
	}
	
}
