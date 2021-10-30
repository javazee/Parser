package main.parsers;

import main.Building;

import java.util.HashMap;
import java.util.Map;

public interface Parser {

    void parse();

    void printStatistics();

    default String getStatistics(HashMap<Building, Integer> buildingStats){
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------------------------------------------\n")
                .append("######### #  #   ").append("Статистика по городам").append("   #  # #########")
                .append("\n-------------------------------------------------------\n");
        HashMap<String, HashMap<Integer, Integer>> statsByCity = new HashMap<>();
        for (Building building :buildingStats.keySet()){
            if (statsByCity.containsKey(building.getCity())) {
                if (statsByCity.get(building.getCity()).containsKey(building.getFloor())){
                    statsByCity.get(building.getCity()).replace(building.getFloor(), building.getHouseNumber() + 1);
                } else {
                    statsByCity.get(building.getCity()).put(building.getFloor(), building.getHouseNumber());
                }
            } else {
                HashMap<Integer, Integer> statsByFloor = new HashMap<>();
                statsByFloor.put(building.getFloor(), 1);
                statsByCity.put(building.getCity(), statsByFloor);
            }
        }
        for (Map.Entry<String, HashMap<Integer, Integer>> entry : statsByCity.entrySet()) {
            sb.append("\n                г. ").append(entry.getKey()).append(" :\n\n");
            HashMap<Integer, Integer> statsByFloor = entry.getValue();
            for (int i = statsByFloor.size(); i >= 1; i--) { //4
                sb.append(statsByFloor.get(i)).append(" ед. - общее количество ").append(i).append("-этажных зданий\n");
                for (int j = 0; j <= statsByFloor.size() - i; j++) { //j=1
                    int digitCount = statsByFloor.get(statsByFloor.size() - j).toString().length(); //
                    for (int k = 0; k < digitCount; k++) {
                        sb.append("█");
                    }
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.append("\n------------------------------------------------------\n")
                .append("######### #  #   ").append("Дублирующиеся записи").append("   #  # #########")
                .append("\n------------------------------------------------------\n");
        for (Map.Entry<Building, Integer> entry: buildingStats.entrySet()){
            if (entry.getValue() == 1) continue;
            sb.append(entry.getKey());
            sb.append(" - ");
            sb.append(entry.getValue());
            sb.append(" повт.\n");
        }
        sb.append("------------------------------------------------------\n\n");
        return sb.toString();
    }
}
