/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author muaad
 */
public class StoreSales implements Serializable{
    
    private String storeName;
    private List<Sale>storeSales;
    private Float target;

    public StoreSales(String storeName, List<Sale> storeSales, Float target) {
        this.storeName = storeName;
        this.storeSales = storeSales;
        this.target = target;
    }

    public StoreSales(String storeName, List<Sale> storeSales) {
        this.storeName = storeName;
        this.storeSales = storeSales;
    }

    public StoreSales(String storeName, Float target) {
        this.storeName = storeName;
        this.target = target;
    }
    

    public StoreSales() {
    }
    
    

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Sale> getStoreSales() {
        return storeSales;
    }

    public void setStoreSales(List<Sale> storeSales) {
        this.storeSales = storeSales;
    }

    public Float getTarget() {
        return target;
    }

    public void setTarget(Float target) {
        this.target = target;
    }
    
    
    
}
