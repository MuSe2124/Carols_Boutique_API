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
import za.co.carols_boutique.ProductBE.ServiceProduct.ProdServiceImp;
import za.co.carols_boutique.models.Product;

/**
 *
 * @author Mustafaa Osman
 */
public class ProductServiceTest {
	
	ProdServiceImp service = new ProdServiceImp();

	public ProductServiceTest() {
	}

	@Test
	void testGetProduct() {
		assertNotNull(service.getProduct("pro1", "m"));
	}

	Product product;

	@Test
	void testAddProduct() {
		product = new Product("TestProd", "Name", "Description", 500F, "M");
		assertNotNull(service.addNewProduct(product, "cat1"));
	}


	@Test
	void testAddProductToInventory() {
		assertNotNull(service.addProductToInventory("str1", "pro1", "emp2", 2, "M"));
	}

	@Test
	void testRemoveProductFromInventory() {
		assertNotNull(service.removeProductFromInventory("str1", "pro1", "emp2", 2, "M"));
	}


	@Test
	void testDeleteProduct() {
		assertNotNull(service.deleteProduct("TestProd", "cat1"));
	}

}
