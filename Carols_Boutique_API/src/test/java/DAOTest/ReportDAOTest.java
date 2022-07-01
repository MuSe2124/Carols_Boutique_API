/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOTest;

import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.carols_boutique.ReportBE.IDaoreport.DAORep;
import za.co.carols_boutique.ReportBE.IDaoreport.DAORepImp;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.Review;

/**
 *
 * @author Mustafaa Osman
 */
public class ReportDAOTest {
	
	DAORep dao;

	public ReportDAOTest() {
		dao = new DAORepImp();
	}

	@Test
	void testViewTopAchievingStores() {
		String month = "june";
		assertNotNull(dao.viewTopAchievingStores(month));
	}

	@Test
	void testGetCustomerReviews() {
		Integer noOfReviews = 2;
		String month = "june";
		assertNotNull(dao.getCustomerReviews(month, noOfReviews));
	}

	@Test
	void testViewMonthlySales() {
		String storeID = "str1";
		String month = "june";
		assertNotNull(dao.viewMonthlySales(storeID, month));

	}

	@Test
	void testViewTopSellingEmployees() {
		String storeID = "str1";
		String month = "june";
		assertNotNull(dao.viewTopSellingEmployees(storeID, month));

	}

	@Test
	void testViewStoresThatAcievedtTarger() {
		String month = "june";

		assertNotNull(dao.viewStoresThatAchievedTarget(month));

	}

	@Test
	void testViewTopSellingProducts() {
		String month = "june";
		assertNotNull(dao.viewTopSellingProducts(month));

	}

	@Test
	void testViewLeastPerformingStores() {
		assertNotNull(dao.viewLeastPerformingStores("june"));
	}

	@Test
	void testViewProductReport() {
		String productID = "pro1";
		String month = "june";
		assertNotNull(dao.viewProductReport(productID, month));

	}

	@Test
	void testViewDailySalesReport() {
		assertNotNull(dao.viewDailySalesReport("Store"));

	}

	@Test
	void testAddReview() {
		String id = "";
		String comment = "";
		Integer rating = 9;
		Date date = null;
		Review review = new Review(id, comment, rating, date);
		assertNotNull(dao.addReview(review));

	}

	@Test
	void testAddCustomer() {
		String id = "";
		String name = "";
		String phoneNumber = "";
		String email = "";
		Customer customer = new Customer(id, name, phoneNumber, email);
		assertNotNull(dao.addCustomer(customer));
	}
}
