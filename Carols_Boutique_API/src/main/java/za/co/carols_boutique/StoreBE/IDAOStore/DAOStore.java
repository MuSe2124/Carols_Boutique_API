package za.co.carols_boutique.StoreBE.IDAOStore;

import java.util.ArrayList;
import za.co.carols_boutique.models.Exchange;
import za.co.carols_boutique.models.Sale;
import za.co.carols_boutique.models.Store;

public interface DAOStore {

	Boolean addStore(Store store);
	//Boolean addEmployeeToStore(Employee employee);

	Boolean addSale(Sale sale);

	Sale getSale(String saleID);

	Store getStore(String storeID, String password);

	Boolean deleteStore(String storeID);
	//Boolean deleteEmployeeFromStore(String employeeID);

	Boolean updateTotal(String storeID, String month);

	public Boolean addExchange(Exchange exchange);

	public Boolean addReturn(Sale sale);

	public ArrayList<String> getCustomers();
}
