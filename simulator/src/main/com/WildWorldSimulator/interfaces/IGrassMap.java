package com.WildWorldSimulator.interfaces;

import com.WildWorldSimulator.classes.*;

public interface IGrassMap {
    Grass grassAt(Point position);
    Grass removeGrassFromMap(Point position);
}
