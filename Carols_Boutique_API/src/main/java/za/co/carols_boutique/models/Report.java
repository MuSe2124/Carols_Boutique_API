/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import za.co.carols_boutique.ReportBE.ServiceReport.RepService;
import za.co.carols_boutique.ReportBE.ServiceReport.RepServiceImp;

/**
 *
 * @author muaad
 */
public class Report implements Serializable{
    
    private RepService service;

    public Report() {
        service = new RepServiceImp();
		reviews = new ArrayList<Review>();
		storeSales = new ArrayList<>();
		empSales = new ArrayList<>();
		prodStores = new ArrayList<>();
		sales = new ArrayList<>();
		saleReports = new ArrayList<>();
    }

    private List<Review>reviews;

	public void setSales(List<Sale> sales) {
		this.sales = sales;
	}
    private List<StoreSale>storeSales;
    private List<EmpSale>empSales;
    private List<ProdStore>prodStores;
    private List<Sale>sales;
    private ProductReport productReport;
    private List<SaleReport> saleReports;

    public List<Sale> getSales() {
        return sales;
    }

    public ProductReport getProductReport() {
        return productReport;
    }

    public void setProductReport(ProductReport productReport) {
        this.productReport = productReport;
    }


    public void setStoresSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<StoreSale> getStoreSales() {
        return storeSales;
    }

    public void setStoreSales(List<StoreSale> storeSales) {
        this.storeSales = storeSales;
    }

    public List<EmpSale> getEmpSales() {
        return empSales;
    }

    public void setEmpSales(List<EmpSale> empSales) {
        this.empSales = empSales;
    }

    public List<ProdStore> getProdStores() {
        return prodStores;
    }

    public void setProdStores(List<ProdStore> prodStores) {
        this.prodStores = prodStores;
    }

    public RepService getService() {
        return service;
    }

    public void setService(RepService service) {
        this.service = service;
    }

    public List<SaleReport> getSaleReports() {
        return saleReports;
    }

    public void setSaleReports(List<SaleReport> saleReports) {
        this.saleReports = saleReports;
    }

	@Override
	public String toString() {
		String s = "Report{" + "\nservice=" + service + "\nreviews=" + reviews.size() + "\nstoreSales=" + storeSales.size() + "\nempSales=" + empSales.size() + "\nprodStores=" + prodStores.size() + "\nsales=" + sales.size()  + "\nsaleReports=" + saleReports.size() + "\n";
		if(productReport!=null){
			s +=  "\nproductReport= "+productReport.toString();
		}
		return s;
	}
    
    
    

}
