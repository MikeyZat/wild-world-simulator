package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.interfaces.*;
import com.WildWorldSimulator.util.MapVisualizer;

import java.util.*;

public class WorldMap implements IWorldMap {
    private Map<Point, Grass> grasses = new HashMap<>();
    private Map<Point, Animal> animals = new HashMap<>();
    private StartingParams startingParams;
    private Point jungleLowerLeft;
    private Point jungleUpperRight;

    public WorldMap(StartingParams startingParams) {
        this.startingParams = startingParams;
        int jungleStartX = startingParams.width / 2 - startingParams.jungleWidth / 2;
        int jungleStartY = startingParams.height / 2 - startingParams.jungleHeight / 2;
        jungleLowerLeft = startingParams.lowerLeft.add(new Point(jungleStartX, jungleStartY));
        jungleUpperRight = jungleLowerLeft.add(new Point(startingParams.jungleWidth, startingParams.jungleHeight));
    }


    public Grass grassAt(Point position) {
        if (grasses.containsKey(position)) {
            return grasses.get(position);
        }
        return null;
    }

    public Animal animalAt(Point position) {
        if (animals.containsKey(position)) {
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

    private void spawnGrass() {
        GrassGenerator.plantGrass(this);
    }

    private void moveAnimals() {
        for (Animal animal : animals.values()) {
            animal.move();
        }
    }

    @Override
    public boolean isOccupied(Point position) {
        return objectAt(position) != null;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException {
        Point position = animal.getPosition();
        if (animalAt(position) == null) {
            animals.put(position, animal);
            animal.setMap(this);
            animal.addObserver(this);
            if (grassAt(position) != null) {
                animal.eatGrass(position);
            }
            return true;
        }
        return false;
    }

    @Override
    public void positionChanged(Point oldPosition, Point newPosition) {
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
        return visualizer.draw(startingParams.lowerLeft, startingParams.upperRight);
    }

    @Override
    public StartingParams getStartingParams() {
        return startingParams;
    }

    @Override
    public Point getJungleLowerLeft() {
        return jungleLowerLeft;
    }

    @Override
    public Point getJungleUpperRight() {
        return jungleUpperRight;
    }

    @Override
    public Map<Point, Grass> getGrasses() {
        return grasses;
    }
}
