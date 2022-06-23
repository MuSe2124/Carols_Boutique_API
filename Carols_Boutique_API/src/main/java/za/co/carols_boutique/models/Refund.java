package za.co.carols_boutique.models;

import java.io.Serializable;
import java.util.List;

public class Refund implements Serializable {

	private Sale sale;
	private List<LineItem> lineItems;

	public Refund(Sale sale, List<LineItem> lineItems) {
		this.sale = sale;
		this.lineItems = lineItems;
	}

	public Refund() {
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

}
