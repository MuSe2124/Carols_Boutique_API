package za.co.carols_boutique.Utilities;

import za.co.carols_boutique.models.KeepAside;
import za.co.carols_boutique.models.LineItem;

public interface KeepAsideInt {

	boolean sendReminder24h(KeepAside keepAside);

	boolean sendReminder36h(KeepAside keepAside);

	boolean removeItem(LineItem lineItem);

	boolean addItem(LineItem lineItem);
}
