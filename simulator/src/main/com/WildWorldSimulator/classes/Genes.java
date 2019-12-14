package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.MapDirection;

import java.util.Arrays;

public class Genes {
    private int[] genesArray;
    private int length;
    private int genesRange;

    public Genes(int length) {
        genesArray = new int[length];
        this.length = length;
    }

    public Genes(int length, int genesRange) {
        this(length);
        this.genesRange = genesRange;
        fillWithRandomGenes();
        fixGenes();
    }

    public Genes(Genes firstGenes, Genes secondGenes) throws IllegalArgumentException {
        this(firstGenes.getLength());
        this.genesRange = firstGenes.getGenesRange();

        if (firstGenes.getLength() != secondGenes.getLength()) {
            throw new IllegalArgumentException("Genes have different length and they can not mutate.");
        }
        if (firstGenes.getGenesRange() != secondGenes.getGenesRange()) {
            throw new IllegalArgumentException("Genes have different range and they can not mutate.");
        }

        int firstSeparation = (int) (Math.random() * (length - 1));
        int secondSeparation = (int) (Math.random() * (length - 1));
        while (secondSeparation == firstSeparation) {   // in case they randomly select same place to separate
            secondSeparation = (int) (Math.random() * (length - 1));
        }

        int maxSeparation = Math.max(firstSeparation, secondSeparation);

        for (int i=0; i<maxSeparation; i++) {
            genesArray[i] = firstGenes.getGenesArray()[i];
        }
        for (int i=maxSeparation; i<length; i++) {
            genesArray[i] = secondGenes.getGenesArray()[i];
        }
        fixGenes();
    }


    public MapDirection getNextMove(MapDirection previousPosition) {
        int delta = genesArray[(int)(Math.random()*length)];
        return previousPosition.getNextDirection(delta);
    }



    public void fillWithRandomGenes() {
        for (int i = 0; i < length; i++) {
            genesArray[i] = (int) (Math.random() * (genesRange));
        }
    }

    public void fixGenes() {
        int[] values = new int[genesRange];
        for (int value : genesArray) {
            values[value] += 1;
        }
        for (int i = 0; i < genesRange; i++) {
            if (values[i] == 0) {
                int randomIndex = (int) (Math.random() * (length));
                while (values[genesArray[randomIndex]] < 2) {   // If we want to add missing gene we can't completely remove another gene
                    randomIndex = (int) (Math.random() * (length));
                }
                genesArray[randomIndex] = i;
                values[i]++;
                values[genesArray[randomIndex]]--;
            }
        }
        Arrays.sort(genesArray);
    }

    public int[] getGenesArray() {
        return genesArray;
    }

    public int getLength() {
        return length;
    }

    public int getGenesRange() {
        return genesRange;
    }

    public void setGenesArray(int[] genesArray){
        this.genesArray = genesArray;
    }
}
