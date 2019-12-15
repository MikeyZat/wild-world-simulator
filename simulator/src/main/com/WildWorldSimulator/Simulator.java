package com.WildWorldSimulator;

import com.WildWorldSimulator.classes.WorldMap;
import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.util.JsonParser;
import com.WildWorldSimulator.util.JsonParserException;

public class Simulator {
    public static void main(String[] args) {
        try {
            StartingParams startingParams = JsonParser.getStartingParamsFromJson("../parameters.json");
            WorldMap worldMap = new WorldMap(startingParams);
            for (int i = 0; i < 10000; i++) {
                worldMap.executeDay();
                System.out.println();
            }
        } catch (JsonParserException e) {
            System.out.println("Wczytywanie danych startowych z pliku JSON nie powiodło się");
        }
    }
}
