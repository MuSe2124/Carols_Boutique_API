package za.co.carols_boutique.EmployeeBE.ServiceEmployee;

import za.co.carols_boutique.models.Employee;

public interface EmpService {

	Employee login(String employeeID, String password, String storeID);

	String register(Employee employee);

	String promoteToManager(String employeeID);

	String updateEmployee(Employee employee);

	String deleteEmployee(String employeeID);

}
