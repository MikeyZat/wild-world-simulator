package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;

public interface IWorldMap extends IGrassMap, IAnimalMap {

    boolean place(Animal animal);
    boolean isOccupied(Point position);
    Object objectAt(Point position);
    void executeDay();
    int getSize();
}
