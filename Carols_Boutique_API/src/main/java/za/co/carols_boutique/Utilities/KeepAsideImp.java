package za.co.carols_boutique.Utilities;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.models.KeepAside;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.models.Product;

public class KeepAsideImp extends Thread implements KeepAsideInt {
	public static void main(String[] args) {
		new KeepAsideImp(new KeepAside(IDGenerator.generateID("KA"), "str1", new java.util.Date(System.currentTimeMillis()), "mustafaaOsman339@gmail.com", new LineItem("li3", IDGenerator.generateID("sa"), new Product("pro6", "Produvt", "Description", 500F, "M"), 1, "M"), new Time(System.currentTimeMillis())));
	}

	private Time time;
	private KeepAside keepAside;

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int rowsAffected;

	public KeepAsideImp(KeepAside keepAside) {

		time = Time.valueOf(LocalTime.MIN);

		try {
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
		start();
	}

	@Override
	public void run() {
		addItem(keepAside.getLineItem());
		System.out.println("Beofre 1sr");
		try {
//			KeepAsideImp.sleep(86400000); //24 hours in milliseconds
			KeepAsideImp.sleep(3000); //24 hours in milliseconds
			System.out.println("After 1st");
		} catch (InterruptedException ex) {
			Logger.getLogger(KeepAsideImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (getKeepAside(keepAside.getId()) != null) {
			sendReminder36h(keepAside);
		}
		try {
//			KeepAsideImp.sleep(43200000); //12 hours in milliseconds
			KeepAsideImp.sleep(3000); //12 hours in milliseconds
			System.out.println("After 2nd");
		} catch (InterruptedException ex) {
			Logger.getLogger(KeepAsideImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (getKeepAside(keepAside.getId()) != null) {
			removeItem(keepAside.getLineItem());
		}
	}

	@Override
	public boolean sendReminder24h(KeepAside keepAside) {
		new Email("send24hReminder", keepAside.getCustomerEmail(), keepAside.getLineItem());
		return true;
	}

	@Override
	public boolean sendReminder36h(KeepAside keepAside) {
		new Email("send24hReminder", keepAside.getCustomerEmail(), keepAside.getLineItem());
		return true;

	}

	@Override
	public boolean removeItem(LineItem lineItem) {
		Product prod = lineItem.getProduct();
		new Email("send48hReminder", keepAside.getCustomerEmail(), keepAside.getLineItem());

		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into keepasidearchive(id, storeID, date, customeremail, lineitem, time) select id, storeID, date, customeremail, lineitem, time from keepaside where keepaside.id = ?");
				ps.setString(1, keepAside.getId());
				ps.setString(2, keepAside.getStoreID());
				ps.setDate(3, (Date) keepAside.getDate());
				ps.setString(4, keepAside.getCustomerEmail());
				ps.setString(5, keepAside.getLineItem().getId());
				ps.setTime(6, new Time(System.currentTimeMillis()));
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}

	@Override  //Switch name
	public boolean addItem(LineItem lineItem) {
		new Email("keepAsideCreated", keepAside.getCustomerEmail(), keepAside.getLineItem());
		Product prod = lineItem.getProduct();
		rowsAffected = 0;
		if (con != null) {
			try {

				//con.setAutoCommit(false);
				ps = con.prepareStatement("insert into keepaside(id, storeID, date, customerEmail , product, time) values(?,?,?,?,?,?)");
				ps.setString(1, keepAside.getId());
				ps.setString(2, keepAside.getStoreID());
				ps.setDate(3, (Date) keepAside.getDate());
				ps.setString(4, keepAside.getCustomerEmail());
				ps.setString(5, keepAside.getLineItem().getId());
				ps.setTime(6, keepAside.getTime());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}

	public KeepAside getKeepAside(String id) {
		KeepAside keepAsides = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("select * from keepaside where id = ?");
				ps.setString(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					keepAsides = new KeepAside(rs.getString("id"),
							rs.getString("StoreId"),
							rs.getDate("date"),
							rs.getString("customerEmail"),
							rs.getTime("Time"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return keepAsides;
	}
}