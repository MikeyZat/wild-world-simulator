package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.*;

import java.util.List;

public class Statistics {
    public final int[][] map;
    public final int animalCount;
    public final int grassCount;
    public final double averageEnergy;
    public final double averageChildrenNum;
    public final double averageLifeLength;
    public final int[] genesFrequency;
    public final List <Integer> mainGenom;

    public Statistics(
            StartingParams startingParams,
            List<Animal> animalsList,
            List<Grass> grassList,
            int animalCount,
            int grassCount,
            double averageEnergy,
            double averageChildrenNum,
            double averageLifeLength,
            int[] genesFrequency,
            List <Integer> mainGenom
    ) {
        this.map = getMapFromLists(animalsList, grassList, startingParams);
        this.animalCount = animalCount;
        this.grassCount = grassCount;
        this.averageEnergy = averageEnergy;
        this.averageChildrenNum = averageChildrenNum;
        this.averageLifeLength = averageLifeLength;
        this.genesFrequency = genesFrequency;
        this.mainGenom = mainGenom;
    }

    public void printStatistic() {
        System.out.print("animal count: " + animalCount + " ");
        System.out.print("grass count: " + grassCount + " ");
        System.out.print("average energy: " + averageEnergy + " ");
        System.out.print("average children num: " + averageChildrenNum + " ");
        System.out.println("average life length: " + averageLifeLength + " ");
        System.out.println("Genes frequency");
        for (int j = 0; j < genesFrequency.length; j++) {
            System.out.print(j + " - " + genesFrequency[j] + " | ");
        }
        System.out.println();
        System.out.println("Most frequent genom:");
        mainGenom.forEach(System.out::print);
        System.out.println();
        System.out.println();
    }

    private int[][] getMapFromLists(List<Animal> animalsList, List<Grass> grassList, StartingParams startingParams){

        int [][] map = new int[startingParams.width][startingParams.height];
        for (Animal animal : animalsList) {
            Point position = animal.getPosition();
            int mappedEnergy = (int)(((double)animal.getEnergy()/(double)startingParams.startingEnergy)*10) + 1;
            if (map[position.x][position.y] < mappedEnergy) {
                map[position.x][position.y] = Math.min(mappedEnergy, 9);
            }
        }
        for (Grass grass : grassList){
            Point position = grass.getPosition();
            map[position.x][position.y] = 10;
        }
        return map;
    }
}
