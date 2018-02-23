package test.entities;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Attribute;
import com.orange.ngsi2.model.Entity;
import org.springframework.web.client.AsyncRestTemplate;
import sun.plugin.dom.core.Attr;

import java.util.HashMap;

public class ReplaceAttributes {

    private static HashMap<String,Attribute> createAttributes() {
        HashMap<String,Attribute> attributes = new HashMap<String, Attribute>();
        attributes.put("newAttribute", new Attribute("test"));

        return attributes;
    }

    public static void main(String[] args) {
        HashMap<String, Attribute> attributes = createAttributes();
        AsyncRestTemplate template = new AsyncRestTemplate();

        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");
        client.setHttpHeaderJSON();

        //Replace all attributes for a entity
        client.replaceEntity("prueba",null,attributes);
    }
}
