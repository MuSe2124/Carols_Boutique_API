/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.ReportBE.ServiceReport;

import java.io.IOException;
import java.util.Date;
import za.co.carols_boutique.ReportBE.IDaoreport.DAORep;
import za.co.carols_boutique.ReportBE.IDaoreport.DAORepImp;
import za.co.carols_boutique.models.Customer;
import za.co.carols_boutique.models.Report;
import za.co.carols_boutique.models.Review;
import za.co.carols_boutique.models.Store;

/**
 *
 * @author muaad
 */
public class RepServiceImp implements RepService {

    private DAORep dao;

    public RepServiceImp() {
        dao = new DAORepImp();
    }

    @Override
    public Report viewTopAchievingStores(String month) {
        Report report = dao.viewTopAchievingStores(month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report getCustomerReviews(String month, Integer amount) {
        Report report = dao.getCustomerReviews(month, amount);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewMonthlySales(String storeID, String month) {
        Report report = dao.viewMonthlySales(storeID, month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewTopSellingEmployees(String storeID, String month) {
        Report report = dao.viewTopSellingEmployees(storeID, month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewStoresThatAchievedTarget(String month) {
        Report report = dao.viewStoresThatAchievedTarget(month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewTopSellingProducts(String month) {
        Report report = dao.viewTopSellingProducts(month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewLeastPerformingStores(String month) {
        Report report = dao.viewLeastPerformingStores(month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewProductReport(String productID, String month) {
        Report report = dao.viewProductReport(productID, month);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public Report viewDailySalesReport(String storeID) {
        Report report = dao.viewDailySalesReport(storeID);
        if (report != null) {
            return report;
        } else {
            return null;
        }
    }

    @Override
    public String addReview(Review review) {
        Boolean b = dao.addReview(review);

        if (b) {
            return "Review added successfully.";
        } else {
            return "Failed to add review, please try again.";
        }
    }

    @Override
    public String addCustomer(Customer customer) {
        Boolean b = dao.addCustomer(customer);

        if (b) {
            return "Customer added successfully.";
        } else {
            return "Failed to add customer, please try again.";
        }
    }

}
