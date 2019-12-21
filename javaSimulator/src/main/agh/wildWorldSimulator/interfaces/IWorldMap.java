package agh.wildWorldSimulator.interfaces;

import agh.wildWorldSimulator.classes.*;
import agh.wildWorldSimulator.constants.StartingParams;

public interface IWorldMap extends IGrassMap, IAnimalMap, IAnimalObserver {

    boolean isOccupied(Point position);
    Object objectAt(Point position);
    void executeDay();
    StartingParams getStartingParams();
    Point getJungleLowerLeft();
    Point getJungleUpperRight();
}
