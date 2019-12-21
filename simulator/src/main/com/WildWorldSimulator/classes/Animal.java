package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.*;
import com.WildWorldSimulator.interfaces.*;

import java.util.*;

public class Animal implements IMapObject, IAnimalObserverTarget {
    private Point position;
    private MapDirection orientation;
    private IWorldMap map;
    private List<IAnimalObserver> observers = new ArrayList<>();
    private Genes genes;
    private int energy;
    private List<Animal> children = new ArrayList<>();
    private int lifeLength = 0;

    // CONSTRUCTORS

    public Animal(Point startingPoint, int startEnergy) {
        position = startingPoint;
        orientation = MapDirection.getRandomDirection();
        energy = startEnergy;
    }

    public Animal(Point startingPoint, Genes newGenes, int startEnergy) {
        position = startingPoint;
        orientation = MapDirection.getRandomDirection();
        genes = newGenes;
        energy = startEnergy;
    }

    // GETTERS & SETTERS

    public Point getPosition() {
        return position;
    }

    public Genes getGenes() {
        return genes;
    }

    public int getEnergy() {
        return energy;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public List<Animal> getChildren() {
        return children;
    }

    public int getLifeLength() {
        return lifeLength;
    }

    public void setMap(IWorldMap map) {
        this.map = map;
        if (genes == null) {
            genes = new Genes(map.getStartingParams().genesLength, map.getStartingParams().genesRange);
        }
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    // ANIMAL'S PURPOSE OF LIFE

    public void move() {
        lifeLength += 1;
        if (map == null) return;

        orientation = genes.getNextMove(orientation);
        Point newPosition = position.add(orientation.toUnitVector());
        int mapWidth = map.getStartingParams().width;
        int mapHeight = map.getStartingParams().height;
        newPosition = new Point(Math.floorMod(newPosition.x , mapWidth), Math.floorMod(newPosition.y , mapHeight));
        Animal animalToCopulate = map.animalAt(newPosition);
        Grass grassOnNextField = map.grassAt(newPosition);
        if (animalToCopulate != null) {
            int minimumEnergy = map.getStartingParams().minimumEnergyToCopulate;
            if (this.energy > minimumEnergy && animalToCopulate.energy > minimumEnergy) {
                map.place(copulateWith(animalToCopulate));
            }
        } else if (grassOnNextField != null) {
            eatGrass(newPosition);
        }
        energy -= map.getStartingParams().everydayEnergyLoss;

        if (energy <= 0) {
            notifyObserversAnimalDied();
            return;
        }

        Point oldPosition = position;
        position = newPosition;
        notifyObserversPositionChanged(oldPosition);
    }

    public void eatGrass(Point position) {
        energy += map.getStartingParams().grassEnergy;
        map.removeGrassFromMap(position);
    }

    private Animal copulateWith(Animal animalToCopulate) {
        Point childPosition = position.add(MapDirection.getRandomDirection().toUnitVector());
        int mapWidth = map.getStartingParams().width;
        int mapHeight = map.getStartingParams().height;
        Point formattedPosition = new Point(Math.floorMod(childPosition.x , mapWidth), Math.floorMod(childPosition.y, mapHeight));
        Genes childGenes = new Genes(genes, animalToCopulate.getGenes());
        int childEnergy = energy / 4 + animalToCopulate.getEnergy() / 4;
        Animal child = new Animal(formattedPosition, childGenes, childEnergy);
        energy -= energy / 4;
        animalToCopulate.energy -= animalToCopulate.getEnergy() / 4;
        children.add(child);
        return child;
    }

    // OBSERVER METHODS

    @Override
    public void addObserver(IAnimalObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IAnimalObserver observer) {
        observers.remove(observer);
    }

    private void notifyObserversPositionChanged(Point oldPosition) {
        for (IAnimalObserver observer : observers) {
            observer.positionChanged(this, oldPosition);
        }
    }

    private void notifyObserversAnimalDied() {
        List<IAnimalObserver> observersCopy = new ArrayList<>(observers);
        for (IAnimalObserver observer : observersCopy) {
            observer.animalDied(this);
        }
    }

    // UTILS

    @Override
    public String toString() {
        return orientation.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Animal))
            return false;
        Animal that = (Animal) other;
        return (position.equals(that.getPosition()) && energy == that.getEnergy() && orientation.equals(that.orientation));
    }
}
