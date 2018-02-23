package test.subscriptions;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Paginated;
import com.orange.ngsi2.model.Subscription;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class GetSubscriptions {
    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");

        Paginated<Subscription> subscriptions;
        try {
            subscriptions = client.getSubscriptions(0,0,true).get();
            for(Subscription subscription: subscriptions.getItems()) {
                System.out.println("Subscription: " + subscription.getId());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
