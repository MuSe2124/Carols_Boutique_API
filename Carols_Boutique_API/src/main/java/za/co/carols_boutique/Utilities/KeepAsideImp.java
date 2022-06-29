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
import za.co.carols_boutique.models.KeepAside;

public class KeepAsideImp extends Thread {

	private Time time;
	private KeepAside keepAside;

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int rowsAffected;

	public KeepAsideImp(KeepAside keepAside) {

		time = Time.valueOf(LocalTime.MIN);
		this.keepAside = keepAside;
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
		this.start();
	}

	@Override
	public void run() {
		createKeepAside(keepAside.getProductID(), keepAside.getAmount());
		System.out.println("Start");
		try {
//			KeepAsideImp.sleep(86400000); //24 hours in milliseconds
			KeepAsideImp.sleep(3000); //24 hours in milliseconds
			System.out.println("Checkpoint 1");
		} catch (InterruptedException ex) {
			Logger.getLogger(KeepAsideImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (getKeepAside(keepAside.getId()) != null) {
			sendReminder36h(keepAside);
		}
		try {
//			KeepAsideImp.sleep(43200000); //12 hours in milliseconds
			KeepAsideImp.sleep(3000); //12 hours in milliseconds
			System.out.println("Checkpoint 2");
		} catch (InterruptedException ex) {
			Logger.getLogger(KeepAsideImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (getKeepAside(keepAside.getId()) != null) {
			removeItem(keepAside.getId());
			System.out.println("Checkpoint 3");
		}
		System.out.println("Its been a minute");
	}

	public boolean sendReminder24h(KeepAside keepAside) {
//		new Email("send24hReminder", keepAside.getCustomerEmail(), keepAside.getLineItem());
		return true;
	}

	public boolean sendReminder36h(KeepAside keepAside) {
//		new Email("send24hReminder", keepAside.getCustomerEmail(), keepAside.getLineItem());
		return true;

	}

	public boolean removeItem(String keepAsideID) {
//		new Email("send48hReminder", keepAside.getCustomerEmail(), keepAside.getLineItem());
		keepAside.setId(IDGenerator.generateID("KAA"));
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into keepasidearchive(id, storeID, date, customerEmail , product, amount, time) select id, storeID, date, customerEmail , product, amount, time from keepaside where keepaside.id = ?");
				ps.setString(1, keepAsideID);
				rowsAffected = ps.executeUpdate();
				ps = con.prepareStatement("delete from keepAside where id = ?");
				ps.setString(1, keepAsideID);
				rowsAffected += ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 2;
	}

	public boolean createKeepAside(String productID, Integer amount) {//Change Email
//		new Email("keepAsideCreated", keepAside.getCustomerEmail(), keepAside.getLineItem());
		rowsAffected = 0;
		keepAside.setId(IDGenerator.generateID("KA"));
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into keepaside(id, storeID, date, customerEmail , product, amount, time) values(?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, keepAside.getId());
				ps.setString(2, keepAside.getStoreID());
				ps.setDate(3, (Date) keepAside.getDate());
				ps.setString(4, keepAside.getCustomerEmail());
				ps.setString(5, keepAside.getProductID());
				ps.setInt(6, keepAside.getAmount());
				ps.setTime(7, keepAside.getTime());
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
					keepAsides = new KeepAside(
							rs.getString("id"),
							rs.getString("StoreID"),
							rs.getDate("Date"),
							rs.getString("customerEmail"),
							rs.getString("product"),
							rs.getInt("amount"),
							rs.getTime("time")
					);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return keepAsides;
	}
}
