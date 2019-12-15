package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;

public interface IAnimalMap {
    Animal animalAt(Point position);
    Animal removeAnimalFromMap(Animal animal);
    void place(Animal animal);
}
