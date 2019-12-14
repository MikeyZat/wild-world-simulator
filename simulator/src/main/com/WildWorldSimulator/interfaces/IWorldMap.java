package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;
import com.WildWorldSimulator.constants.StartingParams;

public interface IWorldMap extends IGrassMap, IAnimalMap {

    boolean place(Animal animal);
    boolean isOccupied(Point position);
    Object objectAt(Point position);
    void executeDay();
    StartingParams getStartingParams();
}
