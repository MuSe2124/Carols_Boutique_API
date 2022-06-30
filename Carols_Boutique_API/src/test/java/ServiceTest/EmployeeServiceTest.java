/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ServiceTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DAOEmp;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DaoEmpImp;
import za.co.carols_boutique.EmployeeBE.ServiceEmployee.EmpService;
import za.co.carols_boutique.EmployeeBE.ServiceEmployee.EmpServiceImp;
import za.co.carols_boutique.models.Employee;

/**
 *
 * @author Mustafaa Osman
 */
public class EmployeeServiceTest {

	DAOEmp dao;
	EmpService service;
	Employee employee;

	public EmployeeServiceTest() {
		this.service = new EmpServiceImp();
		this.dao = new DaoEmpImp();
	}

	@Test
	void testAddEmployee() {
		employee = new Employee("employeeTest1", "name", "surname", "TestPass", "str1", false);
		assertNotNull(service.register(employee));
	}

	@Test
	void testGetEmployee() {
		Employee response = service.login("empoyeeTest1", "TestPass", "str1");
		employee = dao.getEmployee("empoyeeTest1", "TestPass", "TestStoreID");
		assertNotNull(response);
	}

	@Test
	void testUpdateEmployee() {
		employee = new Employee("name2", "TestName2", "pass", "str1", false);
		assertNotNull(service.updateEmployee(employee));
	}

	@Test
	void checkNotMannager() {
		assertFalse(employee.getIsManager());
	}

	@Test
	void testPromoteToManager() {
		assertNotNull(service.promoteToManager("empoyeeTest1"));
	}

	@Test
	void checkIsMannager() {
		assertTrue(employee.getIsManager());
	}

	@Test
	void testDeleteEmployee() {
		assertNotNull(service.deleteEmployee("empoyeeTest1"));
	}

	@Test
	void testAddEmployeeSuccess() {
		employee = new Employee("empoyeeTest1", "TestName", "TestSurname", "TestPass", false);
		assertTrue(service.register(employee).equals("Welcome " + employee.getName() + ", you have logged in successfully."));
	}
}
