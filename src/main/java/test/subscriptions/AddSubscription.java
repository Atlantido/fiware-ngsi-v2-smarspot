package test.subscriptions;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.*;
import org.springframework.web.client.AsyncRestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class AddSubscription {

    public static void main(String[] args) {
        String idSubscription = null;

        AsyncRestTemplate template = new AsyncRestTemplate();
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");
        client.setHttpHeaderJSON();

        List<String> attributes = new LinkedList<String>();
        attributes.add("temperature0");

        try {
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setId(Optional.of("test"));
            subjectEntity.setType(Optional.of("SmartSpot"));

            Condition condition = new Condition();
            condition.setAttributes(Collections.singletonList("temperature"));
            condition.setExpression("temp","temperature0>40");

            Notification notification = new Notification(attributes,new URL("http://192.168.1.250:8666/notify"));;
            notification.setThrottling(Optional.of(new Long(5)));
            notification.setAttrsFormat(Optional.of(Notification.Format.keyValues));

            SubjectSubscription subjectSubscription = new SubjectSubscription(Collections.singletonList(subjectEntity), condition);

            Subscription subscription = new Subscription();
            subscription.setSubject(subjectSubscription);
            subscription.setNotification(notification);
            subscription.setExpires(Instant.parse("2019-04-05T14:00:00.20Z"));

            idSubscription = client.addSubscription(subscription).get();
            System.out.println("id: " + idSubscription);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
