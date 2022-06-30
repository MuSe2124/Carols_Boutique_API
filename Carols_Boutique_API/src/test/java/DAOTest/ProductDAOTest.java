/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOTest;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import za.co.carols_boutique.ProductBE.IDAOProduct.DAOProductImp;
import za.co.carols_boutique.models.Product;

/**
 *
 * @author Mustafaa Osman
 */
public class ProductDAOTest {

	DAOProductImp dao;

	public ProductDAOTest() {
		this.dao = new DAOProductImp();
	}

	@Test
	void testGetProduct() {
		assertNull(dao.getProduct("TestProduct", "M"));
	}

	Product product;

	@Test
	void testAddProduct() {
		product = new Product("TestProduct", "TestName", "Description", 500F, "M");
		assertTrue(dao.addNewProduct(product, "cat1"));
	}

	@Test
	void testAddProductToInventory() { //Add store id and employeeID
		assertTrue(dao.addProductToInventory("str1", "TestProduct", "emp1", 5, "m"));
	}

	@Test
	void testRemoveProductFromInventory() { //Add store id and employeeID
		assertTrue(dao.removeProductFromInventory("str1", "TestProduct", "emp1", 5, "m"));
	}

	@Test
	void testDeleteProduct() {
		assertTrue(dao.deleteProduct("TestProduct", "cat1"));
	}
}
