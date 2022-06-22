
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import za.co.carols_boutique.EmployeeBE.ServiceEmployee.EmpServiceImp;
import za.co.carols_boutique.ProductBE.ServiceProduct.ProdServiceImp;
import za.co.carols_boutique.ReportBE.ServiceReport.RepServiceImp;
import za.co.carols_boutique.StoreBE.ServiceStore.StoreServiceImp;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.models.CardPayment;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.models.Payment;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Report;
import za.co.carols_boutique.models.Review;
import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Stock;
import za.co.carols_boutique.properties.CarolsProperties;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Jomar
 */



public class Test {

//	public EmpServiceImp emp;
	public RepServiceImp rep;
//	public ProdServiceImp prod;
//	public StoreServiceImp store;

	public Test() {
//		emp = new EmpServiceImp();
		rep = new RepServiceImp();
//		prod = new ProdServiceImp();
//		store = new StoreServiceImp();
	}
	
	
    
    public static void main(String[] args) throws FileNotFoundException {
		Test test = new Test();
		Report report;
		
//		WORKS
//		System.out.println("\n\nTesting top achieving stores");
//		report = test.rep.viewTopAchievingStores("june");
//		System.out.println(report.toString());
		
//		WORKS
//		System.out.println("\n\nTesting get customer reviews");
//		report = test.rep.getCustomerReviews("june",2);
//		System.out.println(report.toString());
//		
//		WORKS
//		System.out.println("\n\nTesting monthly sales");
//		report = test.rep.viewMonthlySales("str6","june");
//		System.out.println(report.toString());
//		
//		WORKS
//		System.out.println("\n\nTesting top selling employees");
//		report = test.rep.viewTopSellingEmployees("str6","june");
//		System.out.println(report.toString());
//		
//		WORKS
//		System.out.println("\n\nTesting stores that hit target");
//		report = test.rep.viewStoresThatAchievedTarget("june");
//		System.out.println(report.toString());
//		
//		TODO MUSSY!!!
//		System.out.println("\n\nTesting top selling products");
//		report = test.rep.viewTopSellingProducts("june");
//		System.out.println(report.toString());
//		
//		WORKS
//		System.out.println("\n\nTesting least achieving stores");
//		report = test.rep.viewLeastPerformingStores("june");
//		System.out.println(report.toString());
//		
//		WORKS
//		System.out.println("\n\nTesting product report");
//		report = test.rep.viewProductReport("prod3","june");
//		System.out.println(report.toString());
//		
//		NOT TESTED
//		System.out.println("\n\nTesting daily sales report");
//		report = test.rep.viewDailySalesReport("1");
//		System.out.println(report.toString());
//		
//		NEED TO GENERATE ID
//		System.out.println("\n\nTesting add review");
//		String s = test.rep.addReview(new Review("Great",9));
//		System.out.println(s);
//	
//		NEED TO GENERATE ID
//		System.out.println("\n\nTesting add custoemr");
//		s = test.rep.addCustomer(new Customer("Johannes","0794562816","jomarvn@gmail.com"));
//		System.out.println(s);
    }
}
