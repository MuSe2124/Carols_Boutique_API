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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.Utilities.Phone;
import za.co.carols_boutique.models.IBT;
import za.co.carols_boutique.models.ProdStore;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Store;
import za.co.carols_boutique.models.Store_Product;

public class IBTImp {

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int rowsAffected;

	public IBTImp() {

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

	private String getProductName(String prodID) {
		String name = "";
		if (con != null) {
			try {
				ps = con.prepareStatement("select name from product where id = ?");
				ps.setString(1, prodID);
				rs = ps.executeQuery();
				if (rs.next()) {
					name = rs.getString("name");
				}
			} catch (SQLException ex) {
				Logger.getLogger(IBTImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return name;
	}

	public ArrayList<Store_Product> getProdStores(String productID) {
		ArrayList<Store_Product> storeProds = new ArrayList<>();
		if (con != null) {
			try {
				ps = con.prepareStatement("select id, storeID, productID, amount, Size from store_product where productID = ?");
				ps.setString(1, productID);
				rs = ps.executeQuery();
				while (rs.next()) {
					storeProds.add(
							new Store_Product(rs.getString("storeID"), 
									rs.getString("productID"),
									rs.getInt("amount"), 
									rs.getString("size"))
					);
				}
			} catch (SQLException ex) {
				Logger.getLogger(IBTImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return storeProds;
	}

	public boolean createIBT(IBT ibt) {
		ibt.setId(IDGenerator.generateID("IBT"));
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into ibt(id, product, amount, customerPhone, size) values(?, ?, ?, ?, ?)");
				ps.setString(1, ibt.getId());
				ps.setString(2, ibt.getProductID());
				ps.setInt(3, ibt.getAmount());
				ps.setString(4, ibt.getCustomerPhone());
				ps.setString(5, ibt.getSize());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		message(ibt);
		return rowsAffected == 1;
	}

	public boolean acceptIBT(String ibtID) {
		return true;
	}

	public boolean removeIBT(String ibtId) {
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into ibtArchive(id, product, amount, customerPhone, size) select id, product, amount, customerPhone, size from ibt where ibt.id = ?");
				ps.setString(1, ibtId);
				rowsAffected = ps.executeUpdate();
				ps = con.prepareStatement("delete from ibt where id = ?");
				ps.setString(1, ibtId);
				rowsAffected += ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 2;
	}

	private String getStoreName(String storeID) {
		String storeName = "";
		if (con != null) {
			try {
				ps = con.prepareStatement("select name from store where id = ?");
				ps.setString(1, storeID);
				rs = ps.executeQuery();
				if (rs.next()) {
					storeName = rs.getString("name");
				}
			} catch (SQLException ex) {
				Logger.getLogger(IBTImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return storeName;
	}

	public void message(IBT ibt) {
		String head = "<smsreq>";
		String dateTime = "<datetime>2022/05/20,10:10:00</datetime>";
		String user = " <user>GROUP1</user>";
		String pass = "<pass>group1</pass>";
		String number = "<msisdn>" + ibt.getCustomerPhone() + "</msisdn>";
		String message = "<message>" + "Your order of " + ibt.getAmount() + getProductName(ibt.getProductID()) + " will be ready for pickup from our " + getStoreName(ibt.getStoreID()) + " branch in 2 hours.\nSincerely Carols Boutique</message>";
		String foot = "</smsreq>";

		String sms = head + dateTime + user + pass + number + message + foot;

		String url = "http://196.41.180.157:8080/sms/sms_request";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Response response = webTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(sms));
	}

	public IBT getIBT(String ibtID) {
		IBT ibt = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("select product, amount, customerPhone, size, store from ibt where id = ?");
				ps.setString(1, ibtID);
				rs = ps.executeQuery();
				if (rs.next()) {
					ibt = new IBT(ibtID, rs.getString("product"), rs.getInt("amount"), rs.getString("customerPhone"), rs.getString("size"), rs.getString("store"));
				}
			} catch (SQLException ex) {
				Logger.getLogger(IBTImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return ibt;
	}
}
