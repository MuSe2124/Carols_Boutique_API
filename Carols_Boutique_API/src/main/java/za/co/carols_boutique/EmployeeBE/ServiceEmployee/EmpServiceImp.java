package za.co.carols_boutique.EmployeeBE.ServiceEmployee;

import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DAOEmp;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DaoEmpImp;
import za.co.carols_boutique.models.Employee;

public class EmpServiceImp implements EmpService {

	private DAOEmp dao;

	public EmpServiceImp() {

		dao = new DaoEmpImp();
	}

	@Override
	public Employee login(String employeeID, String password, String storeID) {
		return dao.getEmployee(employeeID, password, storeID);

		
	}

	@Override
	public String register(Employee employee) {
		return dao.addEmployee(employee);
	}

	@Override
	public String promoteToManager(String employeeID) {
		Boolean b = dao.promoteToManager(employeeID);
		if (b) {
			return "You have been promoted to a manager.";
		} else {
			return "Failed to promote employee to manager, please try again.";
		}
	}

	@Override
	public String updateEmployee(Employee employee) {
		Boolean b = dao.updateEmployee(employee);

		if (b) {
			return "Employee updated successfully";
		} else {
			return "Failed to update employee, please try again.";
		}
	}

	@Override
	public String deleteEmployee(String employeeID) {
		Boolean b = dao.deleteEmployee(employeeID);

		if (b) {
			return "Employee deleted successfully.";
		} else {
			return "Failed to delete employee, please try again.";
		}
	}

}
