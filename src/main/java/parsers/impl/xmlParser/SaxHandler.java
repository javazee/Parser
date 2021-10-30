package parsers.impl.xmlParser;


import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class SaxHandler extends DefaultHandler {

    private final HashMap<Integer, Integer> buildingStats;

    public SaxHandler(){
        buildingStats = new HashMap<>();
    }

    @Override
    public void startDocument() {
        buildingStats.put(1, 0);
        buildingStats.put(2, 0);
        buildingStats.put(3, 0);
        buildingStats.put(4, 0);
        buildingStats.put(5, 0);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equals("item")) {
            int floor = Integer.parseInt(attributes.getValue("floor"));
            if (buildingStats.containsKey(floor)){
                buildingStats.replace(floor, buildingStats.get(floor) + 1);
            }
        }
    }

    public HashMap<Integer, Integer> getStatistics(){
        return buildingStats;
    }
}
