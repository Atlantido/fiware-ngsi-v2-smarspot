package test.entities;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Entity;
import com.orange.ngsi2.model.Paginated;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.concurrent.ExecutionException;

public class GetEntities {

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();

        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");
        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");
        Paginated<Entity> entities;
        try {
            entities = client.getEntities(null,null,null,null, 0, 0,true).get();
            for (Entity entity: entities.getItems()) {
                System.out.println(entity.toString());
                System.out.println("\n");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
