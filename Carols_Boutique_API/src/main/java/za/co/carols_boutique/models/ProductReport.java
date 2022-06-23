package za.co.carols_boutique.models;

import java.io.Serializable;

public class ProductReport implements Serializable {

	private String productID;
	private String employeeID;
	private Integer amountsSold;

	public ProductReport(String productID, String employeeID, Integer amountsSold) {
		this.productID = productID;
		this.employeeID = employeeID;
		this.amountsSold = amountsSold;
	}

	public ProductReport() {
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getAmountsSold() {
		return amountsSold;
	}

	public void setAmountsSold(Integer amountsSold) {
		this.amountsSold = amountsSold;
	}

}
