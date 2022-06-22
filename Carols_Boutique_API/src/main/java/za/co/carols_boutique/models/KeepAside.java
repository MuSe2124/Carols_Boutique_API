/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author muaad
 */
public class KeepAside implements Serializable{

	private String id;
	private String storeID;
	private Date date;
	private String customerEmail;
	private LineItem lineItem;
	private Time time; //Format of the time "11:18:11" "hh:mm:ss"

	public KeepAside(String id, String storeID, Date date, String customerEmail, LineItem lineItem, Time time) {
		this.id = id;
		this.storeID = storeID;
		this.date = date;
		this.customerEmail = customerEmail;
		this.lineItem = lineItem;
		this.time = time;
	}

	public KeepAside(String id, String storeID, Date date, String customerEmail, Time time) {
		this.id = id;
		this.storeID = storeID;
		this.date = date;
		this.customerEmail = customerEmail;
		this.time = time;
	}

	public KeepAside(String storeID, Date date, String customerEmail, LineItem lineItem) {
		this.storeID = storeID;
		this.date = date;
		this.customerEmail = customerEmail;
		this.lineItem = lineItem;
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

	public LineItem getLineItem() {
		return lineItem;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "KeepAside{" + "id=" + id + ", storeID=" + storeID + ", date=" + date + ", customerEmail=" + customerEmail + ", lineItem=" + lineItem + ", time=" + time + '}';
	}
}
