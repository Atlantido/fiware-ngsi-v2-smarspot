package test.entities;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Entity;

import com.orange.ngsi2.model.Paginated;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;


public class GetEntity {

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();

        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");
        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");

        Entity entity = null;
        ListenableFuture<Entity> notification;
        try {
            notification = client.getEntity("test",null,null);
            entity = notification.get();
            System.out.println(entity);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
