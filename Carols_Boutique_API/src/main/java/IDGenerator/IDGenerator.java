/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IDGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mustafaa Osman
 */
public class IDGenerator {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int rowsAffected;

	public IDGenerator() {
		//CarolsYAML c = new CarolsYAML();
		try {//com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//String URL = "jdbc:mysql://localhost:3306/carolsboutique";       
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/carolsboutique", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String generateID(String name) {
		int x = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("select count(*) from idgenerator");
				rs = ps.executeQuery();
				if (rs.next()) {
					x = rs.getInt("count(*)");
				}
				ps = con.prepareStatement("insert into idgenerator (id, name, completedId) values(?, ?, ?)");
				ps.setString(1, String.valueOf(x + 101));
				ps.setString(2, name);
				ps.setString(3, String.valueOf(x + 101) + " " + name);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException ex) {
				Logger.getLogger(IDGenerator.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		String id = String.valueOf(x + 101) + " " + name;
		return id;
	}
}
