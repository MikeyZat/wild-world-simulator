package com.WildWorldSimulator.interfaces;

public interface IPositionChangeSubject {
    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);
}