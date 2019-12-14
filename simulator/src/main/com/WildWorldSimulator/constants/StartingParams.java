package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.Point;

public class StartingParams {
    public final Point lowerLeft;
    public final Point upperRight;
    public final int width;
    public final int height;
    public final int jungleWidth;
    public final int jungleHeight;
    public final int genesLength;
    public final int genesRange;
    public final int minimumEnergyToCopulate;
    public final int startingEnergy;
    public final int everydayEnergyLoss;
    public final int grassEnergy;
    public final int numberOfAnimals;
    public final int numberOfGrass;

    public StartingParams(
            int width,
            int height,
            int jungleWidth,
            int jungleHeight,
            int genesLength,
            int genesRange,
            int minimumEnergyToCopulate,
            int startingEnergy,
            int everydayEnergyLoss,
            int grassEnergy,
            int numberOfAnimals,
            int numberOfGrass
    ) {
        this.lowerLeft = new Point(0, 0);
        this.upperRight = new Point(width-1, height-1);
        this.width = width;
        this.height = height;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.genesLength = genesLength;
        this.genesRange = genesRange;
        this.minimumEnergyToCopulate = minimumEnergyToCopulate;
        this.startingEnergy = startingEnergy;
        this.everydayEnergyLoss = everydayEnergyLoss;
        this.grassEnergy = grassEnergy;
        this.numberOfAnimals = numberOfAnimals;
        this.numberOfGrass = numberOfGrass;
    }
}
