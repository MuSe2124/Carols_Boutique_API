/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author Jomar
 */
public class Stock implements Serializable{
    
    private String storeID;
    private String productID;
    private String productName;
    private String employeeID;
    private Integer amount;
    
    private String sizeID;

    public Stock() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public Stock(String storeID, String productID, String employeeID, Integer amount, String sizeID) {
        this.storeID = storeID;
        this.productID = productID;
        this.employeeID = employeeID;
        this.amount = amount;
        this.sizeID = sizeID;
    }

    public Stock(String productID, String productName, Integer amount) {
        this.productID = productID;
        this.productName = productName;
        this.amount = amount;
    }
    
    

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSizeID() {
        return sizeID;
    }

    public void setSizeID(String sizeID) {
        this.sizeID = sizeID;
    }
    
    
}
