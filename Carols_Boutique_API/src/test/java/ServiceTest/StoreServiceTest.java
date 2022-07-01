/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package ServiceTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.carols_boutique.StoreBE.ServiceStore.StoreServiceImp;
import za.co.carols_boutique.models.Store;

/**
 *
 * @author Mustafaa Osman
 */
public class StoreServiceTest {
	
	StoreServiceImp dao;
	Store store;

	public StoreServiceTest() {
		this.dao = new StoreServiceImp();
		this.store = new Store("TestStoreID", "TestName", "Location", "TestStorePassword", 5000F);
	}

	@Test
	void testGetStore() {
		assertNotNull(dao.loginStore("TestStoreID", "TestStorePassword"));
	}

	@Test
	void testGetStoreSuccess() {
		assertTrue(dao.loginStore("TestStoreID", "TestStorePassword").equals(store.getName() + " had been logged in successfully."));
	}

	@Test
	void testAddStore() {
		assertNotNull(dao.registerStore(store));
	}

	@Test
	void testAddStoreSuccess() {
		assertNotNull(dao.registerStore(store));
	}

	@Test
	void testDeleteStore() {
		assertNotNull(dao.deleteStore("TestStoreID"));
	}

	@Test
	void testDeleteStoreSuccess() {
		assertTrue(dao.deleteStore("TestStoreID").equals("Store deleted successfully."));
	}
}
