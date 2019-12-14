package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.Point;

public class StartingParams {
    public final Point lowerLeft;
    public final Point upperRight;
    public final int size;
    public final int minimumEnergyToCopulate;
    public final int genesLength;
    public final int genesRange;
    public final int everydayEnergyLoss;

    public StartingParams(
            Point lowerLeft,
            Point upperRight,
            int size,
            int minimumEnergyToCopulate,
            int genesLength,
            int genesRange,
            int everydayEnergyLoss
    ) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.size = size;
        this.minimumEnergyToCopulate = minimumEnergyToCopulate;
        this.genesLength = genesLength;
        this.genesRange = genesRange;
        this.everydayEnergyLoss = everydayEnergyLoss;
    }
}
