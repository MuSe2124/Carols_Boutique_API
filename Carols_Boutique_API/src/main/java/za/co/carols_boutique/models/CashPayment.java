package za.co.carols_boutique.models;

import java.io.Serializable;

public class CashPayment implements Payment, Serializable {

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
