package za.co.carols_boutique.EmployeeBE.IDAOEmployee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import za.co.carols_boutique.Utilities.IDGenerator;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.yaml.CarolsYAML;

public class DaoEmpImp implements DAOEmp {

	private Connection con;
	private ResultSet rs;
	private PreparedStatement ps;
	private int rowsAffected;
	private String URL;

	public DaoEmpImp() {
//		CarolsYAML c = new CarolsYAML();
		try {//com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//String URL = "jdbc:mysql://localhost:3306/carolsboutique";       
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/carolsboutique","root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String addEmployee(Employee employee) {
		rowsAffected = 0;
		employee.setId(IDGenerator.generateID("emp"));
		if (con != null) {
			try {

				//con.setAutoCommit(false);
				ps = con.prepareStatement("insert into Employee(id,name,surname,isManager,password,StoreID) values(?,?,?,?,?,?)");
				ps.setString(1, employee.getId());
				ps.setString(2, employee.getName());
				ps.setString(3, employee.getSurname());
				ps.setBoolean(4, employee.getIsManager());
				ps.setString(5, employee.getPassword());
				ps.setString(6, employee.getStoreID());
				rowsAffected = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rowsAffected == 1){
		return "You have registered, Your employee ID is: "+employee.getId();
	}
		return "Could not register";
	}

	@Override
	public Employee getEmployee(String employeeID, String password, String StoreID) {
		
		Employee emp = null;
		if (con != null) {
			try {
				ps = con.prepareStatement("select id,name,surname,isManager,password from Employee where id = ? and password =? and StoreID=?");

				ps.setString(1, employeeID);
				ps.setString(2, password);
				ps.setString(3, StoreID);
				rs = ps.executeQuery();
				while (rs.next()) {
				emp = new Employee(rs.getString("id"), rs.getString("name"), rs.getString("surname"), rs.getString("password"), rs.getBoolean("isManager"));
				}
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
		return emp;
	}

	@Override
	public Boolean promoteToManager(String employeeID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Update Employee set isManager = true where id = ?");
				ps.setString(1, employeeID);
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
	public Boolean updateEmployee(Employee employee) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("Update Employee set id = ?, name = ?, surname = ?, isManager = ?, password = ?,StoreID = ? where id = ?");
				ps.setString(1, employee.getId());
				ps.setString(2, employee.getName());
				ps.setString(3, employee.getSurname());
				ps.setBoolean(4, employee.getIsManager());
				ps.setString(5, employee.getPassword());
				ps.setString(6, employee.getStoreID());
				ps.setString(7, employee.getId());

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
	public Boolean deleteEmployee(String employeeID) {
		rowsAffected = 0;
		if (con != null) {
			try {
				ps = con.prepareStatement("delete from Employee where id = ?");
				ps.setString(1, employeeID);
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

}
