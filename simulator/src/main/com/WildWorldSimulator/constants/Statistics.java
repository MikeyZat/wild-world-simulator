package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.*;

import java.util.List;

public class Statistics {
    public final List<Animal> animalsList;
    public final List<Grass> grassList;
    public final int animalCount;
    public final int grassCount;
    public final double averageEnergy;
    public final double averageChildrenNum;
    public final double averageLifeLength;
    public final int[] genesFrequency;

    public Statistics(
            List<Animal> animalsList,
            List<Grass> grassList,
            int animalCount,
            int grassCount,
            double averageEnergy,
            double averageChildrenNum,
            double averageLifeLength,
            int[] genesFrequency
    ) {
        this.animalsList = animalsList;
        this.grassList = grassList;
        this.animalCount = animalCount;
        this.grassCount = grassCount;
        this.averageEnergy = averageEnergy;
        this.averageChildrenNum = averageChildrenNum;
        this.averageLifeLength = averageLifeLength;
        this.genesFrequency = genesFrequency;
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
        System.out.println("");
    }
}
