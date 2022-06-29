package za.co.carols_boutique.models;

import java.io.Serializable;
import za.co.carols_boutique.models.Sale;

public class Exchange implements Serializable {

	Sale sale;
	LineItem preLineItem;
	LineItem postLineItem;

	public Exchange() {
	}

	public Exchange(Sale sale, LineItem preLineItem, LineItem postLineItem) {
		this.sale = sale;
		this.preLineItem = preLineItem;
		this.postLineItem = postLineItem;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public LineItem getPreLineItem() {
		return preLineItem;
	}

	public void setPreLineItem(LineItem preLineItem) {
		this.preLineItem = preLineItem;
	}

	public LineItem getPostLineItem() {
		return postLineItem;
	}

	public void setPostLineItem(LineItem postLineItem) {
		this.postLineItem = postLineItem;
	}
}
