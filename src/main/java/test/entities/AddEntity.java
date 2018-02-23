package test.entities;


import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Attribute;
import com.orange.ngsi2.model.Entity;

import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;


public class AddEntity {

    private static HashMap<String,Attribute> createAttributes() {
        String IDs[] = {
                        "CO",
                        "H2M",
                        "H2S",
                        "NO2",
                        "O3",
                        "SO2",
                        "batteryLevel",
                        "humidity0",
                        "humidity1",
                        "nearDevicesHour",
                        "nearDevicesMinute",
                        "nearDevicesTenMinutes",
                        "noise",
                        "temperature0"};
        String values[] = {
                            "26,5",
                            "14.232221002",
                            "14.232221002",
                            "16.822380869627054",
                            "55.87127377401371",
                            "50.49543883170808",
                            "10",
                            "55.637690554388904",
                            "54.68126740585568",
                            "39",
                            "8",
                            "51",
                            "74.0",
                            "15.2444223"};

        HashMap<String,Attribute> attributes = new HashMap<String,Attribute>();

        for(int i = 0; i < IDs.length; i++) {
            attributes.put(IDs[i],new Attribute(values[i]));
        }

        return attributes;
    }

    public static void main(String[] args) {
        HashMap<String, Attribute> attributes = createAttributes();
        AsyncRestTemplate template = new AsyncRestTemplate();
        Entity entity = new Entity("test","SmartSpot",attributes);
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");
        client.setHttpHeaderJSON();
        //Listener para notificar de la creacion
        client.addEntity(entity);

    }
}
