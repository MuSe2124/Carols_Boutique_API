/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author muaad
 */

public class EmpSale implements Serializable{

    private String employeeID;
    private Float saleTotal;
    private String storeID;

    public EmpSale(String employeeID, Float saleTotal, String storeID) {
        this.employeeID = employeeID;
        this.saleTotal = saleTotal;
        this.storeID = storeID;
    }

    public EmpSale(Float saleTotal, String storeID) {
        this.saleTotal = saleTotal;
        this.storeID = storeID;
    }


    public EmpSale() {
    }

    public EmpSale(String employeeID, Float saleTotal) {
        this.employeeID = employeeID;
        this.saleTotal = saleTotal;
    }

    public String getEmployee() {
        return employeeID;
    }

    public void setEmployee(String employeeID) {
        this.employeeID = employeeID;
    }

    public Float getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Float saleTotal) {
        this.saleTotal = saleTotal;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }
       
}
