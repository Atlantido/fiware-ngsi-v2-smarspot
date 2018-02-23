package test.entities;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Attribute;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class GetAttribute {

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        Attribute attribute = null;

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");

        try {
            String attributeId = "address";
            String value = null;
            attribute = client.getAttribute("prueba",null,attributeId).get();

            System.out.println("Attribute: " + attribute.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

}
