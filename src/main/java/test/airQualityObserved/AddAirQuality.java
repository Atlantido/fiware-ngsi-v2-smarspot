package test.airQualityObserved;

import com.orange.ngsi2.client.Ngsi2Client;
import com.orange.ngsi2.model.Attribute;
import com.orange.ngsi2.model.Entity;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AddAirQuality {
    private static String IDs[] = {
            "address", "addressCountry", "addressLocality", "streetAddress", "dateObserved",
            "location", "type", "coordinates", "source", "precipitacion",
            "relativeHumidity", "temperature", "windDirection", "windSpeed",
            "airQualityLevel", "reliability", "CO", "NO", "NO2", "NOx", "SO2",
            "CO_level","NO_level","refPointOfInterest"};

    private static Object values[] = {
            "ES","Madrid","Plaza de España","2016-03-15T11:00:00/2016-03-15T12:00:00",
            "Point", "[-3.712247222222222, 40.423852777777775]]", "http://datos.madrid.es",
            0,0.54,12.2,186,0.64,"moderate",0.9,500,45,69,139,11,"good","moderate","28079004-Pza. de España"
    };

    private static HashMap<String,Attribute> createAttr() {
        HashMap<String,Attribute> attribute = new HashMap<>();

        List<HashMap<String,Double>> pruebas = new LinkedList<>();
        HashMap<String,Double> pruebas1 = new HashMap<>();

        return attribute;
    }


    private static HashMap<String ,Attribute> createAttributes() {
       HashMap<String,Attribute> attributes = new HashMap<>();

       Attribute attribute = null;
       HashMap<String,String> complexType = new HashMap<>();
       int lengthIDs = IDs.length;
       int contadorAddress = 0;
       int contadorLocation = 0;
       HashMap<String,Object> subAttributes = null;
       String masterAttribute = "";
       int posValues = 0;
       for(int i = 0; i < lengthIDs; i++) {
           if (IDs[i].equals("address")) {
               masterAttribute = "address";
               subAttributes = new HashMap<>();

           } else if (masterAttribute.equals("address") && contadorAddress < 3) {
               subAttributes.put(IDs[i],values[posValues]);
               if(contadorAddress == 2) {
                   attribute = new Attribute();
                   attribute.setValue(subAttributes);
                   attributes.put("address",attribute);
               }
               posValues++;
               contadorAddress++;
           } else if(IDs[i].equals("location")) {
               masterAttribute = "location";
               subAttributes = new HashMap<>();
           } else if(masterAttribute.equals("location") && contadorLocation < 2) {
                subAttributes.put(IDs[i],values[posValues]);
                if(contadorLocation == 1) {
                    attribute = new Attribute();
                    attribute.setValue(subAttributes);
                    attributes.put("location",attribute);
                }
                posValues++;
                contadorLocation++;
           } else {
               attribute = new Attribute(values[posValues]);
               attributes.put(IDs[i],attribute);
               posValues++;
           }
       }
       return attributes;
    }


    public static void main(String[] args) {
        HashMap<String, Attribute> attributes = createAttributes();
        AsyncRestTemplate template = new AsyncRestTemplate();
        Entity entity = new Entity("prueba","AirQualityObserved",attributes);
        Ngsi2Client client = new Ngsi2Client(template,"http://192.168.1.250:1026");

        client.addHttpHeader("fiware-service","smartspot");
        client.addHttpHeader("fiware-servicepath","/smartspot");
        client.setHttpHeaderJSON();

        client.addEntity(entity);
    }

}
