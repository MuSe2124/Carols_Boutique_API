/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Jomar
 */
public class SaleReport implements Serializable{
    private String saleID;
    private Float saleTotal;

    public SaleReport(String saleID, Float saleTotal) {
        this.saleID = saleID;
        this.saleTotal = saleTotal;
    }

    public String getSaleID() {
        return saleID;
    }

    public void setSaleID(String saleID) {
        this.saleID = saleID;
    }

    public Float getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Float saleTotal) {
        this.saleTotal = saleTotal;
    }

    
    
}
