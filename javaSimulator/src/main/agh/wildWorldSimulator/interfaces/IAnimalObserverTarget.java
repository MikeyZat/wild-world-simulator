package agh.wildWorldSimulator.interfaces;

public interface IAnimalObserverTarget {
    void addObserver(IAnimalObserver observer);
    void removeObserver(IAnimalObserver observer);
}