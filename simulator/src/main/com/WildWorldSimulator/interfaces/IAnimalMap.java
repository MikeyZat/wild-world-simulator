package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;

public interface IAnimalMap {
    Animal animalAt(Point position);
    Animal removeAnimalFromMap(Point position);
    boolean place(Animal animal) throws IllegalArgumentException;
}
