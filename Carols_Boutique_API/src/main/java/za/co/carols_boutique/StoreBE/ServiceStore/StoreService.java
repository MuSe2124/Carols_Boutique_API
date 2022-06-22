package za.co.carols_boutique.StoreBE.ServiceStore;

import za.co.carols_boutique.models.Employee;
import za.co.carols_boutique.models.Product;
import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Store;

public interface StoreService {
    
    String loginStore(String storeID, String password);
    String registerStore(Store store);
    String addSale(Sale sale);  
    String deleteStore(String storeID);

}
