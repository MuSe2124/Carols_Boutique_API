package za.co.carols_boutique.ReportBE.ServiceReport;

import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.Report;
import za.co.carols_boutique.models.Review;

public interface RepService {

	Report viewTopAchievingStores(String month);

	Report getCustomerReviews(String month, Integer amount);

	Report viewMonthlySales(String storeID, String month);

	Report viewTopSellingEmployees(String storeID, String month);

	Report viewStoresThatAchievedTarget(String month);

	Report viewTopSellingProducts(String month);

	Report viewLeastPerformingStores(String month);

	Report viewProductReport(String productID, String month);

	Report viewDailySalesReport(String storeID);

	String addReview(Review review);

	String addCustomer(Customer customer);

}
