package za.co.carols_boutique.ProductBE.IDAOProduct;


import java.util.ArrayList;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Stock;

public interface DAOProduct {
    
    Product getProduct(String productID, String size);
    //this is an update for adding in an amount, has addTransaction
    Boolean addProductToInventory(String storeID, String productID, String employeeID, Integer amount, String SizeID);
    Boolean addNewProduct(Product product,String catid);
    //String id, String storeID, String productID, Integer amount,Integer Size
    //this is an insert
    Boolean CreateProductInInventory(String id,String storeId,String ProductID,Integer amount,Integer Size);
    //this is a delete
    Boolean deleteProductInInventory(String StoreId,String ProductID);
    //this is an update for subtracting an amount, has addTransaction
    Boolean removeProductFromInventory(String storeID, String productID, String employeeID, Integer amount,String SizeID);
    
    Boolean deleteProduct(String productID,String catid);
    Boolean addCatagory(String catID,String name,String description); 
    Boolean deleteCategory(String CategoryID);
    ArrayList<Product> lowOnStock(String storeID);
    String getStoreManagerEmail(String storeID);
    ArrayList<Stock> getLowStock(String storeID);
    
        
    
}
