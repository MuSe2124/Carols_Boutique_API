/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.Utilities;

import java.util.ArrayList;
import za.co.carols_boutique.models.Stock;

/**
 *
 * @author Jomar
 */
public class StockCheck{
    
    Email email;
    String storeManagerEmail;
    ArrayList<Stock>prods;

    public StockCheck(ArrayList<Stock> prods, String storeManagerEmail) {
        this.prods = prods;
        this.storeManagerEmail = storeManagerEmail;
        email = new Email("lowStockReminder",storeManagerEmail, prods);
    }
    
}
