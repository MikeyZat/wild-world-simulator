package com.WildWorldSimulator.interfaces;

public interface IAnimalObserverTarget {
    void addObserver(IAnimalObserver observer);
    void removeObserver(IAnimalObserver observer);
}