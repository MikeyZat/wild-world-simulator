package agh.wildWorldSimulator.interfaces;

import agh.wildWorldSimulator.classes.Animal;
import agh.wildWorldSimulator.classes.Point;

public interface IAnimalObserver {

    void positionChanged(Animal animal, Point oldPosition);
    void animalDied(Animal animal);
}