package za.co.carols_boutique.Utilities;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.co.carols_boutique.models.LineItem;
import za.co.carols_boutique.models.Store;

public class Phone extends Thread {

	private LineItem lineItem;
	private String phoneNumber;
	private Store store;

	public Phone(LineItem lineItem, String phoneNumber, Store store) {
		this.lineItem = lineItem;
		this.phoneNumber = phoneNumber;
		this.store = store;
		this.start();
	}

	@Override
	public void run() {
		String head = "<smsreq>";
		String dateTime = "<datetime>2022/05/20,10:10:00< / datetime >";
		String user = " <user>GROUP1</user >";
		String pass = "<pass>group1</pass>";
		String number = "<msisdn>" + phoneNumber + "</msisdn >";
		String message = "<message>" + "Your order of " + lineItem.getAmounnt() + lineItem.getProduct().getName() + " is ready for pickup from " + store.getName() + "\nSincerely Carols Boutique</message >";
		String foot = "</smsreq>";

		String stuff = head + dateTime + user + pass + number + message + foot;

		String url = "http://196.41.180.157:8080/sms/sms_request";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Response response = webTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(stuff));
	}
}
