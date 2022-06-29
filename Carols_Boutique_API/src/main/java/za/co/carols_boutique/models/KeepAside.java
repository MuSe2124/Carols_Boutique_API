package za.co.carols_boutique.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class KeepAside implements Serializable {

	private String id;
	private String storeID;
	private Date date;
	private String customerEmail;
	private String productID;
	private Integer amount;
	private Time time; //Format of the time "11:18:11" "hh:mm:ss"

	public KeepAside(String id, String storeID, Date date, String customerEmail, String productID, Integer amount, Time time) {
		this.id = id;
		this.storeID = storeID;
		this.date = date;
		this.customerEmail = customerEmail;
		this.productID = productID;
		this.amount = amount;
		this.time = time;
	}

	public KeepAside(String id, String storeID, Date date, String customerEmail, Time time) {
		this.id = id;
		this.storeID = storeID;
		this.date = date;
		this.customerEmail = customerEmail;
		this.time = time;
	}

	public KeepAside(String storeID, Date date, String customerEmail, String productID, Integer amount) {
		this.storeID = storeID;
		this.date = date;
		this.customerEmail = customerEmail;
		this.productID = productID;
		this.amount = amount;
	}

	public KeepAside() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "KeepAside{" + "id=" + id + ", storeID=" + storeID + ", date=" + date + ", customerEmail=" + customerEmail + ", productID=" + productID + ", amount=" + amount + ", time=" + time + '}';
	}
}
