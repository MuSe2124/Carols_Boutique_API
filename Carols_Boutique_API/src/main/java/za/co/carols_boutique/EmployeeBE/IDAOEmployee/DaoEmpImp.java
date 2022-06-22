package za.co.carols_boutique.EmployeeBE.IDAOEmployee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.properties.CarolsProperties;
import za.co.carols_boutique.yaml.CarolsYAML;

/**
 *
 * @author HP
 */
public class DaoEmpImp implements DAOEmp {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement ps;
    private int rowsAffected;
    private String URL;
    //String id, String name, String surname, Boolean isManager

    public DaoEmpImp() {
        CarolsYAML c = new CarolsYAML();
        try {//com.mysql.cj.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //String URL = "jdbc:mysql://localhost:3306/carolsboutique";       
        try {
            con = (Connection) DriverManager.getConnection(c.getUrl(), c.getUsername(), c.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean addEmployee(Employee employee) {
        rowsAffected = 0;
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
        return rowsAffected == 1;
    }

    @Override
    public Employee getEmployee(String employeeID, String password, String StoreID) {

        if (con != null) {
            try {
                ps = con.prepareStatement("select id,name,surname,isManager,password from Employee where id = ? and password =? and StoreID=?");

                ps.setString(1, employeeID);
                ps.setString(2, password);
                ps.setString(3, StoreID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    return new Employee(rs.getString("id"), rs.getString("name"), rs.getString("surname"), rs.getString("password"), rs.getBoolean("isManager"));
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return null;
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
                ps = con.prepareStatement("Update Employee set id =?,name =?,surname =?,isManager=?,password =?,StoreID=? where id =?");
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
