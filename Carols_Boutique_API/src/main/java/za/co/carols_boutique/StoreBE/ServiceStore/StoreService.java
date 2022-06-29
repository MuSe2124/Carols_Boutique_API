package za.co.carols_boutique.StoreBE.ServiceStore;

import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Store;

public interface StoreService {

	Store loginStore(String storeID, String password);

	String registerStore(Store store);

	String addSale(Sale sale);

	String deleteStore(String storeID);
        
        Sale getSale(String saleID);
}
