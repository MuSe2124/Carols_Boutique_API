/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique;

import za.co.carols_boutique.ReportBE.IDaoreport.DAORepImp;
import za.co.carols_boutique.ReportBE.ServiceReport.RepServiceImp;

/**
 *
 * @author Jomar
 */
public class Test {
	public static void main(String[] args) {
		
	
	DAORepImp rep = new DAORepImp();
	RepServiceImp s = new RepServiceImp();
	
		System.out.println("view top achieving stores");
		System.out.println(s.viewTopAchievingStores("june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("get customer reviews");
		System.out.println(s.getCustomerReviews("june",2));
		System.out.println("\n\n\n\n");
		
		System.out.println("view montly sales");
		System.out.println(s.viewMonthlySales("str2","june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("view top selling employees");
		System.out.println(s.viewTopSellingEmployees("str2","june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("view stores that achieved target");
		System.out.println(s.viewStoresThatAchievedTarget("june"));
		System.out.println("\n\n\n\n");
	
		System.out.println("view top selling products");
		System.out.println(s.viewTopSellingProducts("june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("view top selling employees");
		System.out.println(s.viewTopSellingEmployees("str2","june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("view least performing stores");
		System.out.println(s.viewLeastPerformingStores("june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("view product report");
		System.out.println(s.viewProductReport("pro1","june"));
		System.out.println("\n\n\n\n");
		
		System.out.println("view daily sales report");
		System.out.println(s.viewDailySalesReport("str2"));
		System.out.println("\n\n\n\n");
	}
}
