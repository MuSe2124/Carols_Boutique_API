package za.co.carols_boutique.models;

import java.io.Serializable;

public class LineItem implements Serializable {

	private String id;
	private String saleID;
	private Product product;
	private Integer amount;
	private String size;

	public LineItem(String id, String saleID, Product product, Integer amounnt, String size) {
		this.id = id;
		this.saleID = saleID;
		this.product = product;
		this.amount = amounnt;
		this.size = size;
	}

	public LineItem(String saleID, Product product, Integer amounnt) {
		this.saleID = saleID;
		this.product = product;
		this.amount = amounnt;
	}

	public LineItem(Product product, Integer amounnt) {
		this.product = product;
		this.amount = amounnt;
	}

	public LineItem(Product product, Integer amount, String size) {
		this.product = product;
		this.amount = amount;
		this.size = size;
	}

	public LineItem() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public void setProduct(Product product) {
		this.product = product;
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

	public Float getTotal() {
		return product.getPrice() * amount;
	}

	@Override
	public String toString() {
		return "LineItem{" + "id=" + id + ", saleID=" + saleID + ", product=" + product.getName() + ", amounnt=" + amount + '}';
	}
}
