/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeepAsideStuff;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DaoEmpImp;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.models.KeepAside;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.properties.CarolsProperties;

/**
 * @author Mustafaa Osman
 */
public class KeepAsideImp extends Thread implements KeepAsideInt {

	Time time;
	KeepAside keepAside;

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int rowsAffected;
	//String id, String name, String surname, Boolean isManager

	public KeepAsideImp(KeepAside keepAside) {

		time = Time.valueOf(LocalTime.MIN);

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
	public void run() {
		addItem(keepAside.getLineItem());
		try {
			KeepAsideImp.sleep(86400000); //24 hours in milliseconds
		} catch (InterruptedException ex) {
			Logger.getLogger(KeepAsideImp.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (getKeepAside(keepAside.getId()) != null) {
			sendReminder36h(keepAside);
		}
		try {
			KeepAsideImp.sleep(43200000); //12 hours in milliseconds
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
//Action recipient lineItemn

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
				ps.setString(5, keepAside.getLineItem().getID());
				ps.setTime(6, keepAside.getTime());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;

	}

	@Override  //Switch name
	public boolean addItem(LineItem lineItem) {
		new Email("", keepAside.getCustomerEmail(), keepAside.getLineItem());
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
				ps.setString(5, keepAside.getLineItem().getID());
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
