
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author Mustafaa Osman
 */
public class CashPayment implements Payment, Serializable{
	
	private int payment;

	public CashPayment(int payment) {
		this.payment = payment;
	}

	@Override
	public boolean verify(int price) {
		if (payment < price) {
			return false;
		} else {
			return true;
		}
	}
}