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

    public Genes getGenes() { return genes; }

    public int getEnergy() { return energy; }

    public void setMap(IWorldMap map) {
        this.map = map;
        if (genes == null) {
            genes = new Genes(map.getStartingParams().genesLength, map.getStartingParams().genesRange);
        }
    }

    // ANIMAL'S PURPOSE OF LIFE

    public void move() {
        orientation = genes.getNextMove(orientation);
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
            energy -= map.getStartingParams().everydayEnergyLoss;
        }
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
        Point childPosition = position.add(MapDirection.getRandomDirection().toUnitVector());
        Genes childGenes = new Genes(genes, animalToCopulate.getGenes());
        int childEnergy = energy/4 + animalToCopulate.getEnergy()/4;
        Animal child = new Animal(childPosition, childGenes, childEnergy);
        map.place(child);
        energy -= energy/4;
        animalToCopulate.energy -= animalToCopulate.getEnergy()/4;
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
