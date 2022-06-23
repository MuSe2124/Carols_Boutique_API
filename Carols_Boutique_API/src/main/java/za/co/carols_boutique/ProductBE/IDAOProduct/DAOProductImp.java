package za.co.carols_boutique.ProductBE.IDAOProduct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import za.co.carols_boutique.models.Product;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.co.carols_boutique.models.ProdStore;
import za.co.carols_boutique.models.Stock;

public class DAOProductImp implements DAOProduct {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private int rowsAffected;

//String id, String name, String description, Float price
    public DAOProductImp() {
//        CarolsYAML c = new CarolsYAML();
        try {//com.mysql.cj.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //String URL = "jdbc:mysql://localhost:3306/carolsboutique";       
        try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/carolsboutique", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private Boolean addTransaction(String storeID, String productID, String employeeID, Integer noBefore, Integer noAdded, Integer total, Date currentDate) {
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into StockTransaction(id, storeID, ProductID, employeeID, NoBefore, NoAdded, Total, date) values(?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, "a");
				ps.setString(2, storeID);
				ps.setString(3, productID);
				ps.setString(4, employeeID);
				ps.setInt(5, noBefore);
				ps.setInt(6, noAdded);
				ps.setInt(7, total);
				ps.setDate(8, (java.sql.Date) currentDate);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;
	}

	private Boolean addCatToProd(String categoryID, String productID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into product_category(category,ProductIDs,id) values(?,?,?)");
				ps.setString(1, categoryID);
				ps.setString(2, productID);
				ps.setString(3, "gcvb ");
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;

	}

	private Boolean RemoveCatFromProd(String categoryID, String productID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Delete from Product_Category where category = ? and ProductIDs =?");
				ps.setString(1, categoryID);
				ps.setString(2, productID);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowsAffected == 1;

	}

	@Override
	public Product getProduct(String productID, String size) {
		Product product = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("Select id,name,description,price from Product where id = ?");
				ps.setString(1, productID);
				rs = ps.executeQuery();
				while (rs.next()) {
					product = new Product(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), size);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return product;
	}

	@Override
	public Boolean addProductToInventory(String storeID, String productID, String employeeID, Integer amount, String sizeID) {
		//String id, String storeID, String productID, Integer amount,Integer Size
		rowsAffected = 0;
		int rows = 0;
		int current = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Select amount, product.name from store_product inner join product on store_product.productid = product.id where storeID =? and productID =?");
				ps.setString(1, storeID);
				ps.setString(2, productID);
				rs = ps.executeQuery();
				if (rs.next()) {
					current = rs.getInt("amount");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Date currentDate = new Date(System.currentTimeMillis());
			Integer total = amount + current;
			if (addTransaction(storeID, productID, employeeID, current, amount, total, currentDate)) {
				try {
					ps = con.prepareStatement("update store_product set amount = ? where productID = ? and store_product.storeID = ? and size = ?");
					ps.setInt(1, total);
					ps.setString(2, productID);
					ps.setString(3, storeID);
					ps.setString(4, sizeID);

					rows = ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rowsAffected + rows == 2;
	}

	@Override
	public Boolean addNewProduct(Product product, String catID) {
		rowsAffected = 0;
		if (addCatToProd(catID, product.getId())) {
			if (con != null) {
				try {
					ps = con.prepareStatement("insert into Product(ID,Name,Description,Price) values(?,?,?,?)");
					ps.setString(1, product.getId());
					ps.setString(2, product.getName());
					ps.setString(3, product.getDescription());
					ps.setFloat(4, product.getPrice());
					//ps.setInt(5, product);
					rowsAffected = ps.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return rowsAffected == 1;

	}

	@Override
	public Boolean removeProductFromInventory(String storeID, String productID, String employeeID, Integer amount, String SizeID) {
		ProdStore prodstore = null;

		if (con != null) {
			try {
				ps = con.prepareStatement("select storeID,productID, amount, product.name inner join product on product_store.productid = product.id from store_product where storeID =? and productID =?");
				ps.setString(1, storeID);
				ps.setString(2, productID);
				rs = ps.executeQuery();
				while (rs.next()) {
					prodstore = new ProdStore(rs.getString("storeID"), rs.getString("productID"), rs.getString("product.name"), rs.getInt("amount"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		rowsAffected = 0;
		Integer total = prodstore.getAmount() - amount;
		Date currentdate = new Date(System.currentTimeMillis());
		if (addTransaction(storeID, productID, employeeID, prodstore.getAmount(), amount, total, currentdate)) {
			if (con != null) {
				try {
					ps = con.prepareStatement("update Store_Product set amount=amount-? where storeID=? and productID =? and size =?");

					ps.setInt(1, amount);
					ps.setString(2, storeID);
					ps.setString(3, productID);
					ps.setString(4, SizeID);
					rowsAffected = ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return rowsAffected == 1;

	}

	@Override
	public Boolean deleteProduct(String productID, String catid) {
		rowsAffected = 0;
		if (RemoveCatFromProd(catid, productID)) {
			if (con != null) {
				try {
					ps = con.prepareStatement("Delete from Product where id =?");
					ps.setString(1, productID);
					rowsAffected = ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return rowsAffected == 1;

	}

	@Override
	public Boolean addCatagory(String catID, String name, String description) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("insert into Category(id,name,description) values(?,?,?)");
				ps.setString(1, catID);
				ps.setString(2, catID);
				ps.setString(3, description);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (rowsAffected != 1) {

			return false;
		} else {
			return true;
		}

	}

	@Override
	public Boolean deleteCategory(String CategoryID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Delete from Category where id =?");
				ps.setString(1, CategoryID);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rowsAffected != 1) {
			return false;
		} else {
			return true;
		}

	}

	//Insert
	@Override
	public Boolean CreateProductInInventory(String id, String storeId, String ProductID, Integer amount, Integer Size) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Insert into store_product(id,storeid,productID,amount,size) values(?,?,?,?,?)");
				ps.setString(1, id);
				ps.setString(2, storeId);
				ps.setString(3, ProductID);
				ps.setInt(4, amount);
				ps.setInt(5, Size);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rowsAffected != 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Boolean deleteProductInInventory(String StoreId, String ProductID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Delete from store_product where storeID =? and ProductID=?");
				ps.setString(1, StoreId);
				ps.setString(2, ProductID);
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rowsAffected != 1) {
			return false;
		} else {

			return true;
		}
	}

	@Override
	public ArrayList<Product> lowOnStock(String storeID) {
		ArrayList<Product> prods = new ArrayList<Product>();
		if (con != null) {
			try {
				ps = con.prepareStatement("select product.id,product.name, from product inner join product.id on store_product.productID where storeID = ? and amount < 5");
				ps.setString(1, storeID);
				rs = ps.executeQuery();
				while (rs.next()) {
					Product p = new Product(rs.getString("id"),
							rs.getString("name")
					);
					prods.add(p);
				}

			} catch (SQLException ex) {
				Logger.getLogger(DAOProductImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return prods;
	}

	@Override
	public String getStoreManagerEmail(String string) {
		String email = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("select email from manageremail where storeID = ?");
				if (rs.next()) {
					email = rs.getString("email");
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOProductImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return email;
	}

	@Override
	public ArrayList<Stock> getLowStock(String storeID) {
		ArrayList<Stock> prods = new ArrayList();
		if (con != null) {
			try {
				ps = con.prepareStatement("select product.id,product.name,store_product.amount from store_product inner join product on product.id = store_product.product.id where amount < 5");
				rs = ps.executeQuery();
				while (rs.next()) {
					Stock stock = new Stock(rs.getString("product.id"),
							rs.getString("product.name"),
							rs.getInt("store_product.amount")
					);
				}
			} catch (SQLException ex) {
				Logger.getLogger(DAOProductImp.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return prods;
	}

}
