package main.parsers.impl.xmlParser;


import main.Building;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class SaxHandler extends DefaultHandler {

    private final HashMap<Building, Integer> buildingStats;

    public SaxHandler(){
        buildingStats = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (qName.equals("item")) {
            Building building = new Building(attributes.getValue("city"),
                    attributes.getValue("street"),
                    Integer.parseInt(attributes.getValue("floor")),
                    Integer.parseInt(attributes.getValue("house")));
            if (buildingStats.containsKey(building)) {
                buildingStats.replace(building, buildingStats.get(building) + 1);
            } else {
                buildingStats.put(building, 1);
            }
        }
    }
    public HashMap<Building, Integer> getStatistics(){
        return buildingStats;
    }
}
