package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.*;
import com.WildWorldSimulator.interfaces.*;

import java.util.*;

public class Animal implements IMapObject, IPositionChangeSubject {
    private Point position;
    private MapDirection orientation;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();
    private Genes genes;
    private int energy;

    // CONSTRUCTORS

    public Animal(int x, int y) {
        position = new Point(x, y);
        orientation = MapDirection.getRandomDirection();
    }

    public Animal(Point startingPoint) {
        position = startingPoint;
        orientation = MapDirection.getRandomDirection();
    }

    public Animal(Point startingPoint, Genes newGenes) {
        position = startingPoint;
        orientation = MapDirection.getRandomDirection();
        genes = newGenes;
    }

    // GETTERS & SETTERS

    public Point getPosition() {
        return position;
    }

    public void setMap(IWorldMap map) {
        this.map = map;
        if (genes == null) {
            genes = new Genes(map.getStartingParams().genesLength, map.getStartingParams().genesRange);
        }
    }

    // ANIMAL'S PURPOSE OF LIFE

    public void move() {
        orientation = genes.getNextMove();
        Point newPosition = position.add(orientation.toUnitVector());
        if (map != null) {
            int mapSize = map.getStartingParams().size;
            newPosition = new Point(newPosition.x % mapSize, newPosition.y % mapSize);
            Animal animalToCopulate = map.animalAt(newPosition);
            Grass grassOnNextField = map.grassAt(newPosition);
            if (animalToCopulate != null) {
                int minimumEnergy = map.getStartingParams().minimumEnergyToCopulate;
                if (this.energy > minimumEnergy && animalToCopulate.energy > minimumEnergy) {
                    copulateWith(animalToCopulate);
                }
            } else if (grassOnNextField != null) {
                eatGrass(newPosition);
            }
        }
        energy -= map.getStartingParams().everydayEnergyLoss;
        if (energy <= 0) {
            notifyObserversAnimalDied(position);
            return;
        }

        Point oldPosition = position;
        position = newPosition;
        notifyObserversPositionChanged(oldPosition, position);
    }

    public void eatGrass(Point position) {
        energy += 10;
        map.removeGrassFromMap(position);
    }

    private void copulateWith(Animal animalToCopulate) {

    }

    // OBSERVER METHODS

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    private void notifyObserversPositionChanged(Point oldPosition, Point newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }

    private void notifyObserversAnimalDied(Point position) {
        for (IPositionChangeObserver observer : observers) {
            observer.animalDied(position);
        }
    }

    // UTILS

    @Override
    public String toString() {
        return orientation.toString();
    }

}
