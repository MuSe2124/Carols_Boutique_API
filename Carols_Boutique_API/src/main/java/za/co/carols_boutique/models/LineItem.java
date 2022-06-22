/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author muaad
 */
public class LineItem implements Serializable{

	private String id;
	private String saleID;
	private Product product;
	private Integer amount;
        private String size;

	public LineItem(String id, String saleID, Product product, Integer amounnt) {
		this.id = id;
		this.saleID = saleID;
		this.product = product;
		this.amount = amounnt;
	}

	public LineItem(String saleID, Product product, Integer amounnt) {
		this.saleID = saleID;
		this.product = product;
		this.amount = amounnt;
	}

	public LineItem() {
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getSaleID() {
		return saleID;
	}

	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProductID(Product product) {
		this.product = product;
	}

	public Integer getAmounnt() {
		return amount;
	}

	public void setAmounnt(Integer amounnt) {
		this.amount = amounnt;
	}

	public Float getTotal() {
		return product.getPrice() * amount;
	}
       

	@Override
	public String toString() {
		return "LineItem{" + "id=" + id + ", saleID=" + saleID + ", product=" + product.getName() + ", amounnt=" + amount + '}';
	}
    
    
}
