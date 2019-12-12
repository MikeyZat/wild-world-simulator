package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.interfaces.*;
import com.WildWorldSimulator.util.MapVisualizer;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver {
    private Map<Point, Grass> grasses = new HashMap<>();
    private Map<Point, Animal> animals = new HashMap<>();
    private List<Animal> animalsList = new ArrayList<>();
    private Point lowerLeft;
    private Point upperRight;
    private int size;

    public WorldMap(StartingParams startingParams){
        lowerLeft = startingParams.lowerLeft;
        upperRight = startingParams.upperRight;
        size = startingParams.size;
    }


    public Grass grassAt(Point position) {
        if (grasses.containsKey(position)){
            return grasses.get(position);
        }
        return null;
    }

    public Animal animalAt(Point position) {
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }

    @Override
    public Object objectAt(Point position) {
        Object searchedObject = animalAt(position);
        if (searchedObject == null) searchedObject = grassAt(position);
        return searchedObject;
    }

    @Override
    public Animal removeAnimalFromMap(Point position) {
        Animal animal = animals.get(position);
        animals.remove(position);
        animalsList.remove(animal);
        return animal;
    }

    @Override
    public Grass removeGrassFromMap(Point position) {
        Grass grass = grasses.get(position);
        grasses.remove(position);
        return grass;
    }

    @Override
    public void executeDay() {
        spawnGrass();
        moveAnimals();
    }

    @Override
    public int getSize() {
        return size;
    }

    private void spawnGrass(){

    }

    private void moveAnimals(){
        for (Animal animal : animalsList){
            animal.move();
        }
    }

    @Override
    public boolean isOccupied(Point position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        Point position = animal.getPosition();
        if (animalAt(position) == null) {
            animals.put(position, animal);
            animalsList.add(animal);
            animal.setMap(this);
            animal.addObserver(this);
            if (grassAt(position) != null){
                animal.eatGrass(position);
            }
            return true;
        }
        return false;
    }

    @Override
    public void positionChanged(Point oldPosition, Point newPosition){
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    @Override
    public void animalDied(Point position) {
        Animal animal = removeAnimalFromMap(position);
        animal.removeObserver(this);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft, upperRight);
    }
}
