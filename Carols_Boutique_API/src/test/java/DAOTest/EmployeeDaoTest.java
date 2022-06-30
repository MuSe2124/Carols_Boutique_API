package DAOTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import za.co.carols_boutique.EmployeeBE.IDAOEmployee.DaoEmpImp;
import za.co.carols_boutique.StoreBE.IDAOStore.DAOStoreImp;
import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.models.Store;


public class EmployeeDaoTest {
	
	DaoEmpImp emp;
	Store store;

	public EmployeeDaoTest() {
		this.emp = new DaoEmpImp();
		this.store = new Store("Store", "StoreName", "Location", 50000F);
	}

	@Test
	void testAddEmployee() {
		sImp = new DAOStoreImp();
		sImp.addStore(store);
		assertTrue(emp.addEmployee(new Employee("empoyeeTestID1", "Test Name ", "Test surname", "EmployeeTestPass", "Store", false)).equals("You have registered, Your employee ID is: empoyeeTestID1" ));
	}

	Employee employee = null;

	DAOStoreImp sImp;

	@Test
	void testGetEmployee() {
		employee = emp.getEmployee("empoyeeTestID1", "EmployeeTestPass", "Store");
		assertNotNull(employee);
	}

	@Test
	void testUpdateEmployee() {
		employee.setName("TestName2");
		assertTrue(emp.updateEmployee(employee));
	}

	@Test
	void checkNotMannager() {
		assertFalse(employee.getIsManager());
	}

	@Test
	void testPromoteToManager() {
		assertTrue(emp.promoteToManager("empoyeeTestID1"));
	}

	@Test
	void checkIsMannager() {
		assertTrue(employee.getIsManager());
	}

	@Test
	void testDeleteEmployee() {
		assertTrue(emp.deleteEmployee("empoyeeTestID1"));
		sImp.deleteStore(store.getId());
	}
}
