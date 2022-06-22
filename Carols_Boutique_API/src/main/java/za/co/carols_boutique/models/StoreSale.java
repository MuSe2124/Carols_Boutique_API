package za.co.carols_boutique.models;

import java.io.Serializable;

public class StoreSale implements Serializable{
    
    private String storeName;
    private Float saleTotal;
    private Float target;

    public StoreSale(String storeID, Float saleTotal) {
        this.storeName = storeID;
        this.saleTotal = saleTotal;
    }

    public StoreSale(String storeID, Float saleTotal, Float target) {
        this.storeName = storeID;
        this.saleTotal = saleTotal;
        this.target = target;
    }

    public Float getTarget() {
        return target;
    }

    public void setTarget(Float target) {
        this.target = target;
    }

   
    public String getStoreID() {
        return storeName;
    }

    public void setStoreID(String storeID) {
        this.storeName = storeID;
    }

    public Float getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Float saleTotal) {
        this.saleTotal = saleTotal;
    }
    
}
