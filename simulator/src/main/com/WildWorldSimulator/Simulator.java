package com.WildWorldSimulator;

import com.WildWorldSimulator.classes.Animal;
import com.WildWorldSimulator.classes.Point;
import com.WildWorldSimulator.classes.WorldMap;
import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.constants.Statistics;
import com.WildWorldSimulator.util.JsonParser;
import com.WildWorldSimulator.util.JsonParserException;

import java.util.ArrayList;
import java.util.List;

public class Simulator {
    public static void main(String[] args) {
        try {
            StartingParams startingParams = JsonParser.getStartingParamsFromJson("../parameters.json");
            WorldMap worldMap = new WorldMap(startingParams);
            // add starting grass
            int planterIterations = startingParams.numberOfGrass / startingParams.everydayGrassGain;
            for (int i = 0; i < planterIterations; i++) {
                worldMap.spawnGrass();
            }
            // add starting animals
            for (int i = 0; i < startingParams.numberOfAnimals; i++) {
                Animal animal = new Animal(Point.getRandomPoint(
                        startingParams.lowerLeft.x,
                        startingParams.upperRight.x,
                        startingParams.lowerLeft.y,
                        startingParams.upperRight.y), startingParams.startingEnergy);
                worldMap.place(animal);
            }
            // run simulation
            List <Statistics> statistics = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                worldMap.executeDay();
                Statistics dailyStats = worldMap.getStatistics();
                statistics.add(dailyStats);
                System.out.println("day " + i);  // UNCOMMENT TO SEE STATS IN THE CONSOLE
                dailyStats.printStatistic();
            }

            JsonParser.writeStatsToJson(statistics, "../simulationResults.json");
        } catch (JsonParserException e) {
            System.out.println("Reading starting params from JSON file failed");
        }
    }
}
