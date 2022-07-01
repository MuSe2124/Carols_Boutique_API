package ServiceTest;

import java.util.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.co.carols_boutique.ReportBE.ServiceReport.RepService;
import za.co.carols_boutique.ReportBE.ServiceReport.RepServiceImp;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.Review;


public class ReportServiceTest {

	RepService service;

	public ReportServiceTest() {
		service = new RepServiceImp();
	}

	@Test
	void testViewTopAchievingStores() {
		String month = "june";
		assertNotNull(service.viewTopAchievingStores(month));
	}

	@Test
	void testGetCustomerReviews() {
		Integer noOfReviews = 2;
		String month = "june";
		assertNotNull(service.getCustomerReviews(month, noOfReviews));
	}

	@Test
	void testViewMonthlySales() {
		String storeID = "str1";
		String month = "june";
		assertNotNull(service.viewMonthlySales(storeID, month));
	}


	@Test
	void testViewTopSellingEmployees() {
		String storeID = "str1";
		String month = "june";
		assertNotNull(service.viewTopSellingEmployees(storeID, month));
	}


	@Test
	void testViewStoresThatAcievedtTarger() {
		String month = "june";
		assertNotNull(service.viewStoresThatAchievedTarget(month));
	}



	@Test
	void testViewTopSellingProducts() {
		String month = "june";
		assertNotNull(service.viewTopSellingProducts(month));
	}


	@Test
	void testViewLeastPerformingStores() {
		assertNotNull(service.viewLeastPerformingStores("june"));
	}

	@Test
	void testViewProductReport() {
		String productID = "pro1";
		String month = "june";
		assertNotNull(service.viewProductReport(productID, month));
	}


	@Test
	void testViewDailySalesReport() {
		assertNotNull(service.viewDailySalesReport("Store"));
	}


	@Test
	void testAddReview() {
		String id = "REview1";
		String comment = "OKstore";
		Integer rating = 9;
		Date date = null;
		Review review = new Review(id, comment, rating, date);
		assertNotNull(service.addReview(review));
	}

	@Test
	void testAddCustomer() {
		String id = "Cus1";
		String name = "Name";
		String phoneNumber = "0748035093";
		String email = "mustafaaosman339@gmail.com";
		Customer customer = new Customer(id, name, phoneNumber, email);
		assertNotNull(service.addCustomer(customer));
	}
}
