package agh.wildWorldSimulator.interfaces;

import agh.wildWorldSimulator.classes.*;

public interface IAnimalMap {
    Animal animalAt(Point position);
    Animal removeAnimalFromMap(Animal animal);
    void place(Animal animal);
}
