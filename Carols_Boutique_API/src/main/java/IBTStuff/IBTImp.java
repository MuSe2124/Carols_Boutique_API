
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IBTStuff;

/**
 *
 * @author Mustafaa Osman
 */
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
				//con.setAutoCommit(false);
				ps = con.prepareStatement("insert into ibt(id, lineItem, customer) values(?,?,?)");
//				ps.setString(1,);
				ps.setString(2, lineItem.getID());
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
//		Phone phone = new Phone();
//		return phone != null;
		Phone phone = new Phone(lineItem, customer.getPhoneNumber(), store);
		return phone != null;
	}

	//insert into keepasidearchive(id, storeID, date, customeremail, lineitem, time) select id, storeID, date, customeremail, lineitem, time from keepaside where keepaside.id = ?
	public boolean removeKeepAside() {
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into ibtArchive(id, lineItem, customer) select id, lineItem, customr from ibt where ibt.id = ?");
//				ps.setString(1, );
				ps.setString(2, lineItem.getID());
				ps.setString(3, customer.getId());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}
}