package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.Point;

public class StartingParams {
    public final Point lowerLeft;
    public final Point upperRight;
    public final int size;
    public final int genesLength;
    public final int genesRange;
    public final int minimumEnergyToCopulate;
    public final int startingEnergy;
    public final int everydayEnergyLoss;
    public final int grassEnergy;

    public StartingParams(
            int size,
            int genesLength,
            int genesRange,
            int minimumEnergyToCopulate,
            int startingEnergy,
            int everydayEnergyLoss,
            int grassEnergy
    ) {
        this.lowerLeft = new Point(0, 0);
        this.upperRight = new Point(size-1, size-1);
        this.size = size;
        this.genesLength = genesLength;
        this.genesRange = genesRange;
        this.minimumEnergyToCopulate = minimumEnergyToCopulate;
        this.startingEnergy = startingEnergy;
        this.everydayEnergyLoss = everydayEnergyLoss;
        this.grassEnergy = grassEnergy;
    }
}
