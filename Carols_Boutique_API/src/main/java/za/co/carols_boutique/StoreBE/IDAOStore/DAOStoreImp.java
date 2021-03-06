package za.co.carols_boutique.StoreBE.IDAOStore;

import za.co.carols_boutique.Utilities.IDGenerator;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique.ProductBE.IDAOProduct.DAOProductImp;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.models.Exchange;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Stock;
import za.co.carols_boutique.models.Store;

public class DAOStoreImp implements DAOStore {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int rowsAffected;

	public DAOStoreImp() {

		try {//com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver");
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

	//String id, String name, String location, String password
	@Override
	public Boolean addStore(Store store) {
		store.setId(IDGenerator.generateID("str"));
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into Store(id, name, location, password, target) values(?,?,?,?,?)");
				ps.setString(1, store.getId());
				ps.setString(2, store.getName());
				ps.setString(3, store.getLocation());
				ps.setString(4, store.getPassword());
				ps.setFloat(5, store.getTarget());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}

	@Override
	public Store getStore(String storeID, String password) {
		Store store = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("Select ID, name, location, password, target from Store where password= ? and ID = ?");
				ps.setString(1, password);
				ps.setString(2, storeID);
				rs = ps.executeQuery();
				while (rs.next()) {
					store = new Store(rs.getString("ID"), rs.getString("name"), rs.getString("location"), rs.getString("password"), rs.getFloat("target"));
				}

			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		ArrayList<Stock> lowStock = new DAOProductImp().getLowStock(storeID);
		if (lowStock.size() > 0) {
			System.out.println("Sending email");
			new Email("lowStockReminder", getManagerEmail(storeID), lowStock);
		}
		return store;
	}

	private String getManagerEmail(String storeID) {
		String email = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("Select ManagerEmail from manager where storeID = ?");
				ps.setString(1, storeID);
				rs = ps.executeQuery();
				if (rs.next()) {
					email = rs.getString("ManagerEmail");
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return email;
	}

	public static void main(String[] args) {
//		System.out.println(new DAOStoreImp().getStore("str1", "pass").toString());
//		System.out.println(new DAOProductImp().getLowStock("str1").size());
//		new Email("test", "mustafaaosman339@gmail.com");

	}

	private boolean insertLineItems(Sale sale) {
		Integer discount = getDiscount(sale.getPromo());
		if (con != null) {
			for (LineItem li : sale.getLineItems()) {
				try {
					ps = con.prepareStatement("insert into lineitem(id, sale, product, amount, total, size) values(?,?,?,?,?,?)");
					ps.setString(1, IDGenerator.generateID("LI"));
					ps.setString(2, sale.getId());
					ps.setString(3, li.getId());
					ps.setInt(4, li.getAmount());
					ps.setFloat(5, li.getTotal() - (li.getTotal() / discount));
					ps.setString(6, li.getSize());
					rowsAffected += ps.executeUpdate();
				} catch (SQLException ex) {
					Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return rowsAffected == sale.getLineItems().size();
	}

	public Integer getDiscount(String promo) {
		Integer discount = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("select discount from promo where code = ?");
				ps.setString(1, promo);
				rs = ps.executeQuery();
				if (rs.next()) {
					discount = rs.getInt("discount");
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return discount;
	}

	@Override
	public Boolean addSale(Sale sale) {
		insertLineItems(sale);
		if (con != null) {
			try {
				if (sale.getPromo() != null) {
					ps = con.prepareStatement("insert into Sale(id, storeID, employeeID, customerEmail, date, promo) values(?,?,?,?,?,?)");
					ps.setString(6, sale.getPromo());

				} else {
					ps = con.prepareStatement("insert into Sale(id, storeID, employeeID, customerEmail, date) values(?,?,?,?,?)");
				}
				ps.setString(1, sale.getId());
				ps.setString(2, sale.getStore().getId());
				ps.setString(3, sale.getEmployee().getId());
				ps.setString(4, sale.getCustomerEmail());
				ps.setString(5, sale.getDate().toString());

				rowsAffected = ps.executeUpdate();

				new Email("sendReceipt", sale.getCustomerEmail(), sale);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}

	@Override
	public Boolean addReturn(Sale sale) {
		Integer rows = 0;
		if (con != null) {
			try {
				for (LineItem li : sale.getLineItems()) {
					ps = con.prepareStatement("insert into returns(id, lineItemId, Sale, Product, amount, total, size) select id, Id, Sale, Product, amount, total, size from lineItem where lineItem.id = ?");
					ps.setString(1, li.getId());
					rowsAffected += ps.executeUpdate();
					ps = con.prepareStatement("Delete from lineitem where id = ?");
					ps.setString(1, li.getId());
					rows += ps.executeUpdate();
					new Email("sendRefund", sale.getCustomerEmail(), sale);
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return rowsAffected + rows == sale.getLineItems().size() * 2;
	}

	@Override
	public Boolean addExchange(Exchange exchange) {

		if (con != null) {
			try {
				ps = con.prepareStatement("insert into exchange (id, saleID, preLineItem, PostLineItem) values (?, ?, ?, ?)");
				ps.setString(1, IDGenerator.generateID("exc"));
				ps.setString(2, exchange.getSale().getId());
				ps.setString(3, exchange.getPreLineItem().getId());
				ps.setString(4, exchange.getPostLineItem().getId());
				rowsAffected = ps.executeUpdate();

				ps = con.prepareStatement("insert into returns(id, lineItemId, Sale, Product, amount, total, size) select id, Id, Sale, Product, amount, total, size from lineItem where lineItem.id = ?");
				ps.setString(1, exchange.getPreLineItem().getId());
				rowsAffected += ps.executeUpdate();

				ps = con.prepareStatement("Delete from lineitem where id = ?");
				ps.setString(1, exchange.getPreLineItem().getId());
				rowsAffected += ps.executeUpdate();

				ps = con.prepareStatement("insert into lineitem(id, sale, product, amount, total, size) values(?, ?, ?, ?, ?, ?)");
				ps.setString(1, exchange.getPostLineItem().getId());
				ps.setString(2, exchange.getSale().getId());
				ps.setString(3, exchange.getPostLineItem().getProduct().getId());
				ps.setInt(4, exchange.getPostLineItem().getAmount());
				ps.setFloat(5, exchange.getPostLineItem().getTotal());
				ps.setString(6, exchange.getPostLineItem().getSize());
				rowsAffected += ps.executeUpdate();

				new Email("sendAmendedReceipt", exchange.getSale().getCustomerEmail(), exchange.getSale(), exchange.getPreLineItem(), exchange.getPostLineItem());

			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
		return rowsAffected == 4;
	}

	@Override
	public Boolean deleteStore(String storeID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("update Store set active = ? where id = ?");
				ps.setBoolean(1, false);
				ps.setString(2, storeID);
				rowsAffected = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;

	}

	private Integer getStoresTotal(List<String> sales, String storeID) {
		Integer total = 0;
		for (String sale : sales) {
			if (con != null) {
				try {
					ps = con.prepareStatement("select total from lineitem where sale = ?");
					ps.setString(1, sale);
					rs = ps.executeQuery();
					while (rs.next()) {
						total += rs.getInt("total");
					}
				} catch (SQLException ex) {
					Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}

		return total;
	}

	private List<String> getStoreSales(String storeID, String month) {
		List<String> sales = new ArrayList<>();
		if (con != null) {
			try {
				ps = con.prepareStatement("Select id from sale where storeid = ? and monthname(date) = ?");
				ps.setString(1, storeID);
				ps.setString(2, month);
				rs = ps.executeQuery();
				while (rs.next()) {
					sales.add(rs.getString("id"));
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return sales;
	}

	@Override
	public Boolean updateTotal(String storeID, String month) {

		//String id, String storeID, String employeeID, String lineItemID, String customerID, Date date
		rowsAffected = 0;
		Integer total = getStoresTotal(getStoreSales(storeID, month), storeID);
		if (con != null) {
			try {
				ps = con.prepareStatement("update store set total = ? where id = ?");
				ps.setInt(1, total);
				ps.setString(2, storeID);
				rowsAffected = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;

	}

	@Override
	public Sale getSale(String saleID) {
		Sale sale = new Sale();
		sale.setLineItems(new ArrayList<LineItem>());
		if (con != null) {
			try {
				ps = con.prepareStatement("select storeID, employeeID, CustomerEmail, date from sale where ID = ?");
				ps.setString(1, saleID);
				rs = ps.executeQuery();
				if (rs.next()) {
					sale.setCustomerEmail(rs.getString("CustomerEmail"));
					sale.setId(saleID);
					ps = con.prepareStatement("select name, location, password, target from store where id = ?");
					ps.setString(1, rs.getString("storeID"));
					ResultSet rs2 = ps.executeQuery();
					if (rs2.next()) {
						Store store = new Store(rs.getString("storeID"),
								rs2.getString("name"),
								rs2.getString("location"),
								rs2.getString("password"),
								rs2.getFloat("target"));
						sale.setStore(store);
					}
					ps = con.prepareStatement("select name,surname,isManager,password,storeID from employee where id = ?");
					ps.setString(1, rs.getString("employeeID"));
					ResultSet rs3 = ps.executeQuery();
					if (rs.next()) {
						Employee employee = new Employee(
								rs.getString("employeeID"),
								rs3.getString("name"),
								rs3.getString("surname"),
								rs3.getString("password"),
								rs3.getString("storeID"),
								rs3.getBoolean("isManager")
						);
						sale.setEmployee(employee);
					}
					ps = con.prepareStatement("select id, product, amount, total, size, sale from lineitem where sale = ?");
					ps.setString(1, saleID);
					ResultSet rs4 = ps.executeQuery();
					while (rs4.next()) {
						ps = con.prepareStatement("select id, name, description, price from product where id = ?");
						ps.setString(1, rs4.getString("product"));
						ResultSet rs5 = ps.executeQuery();
						Product product = null;
						if (rs5.next()) {
							product = new Product(
									rs5.getString("id"),
									rs5.getString("name"),
									rs5.getString("description"),
									rs5.getFloat("price"),
									rs4.getString("size")
							);
						}
						LineItem li = new LineItem(
								rs4.getString("id"),
								rs4.getString("sale"),
								product,
								rs4.getInt("amount"),
								rs4.getString("size")
						);
						sale.getLineItems().add(li);
					}
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return sale;
	}

	@Override
	public ArrayList<String> getCustomers() {
		ArrayList<String> emails = new ArrayList<>();
		if (con != null) {
			try {
				ps = con.prepareStatement("select emailAdress from customer");
				rs = ps.executeQuery();
				while (rs.next()) {
					emails.add(rs.getString("emailAdress"));
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOStoreImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return emails;
	}

	public boolean sendPromotionEmail(String prooCode) {
		new Email("sendPromotions", getCustomers(), prooCode);
		return rowsAffected == getCustomers().size();
	}
}
