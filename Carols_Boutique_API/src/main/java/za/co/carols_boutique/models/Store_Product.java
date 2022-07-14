/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

/**
 *
 * @author Mustafaa Osman
 */
public class Store_Product {

	private String storeID;
	private String ProductID;
	private Integer amount;
	private String size;

	public Store_Product(String storeID, String ProductID, Integer amount, String size) {
		this.storeID = storeID;
		this.ProductID = ProductID;
		this.amount = amount;
		this.size = size;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

	public String getProductID() {
		return ProductID;
	}

	public void setProductID(String ProductID) {
		this.ProductID = ProductID;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
