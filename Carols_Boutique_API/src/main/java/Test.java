
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
import za.co.carols_boutique.models.Store;
import za.co.carols_boutique.properties.CarolsProperties;

public class Test {

//	public EmpServiceImp emp;
//	public RepServiceImp rep;
	public ProdServiceImp prod;
//	public StoreServiceImp store;

	public Test() {
//		emp = new EmpServiceImp();
//		rep = new RepServiceImp();
		prod = new ProdServiceImp();
//		store = new StoreServiceImp();
	}

	public static void main(String[] args) throws FileNotFoundException {
		Test test = new Test();
		Report report;

		Store store = new Store("TestID", "Test Name", "Locaion", "pass", 2432f);

//		Work
//		System.out.println(test.store.registerStore(store));
//		No fucking clue. What the check low stock doing here. Talk to Sedat
//		System.out.println(test.store.loginStore("TestID", "pass"));
//
//		Works
//		Employee employee = new Employee("emp1", "nae", "surname", "password", "storeID", false);
//		ArrayList<LineItem> lineItems = new ArrayList<>();
//		lineItems.add(new LineItem("Id", "Sale", new Product("pro1"), 4));
//		Sale sale = new Sale("Sale", store, employee, lineItems, "DWS", new java.sql.Date(2022, 06, 22), new CashPayment(6573));
//		System.out.println(test.store.addSale(sale));
//
//		Works.
//		System.out.println(test.store.deleteStore("TestID"));
//
//		works
//		System.out.println(test.prod.getProduct("pro1", "10").toString());
//
//		works
//		System.out.println(test.prod.addProductToInventory("str1", "pro6", "emp5", 5, "m"));
//		
//		Product product = new Product("TestProd", "NameTest", "Description", 563f, "10");
//
//		Works
//		System.out.println(test.prod.addNewProduct(product, "cat1"));
//
//
//		Not work. Need fix
		System.out.println(test.prod.removeProductFromInventory("str1", "pro6", "enp5", 1, "10"));
//
//		Works
//		System.out.println(test.prod.deleteProduct("TestProd", "cat1"));
//
//      Login Works
//		System.out.println(test.emp.login("ep5", "pass", "str5"));
//
//		Works
//		Employee employee = new Employee("TestID1", "Test name", "Test surname", "pass", "s", false);
//		System.out.println(test.emp.register(employee));
//
//		works
//		System.out.println(test.emp.promoteToManager("TestID1"));
//
//		Works
//      Employee employee = new Employee("TestID1", "Test Change name", "Test Change surname", "pass", "s", false);
//		System.out.println(test.emp.updateEmployee(employee));
//
//		Works
//		System.out.println(test.emp.deleteEmployee("TestID1"));
//
//		WORKS
//		System.out.println("\n\nTesting top achieving stores");
//		report = test.rep.viewTopAchievingStores("june");
//		System.out.println(report.toString());
//
//		WORKS
//		System.out.println("\n\nTesting get customer reviews");
//		report = test.rep.getCustomerReviews("june",2);
//		System.out.println(report.toString());
//		
//		WORKS
		System.out.println("\n\nTesting monthly sales");
		report = test.rep.viewMonthlySales("str6","june");
		System.out.println(report.toString());
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
