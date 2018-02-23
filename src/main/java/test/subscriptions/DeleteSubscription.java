package test.subscriptions;

import com.orange.ngsi2.client.Ngsi2Client;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class DeleteSubscription {

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");

        try {
            client.deleteSubscription("5a86bb4122894110859c6416").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
