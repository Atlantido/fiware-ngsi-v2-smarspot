package test.entities;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Attribute;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;

public class UpdateEntity {

    private static HashMap<String,Attribute> updateAttributes() {
        String id = "temperature";
        String value = "17";

        HashMap<String,Attribute> attributes = new HashMap<String,Attribute>();
        attributes.put(id,new Attribute(value));
        return attributes;
    }

    public static void main(String[] args) {
        AsyncRestTemplate template = new AsyncRestTemplate();

        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");
        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");
        client.setHttpHeaderJSON();

        client.updateEntity("prueba",null, updateAttributes(),false);

    }
}
