package parsers;

import java.util.HashMap;
import java.util.Map;

public interface Parser {

    void parse();

    void printStatistics();

    default String getStatistics(HashMap<Integer, Integer> buildingStats){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry: buildingStats.entrySet()){
            sb.append(entry.getKey());
            sb.append(" : ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
