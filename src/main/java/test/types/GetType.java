package test.types;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.AttributeType;
import com.orange.ngsi2.model.EntityType;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class GetType {

    public static void main(String[] args) {

        AsyncRestTemplate template = new AsyncRestTemplate();
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");

        EntityType type;

        try {
           type = client.getEntityType("SmartSpot").get();
            System.out.println("Type: SmartSpot");
            Map<String,AttributeType> attributes = type.getAttrs();
            Set<String> keys = attributes.keySet();
            for (String key: keys) {
                System.out.println(key + ": " + attributes.get(key).getType());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
