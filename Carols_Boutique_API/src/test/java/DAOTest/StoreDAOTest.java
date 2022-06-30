/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.carols_boutique.StoreBE.IDAOStore.DAOStore;
import za.co.carols_boutique.StoreBE.IDAOStore.DAOStoreImp;
import za.co.carols_boutique.models.Store;

/**
 *
 * @author Mustafaa Osman
 */
public class StoreDAOTest {
	
	DAOStore dao;
	Store store;

	public StoreDAOTest() {
		this.dao = new DAOStoreImp();
		this.store = new Store("TestStoreID", "TestName", "Location", "TestStorePassword", 5000F);
	}

	@Test
	void testNotGetStore() {
		assertNull(dao.getStore("TestStoreID", "TestStorePassword"));
	}

	@Test
	void testAddStore() {
		assertTrue(dao.addStore(store));
	}

	@Test
	void testGetStore() {
		assertNotNull(dao.getStore("TestStoreID", "TestStorePassword"));
	}

	@Test
	void testDeleteStore() {
		assertTrue(dao.deleteStore("TestStoreID"));
	}
}
