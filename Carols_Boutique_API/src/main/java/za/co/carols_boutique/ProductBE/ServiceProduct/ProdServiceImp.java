/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.ProductBE.ServiceProduct;

import java.util.ArrayList;
import za.co.carols_boutique.ProductBE.IDAOProduct.DAOProduct;
import za.co.carols_boutique.ProductBE.IDAOProduct.DAOProductImp;
import za.co.carols_boutique.Utilities.Email;
import za.co.carols_boutique.Utilities.StockCheck;
import za.co.carols_boutique.models.Exchange;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Refund;

/**
 *
 * @author muaad
 */
public class ProdServiceImp implements ProdService {

    private DAOProduct dao;
    private Email email;
    private StockCheck stockCheck;

    public ProdServiceImp() {
        dao = new DAOProductImp();
        email = null;
    }

    @Override
    public Product getProduct(String productID, String size) {
        Product product = dao.getProduct(productID,size);

        if (product != null) {
            return product; 
        } else {
            return null;
        }
    }

    @Override
    public String addProductToInventory(String storeID, String productID, String employeeID, Integer amount, String sizeID) {
        Boolean b = dao.addProductToInventory(storeID, productID, employeeID, amount, storeID);
        if (b) {
            return "Product added successfully";
        } else {
            return "Failed to add product, please try again";
        }
    }

    @Override
    public String addNewProduct(Product product, String catID) {
        Boolean b = dao.addNewProduct(product,catID);

        if (b) {
            return "New product added successfully.";
        } else {
            return "Failed to add new product, please try again.";
        }
    }

    @Override
    public String removeProductFromInventory(String storeID, String productID, String employeeID, Integer amount, String sizeID) {
        Boolean b = dao.removeProductFromInventory(storeID, productID, employeeID, amount, sizeID);

        if (b) {
            return "Product removed successfully";
        } else {
            return "Failed to remove product, please try again.";
        }
    }

    @Override
    public String deleteProduct(String productID, String catID) {
        Boolean b = dao.deleteProduct(productID, catID);

        if (b) {
            return "Product removed successfully";
        } else {
            return "Failed to remove product, please try again.";
        }
    }

    @Override
    public String refund(Refund refund) {
        //email = new Email("refund",refund);
        return "Refund completed";
    }

    @Override
    public String exchange(Exchange exchng) {
        //email = new Email("Exchange,exchange);
        return "Exchange complete";
    }

    @Override
    public void checkLowStock(String storeID) {
        stockCheck = new StockCheck(dao.getLowStock(storeID),dao.getStoreManagerEmail(storeID));
    }
}
