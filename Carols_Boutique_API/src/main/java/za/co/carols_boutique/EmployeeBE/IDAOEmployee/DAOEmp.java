package za.co.carols_boutique.EmployeeBE.IDAOEmployee;

import za.co.carols_boutique.models.Employee;

public interface DAOEmp {

	String addEmployee(Employee employee);

	Employee getEmployee(String employeeID, String password, String StoreID);

	Boolean promoteToManager(String employeeID);

	Boolean updateEmployee(Employee employee);

	Boolean deleteEmployee(String employeeID);
}
