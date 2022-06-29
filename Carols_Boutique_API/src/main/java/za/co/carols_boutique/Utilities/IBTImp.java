package za.co.carols_boutique.Utilities;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.carols_boutique.Utilities.IDGenerator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.Utilities.Phone;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Store;

public class IBTImp implements IBTInt {

	private LineItem lineItem;
	private Customer customer;
	private Store store;

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int rowsAffected;

	public IBTImp(LineItem lineItem, Customer customer, Store store) {
		this.lineItem = lineItem;
		this.customer = customer;
		this.store = store;

		try {//com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String URL = "jdbc:mysql://localhost:3306/carolsboutique";
		try {
			con = (Connection) DriverManager.getConnection(URL, "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean createIBT() {

		Product prod = lineItem.getProduct();

		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into ibt(id, lineItem, customer) values(?,?,?)");
				ps.setString(1,IDGenerator.generateID("IBT"));
				ps.setString(2, lineItem.getId());
				ps.setString(3, customer.getId());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}

	@Override
	public boolean sendMessageToOtherStore() {
		return false;
	}

	@Override
	public boolean sendCustomerMessage() {
		Phone phone = new Phone(lineItem, customer.getPhoneNumber(), store);
		return phone != null;
	}

	//insert into keepasidearchive(id, storeID, date, customeremail, lineitem, time) select id, storeID, date, customeremail, lineitem, time from keepaside where keepaside.id = ?
	public boolean removeIBT(String ibtId) {
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into ibtArchive(id, lineItem, customer) select id, lineItem, customr from ibt where ibt.id = ?");
				ps.setString(1, ibtId);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}
	
	public void message(String phoneNumber, String producID, Integer amount, Store store) {
		String head = "<smsreq>";
		String dateTime = "<datetime>2022/05/20,10:10:00< / datetime >";
		String user = " <user>GROUP1</user >";
		String pass = "<pass>group1</pass>";
		String number = "<msisdn>" + phoneNumber + "</msisdn >";
		String message = "<message>" + "Your order of " + lineItem.getAmount() + lineItem.getProduct().getName() + " is ready for pickup from " + store.getName() + "\nSincerely Carols Boutique</message >";
		String foot = "</smsreq>";

		String stuff = head + dateTime + user + pass + number + message + foot;

		String url = "http://196.41.180.157:8080/sms/sms_request";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Response response = webTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(stuff));
	}
}
