package za.co.carols_boutique.models;

import java.io.Serializable;

public class ProdCat implements Serializable {

	private Product product;
	private String catID;
	private String productID;

	public ProdCat(Product product, String catID) {
		this.product = product;
		this.catID = catID;
	}

	public ProdCat() {
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCatID() {
		return catID;
	}

	public void setCatID(String catID) {
		this.catID = catID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

}
