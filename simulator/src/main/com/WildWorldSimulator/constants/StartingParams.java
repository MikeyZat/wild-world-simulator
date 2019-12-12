package com.WildWorldSimulator.constants;

import com.WildWorldSimulator.classes.Point;

public class StartingParams {
    public final Point lowerLeft;
    public final Point upperRight;
    public final int size;

    public StartingParams(Point lowerLeft, Point upperRight, int size){
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.size = size;
    }
}
