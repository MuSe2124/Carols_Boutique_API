/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class ExchangedItems implements Serializable{
    private String lineItemId;
    private String exchanged;
    private String qty1;
    private Double amount1;
    private String Brought;
    private String qty2;
    private Double amount2;

    public ExchangedItems(String lineItemId, String exchanged, String qty1, Double amount1, String Brought, String qty2, Double amount2) {
        this.lineItemId = lineItemId;
        this.exchanged = exchanged;
        this.qty1 = qty1;
        this.amount1 = amount1;
        this.Brought = Brought;
        this.qty2 = qty2;
        this.amount2 = amount2;
    }

    public String getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(String lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getExchanged() {
        return exchanged;
    }

    public void setExchanged(String exchanged) {
        this.exchanged = exchanged;
    }

    public String getQty1() {
        return qty1;
    }

    public void setQty1(String qty1) {
        this.qty1 = qty1;
    }

    public Double getAmount1() {
        return amount1;
    }

    public void setAmount1(Double amount1) {
        this.amount1 = amount1;
    }

    public String getBrought() {
        return Brought;
    }

    public void setBrought(String Brought) {
        this.Brought = Brought;
    }

    public String getQty2() {
        return qty2;
    }

    public void setQty2(String qty2) {
        this.qty2 = qty2;
    }

    public Double getAmount2() {
        return amount2;
    }

    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }
    
    
    
}
