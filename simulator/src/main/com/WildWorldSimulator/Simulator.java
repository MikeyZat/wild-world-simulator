package com.WildWorldSimulator;

import com.WildWorldSimulator.classes.Animal;
import com.WildWorldSimulator.classes.Point;
import com.WildWorldSimulator.classes.WorldMap;
import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.constants.Statistics;
import com.WildWorldSimulator.util.JsonParser;
import com.WildWorldSimulator.util.JsonParserException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Simulator {
    public static void main(String[] args) {
        StartingParams startingParams;
        try {
            startingParams = JsonParser.getStartingParamsFromJson("../parameters.json");
        } catch (JsonParserException e) {
            System.out.println("Reading starting params from JSON file failed");
            return;
        }

        WorldMap worldMap = new WorldMap(startingParams);
        int iterations = 1000;

        Simulator.addStartingGrass(startingParams, worldMap);
        Simulator.addStartingAnimals(startingParams, worldMap);

        Simulator.runSimulation(startingParams, worldMap, iterations, true, true);
    }

    public static void addStartingGrass(StartingParams startingParams, WorldMap worldMap) {
        int planterIterations = startingParams.numberOfGrass / startingParams.everydayGrassGain;
        for (int i = 0; i < planterIterations; i++) {
            worldMap.spawnGrass();
        }
    }

    public static void addStartingAnimals(StartingParams startingParams, WorldMap worldMap) {
        for (int i = 0; i < startingParams.numberOfAnimals; i++) {
            Animal animal = new Animal(Point.getRandomPoint(
                    startingParams.lowerLeft.x,
                    startingParams.upperRight.x,
                    startingParams.lowerLeft.y,
                    startingParams.upperRight.y), startingParams.startingEnergy);
            worldMap.place(animal);
        }
    }

    public static void runSimulation(StartingParams startingParams, WorldMap worldMap, int iterations, boolean printInConsole, boolean saveToJson) {
        List<Statistics> statistics = new ArrayList<>();
        Statistics dailyStats = worldMap.getStatistics();
        statistics.add(worldMap.getStatistics());
        for (int i = 0; i < iterations; i++) {
            worldMap.executeDay();
            dailyStats = worldMap.getStatistics();
            statistics.add(dailyStats);
            if (printInConsole) {
                System.out.println("day " + (i + 1));
                dailyStats.printStatistic();
            }
        }

        if (saveToJson) {
            Simulator.produceFinalStats(startingParams, statistics, iterations);
        }
        JsonParser.writeStatsToJson(statistics, "../simulationResults.json"); //UNCOMMENT THIS TO SAVE SIMULATION DATA IN JSON
    }

    public static void produceFinalStats(StartingParams startingParams, List<Statistics> statistics, int iterations) {
        int animalCount = 0;
        int grassCount = 0;
        double averageEnergy = 0;
        double averageChildrenNum = 0;
        double averageLifeLength = 0;
        int[] genesFrequency = new int[startingParams.genesRange];
        for (Statistics stats : statistics) {
            animalCount += stats.animalCount;
            grassCount += stats.grassCount;
            averageEnergy += stats.averageEnergy;
            averageChildrenNum += stats.averageChildrenNum;
            averageLifeLength += stats.averageLifeLength;
            for (int i = 0; i < startingParams.genesRange; i++) {
                genesFrequency[i] += stats.genesFrequency[i];
            }
        }
        for (int i = 0; i < startingParams.genesRange; i++) {
            genesFrequency[i]/=iterations;
        }

        Statistics finalStats = new Statistics(
                startingParams,
                new ArrayList<>(),
                new ArrayList<>(),
                animalCount / iterations,
                grassCount / iterations,
                averageEnergy / iterations,
                averageChildrenNum / iterations,
                averageLifeLength / iterations,
                genesFrequency,
                new int[]{}
        );
        finalStats.printStatistic();
        JsonParser.writeStatsToJson(Collections.singletonList(finalStats), "../finalStatistics.json");
    }
}
