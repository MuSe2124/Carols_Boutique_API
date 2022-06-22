/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KeepAsideStuff;

import za.co.carols_boutique.models.KeepAside;
import za.co.carols_boutique.models.LineItem;

/**
 *
 * @author Mustafaa Osman
 */
public interface KeepAsideInt {
	
	boolean sendReminder24h(KeepAside keepAside);

	boolean sendReminder36h(KeepAside keepAside);

	boolean removeItem(LineItem lineItem);

	boolean addItem(LineItem lineItem);
}
