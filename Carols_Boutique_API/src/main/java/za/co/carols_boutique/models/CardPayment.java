package za.co.carols_boutique.models;

import java.io.Serializable;

public class CardPayment implements Payment, Serializable {

	private String CardNum;
	private String CardType;

	public CardPayment(String CardNum, String CardType) {
		this.CardNum = CardNum;
		this.CardType = CardType;
	}

	public String getCardNum() {
		return CardNum;
	}

	public void setCardNum(String CardNum) {
		this.CardNum = CardNum;
	}

	public String getCardType() {
		return CardType;
	}

	public void setCardType(String CardType) {
		this.CardType = CardType;
	}

	@Override
	public boolean verify(int price) {
		if (price < 10000) {
			return true;
		} else {
			int ran = (int) (Math.random() * 10 + 1);
			if (ran > 7) {
				return false;
			}
		}
		return true;
	}
}
