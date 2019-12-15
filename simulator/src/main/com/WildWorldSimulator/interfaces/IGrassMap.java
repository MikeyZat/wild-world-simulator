package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;

import java.util.Map;

public interface IGrassMap {
    Grass grassAt(Point position);
    Grass removeGrassFromMap(Point position);
    Map<Point, Grass> getGrasses();
}
