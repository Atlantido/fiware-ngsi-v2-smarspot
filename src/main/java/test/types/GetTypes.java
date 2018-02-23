package test.types;

import com.orange.ngsi2.client.Ngsi2Client;

import com.orange.ngsi2.model.EntityType;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class GetTypes {

    public static void main(String[] args) {

        AsyncRestTemplate template = new AsyncRestTemplate();
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");

        List<EntityType> types = null;

        try {
          types = client.getEntityTypes(0,0,false).get().getItems();
            for (EntityType type: types) {
                System.out.println("Type: " + type.getType());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
