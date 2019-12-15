package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.Animal;
import com.WildWorldSimulator.classes.Point;

public interface IAnimalObserver {

    void positionChanged(Animal animal, Point oldPosition);
    void animalDied(Animal animal);
}