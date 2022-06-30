
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class TestPhone {

	public static void main(String[] args) {
		String head = "<smsreq>";
		String dateTime = "<datetime>2022/06/30,11:54:00</datetime>";
		String user = " <user>GROUP1</user>";
		String pass = "<pass>group1</pass>";
		String number = "<msisdn>0609111848</msisdn>";
		String message = "<message>Sincerely Carols Boutique3</message>";
		String foot = "</smsreq>";

		String stuff = head + dateTime + user + pass + number + message + foot;

		String url = "http://196.41.180.157:8080/sms/sms_request";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Response response = webTarget.request(MediaType.APPLICATION_XML).post(Entity.xml(stuff));
	}
}
