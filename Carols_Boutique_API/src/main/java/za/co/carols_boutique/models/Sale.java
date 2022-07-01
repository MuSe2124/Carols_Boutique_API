package za.co.carols_boutique.models;

import java.io.Serializable;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import za.co.carols_boutique.models.Employee;

public class Sale implements Serializable {

	private String id;
	private Store store;
	private Employee employee;
	private List<LineItem> lineItems;
	private String customerEmail;
	private LocalDate date;
	private Payment payment;
	private String promo;

	public Sale(String id, Store store, Employee employee, List<LineItem> lineItems, String customerEmail, LocalDate date, Payment payment, String promo) {
		this.id = id;
		this.store = store;
		this.employee = employee;
		this.lineItems = lineItems;
		this.customerEmail = customerEmail;
		this.date = date;
		this.payment = payment;
		this.promo = promo;
	}

	public Sale(LocalDate date, Payment payment) {
		this.date = date;
		this.payment = payment;
	}

	public Sale(List<LineItem> lineItems, LocalDate date, Payment payment) {

		this.lineItems = lineItems;
		this.date = date;
		this.payment = payment;
	}

	public Sale(Store store, Employee employee, List<LineItem> lineItems, String customerID, LocalDate date, String promo) {
		this.store = store;
		this.employee = employee;
		this.lineItems = lineItems;
		this.customerEmail = customerID;
		this.date = date;
		this.promo = promo;

	}

	{
		store = new Store("str2", "pass");
		employee = new Employee("J", "P", "pass", "s", true);
		lineItems = new ArrayList<>();
		customerEmail = "muaadhseedat3@gmail.com";
		DateTimeFormatter ds = DateTimeFormatter.ofPattern("yyyy:mm:dd");

		date = LocalDate.parse(ds.format(now()));
		promo = "PROMOPROMO";
	}

	public Sale(Store store, String id) {
		this.store = store;
		this.id = id;
	}

	public Sale() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerID) {
		this.customerEmail = customerID;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Float calculateTotal() {
		Float flo = null;
		for (LineItem lineItem : lineItems) {
			flo += lineItem.getProduct().getPrice() * lineItem.getAmount();
		}
		return flo;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	@Override
	public String toString() {
		return "Sale{" + "id=" + id + ", storeID=" + store + ", employeeID=" + employee + ", lineItems=" + lineItems + ", customerID=" + customerEmail + ", date=" + date + '}';
	}
}
