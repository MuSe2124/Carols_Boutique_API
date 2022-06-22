/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.EmployeeBE.ServiceEmployee;

import java.io.IOException;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DAOEmp;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DaoEmpImp;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.models.Sale;

/**
 *
 * @author muaad
 */
public class EmpServiceImp implements EmpService{
    
    private DAOEmp dao;

    public EmpServiceImp() {
        
        dao = new DaoEmpImp();
    }
    
    

    @Override
    public String login(String employeeID, String password,String storeID) {
        Employee employee = dao.getEmployee(employeeID, password,storeID);
        
        if (employee != null) {
            return "Welcome " + employee.getName() + ", you have logged in successfully.";
        }else{
            return "Failed to log in, employee ID or password incorrect. Please try again.";
        }
    }

    @Override
    public String register(Employee employee) {
        Boolean b = dao.addEmployee(employee);
        
        if (b) {
            return "Employee has been registered successfully.";
        }else{
            return "Failed to register employee, please try again.";
        }
    }

    @Override
    public String promoteToManager(String employeeID) {
        Boolean b = dao.promoteToManager(employeeID);
        if (b) {
            return "You have been promoted to a manager.";
        }else{
            return "Failed to promote employee to manager, please try again.";
        }
    }

    @Override
    public String updateEmployee(Employee employee) {
        Boolean b = dao.updateEmployee(employee);
        
        if (b) {
            return "Employee updated successfully";
        }else{
            return "Failed to update employee, please try again.";
        }
    }

    @Override
    public String deleteEmployee(String employeeID) {
        Boolean b = dao.deleteEmployee(employeeID);
        
        if (b) {
            return "Employee deleted successfully.";
        }else{
            return "Failed to delete employee, please try again.";
        }
    }

}
