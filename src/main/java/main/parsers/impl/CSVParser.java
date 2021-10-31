package main.parsers.impl;

import main.Building;
import main.parsers.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class CSVParser implements Parser {

    private final String path;
    private final String  DELIMITER = ";";

    public HashMap<Building, Integer> buildingStats;

    public CSVParser(String path){
        this.path = path;
        buildingStats = new HashMap<>();
    }

    @Override
    public void parse(){
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))){
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] columns = line.split(DELIMITER);
                    Building building = new Building(columns[0],
                            columns[1],
                            Integer.parseInt(columns[3]),
                            Integer.parseInt(columns[2]));
                    if (buildingStats.containsKey(building)) {
                        buildingStats.replace(building, buildingStats.get(building) + 1);
                    } else {
                        buildingStats.put(building, 1);
                    }
                } catch (NumberFormatException ex){
                    System.out.println(ex.getCause() + " " + ex.getMessage() + " in  line " + line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        };
    }

    @Override
    public void printStatistics() {
        if (buildingStats == null){
            System.out.println("Для получения статистики необходимо предварительно распарсить файл");
        } else {
            System.out.println(this.getStatistics(buildingStats));
        }
    }

}
