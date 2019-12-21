package agh.wildWorldSimulator;

import agh.wildWorldSimulator.classes.*;
import agh.wildWorldSimulator.constants.*;
import agh.wildWorldSimulator.util.*;

import java.util.*;

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
        int ITERATIONS = 10000;

        Simulator.addStartingGrass(startingParams, worldMap);
        Simulator.addStartingAnimals(startingParams, worldMap);
        Simulator.runSimulation(startingParams, worldMap, ITERATIONS, false, true);
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
        JsonParser.writeStatsToJson(statistics, "../results.json"); //UNCOMMENT THIS TO SAVE SIMULATION DATA IN JSON
    }

    public static void produceFinalStats(StartingParams startingParams, List<Statistics> statistics, int iterations) {
        int animalCount = 0;
        int grassCount = 0;
        double averageEnergy = 0;
        double averageChildrenNum = 0;
        double averageLifeLength = 0;
        int[] genesFrequency = new int[startingParams.genesRange];
        Map<List<Integer>, Integer> genesMap = new HashMap<>();
        for (Statistics stats : statistics) {
            animalCount += stats.animalCount;
            grassCount += stats.grassCount;
            averageEnergy += stats.averageEnergy;
            averageChildrenNum += stats.averageChildrenNum;
            averageLifeLength += stats.averageLifeLength;
            for (int i = 0; i < startingParams.genesRange; i++) {
                genesFrequency[i] += stats.genesFrequency[i];
            }
            if (genesMap.containsKey(stats.mainGenom)) {
                Integer prev = genesMap.get(stats.mainGenom);
                genesMap.put(stats.mainGenom, prev + 1);
            } else {
                genesMap.put(stats.mainGenom, 1);
            }
        }
        int maxFrequency = 0;
        List <Integer> mostFrequentGenes = new ArrayList<>();
        for (Map.Entry<List<Integer>, Integer> entry : genesMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentGenes = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        for (int i = 0; i < startingParams.genesRange; i++) {
            genesFrequency[i]/=iterations;
        }

        Statistics finalStats = new Statistics(
                animalCount / iterations,
                grassCount / iterations,
                averageEnergy / iterations,
                averageChildrenNum / iterations,
                averageLifeLength / iterations,
                genesFrequency,
                mostFrequentGenes
        );
        finalStats.printStatistic();
        JsonParser.writeStatsToJson(Collections.singletonList(finalStats), "../finalStatistics.json");
    }
}
