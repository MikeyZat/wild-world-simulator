package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.interfaces.IWorldMap;


public class GrassGenerator {
    public static void plantGrass(IWorldMap map) {
        StartingParams startingParams = map.getStartingParams();
        Point jungleLowerLeft = map.getJungleLowerLeft();
        Point jungleUpperRight = map.getJungleUpperRight();
        int grassInJungle = startingParams.everydayGrassGain / 2;
        int grassInSawanna = grassInJungle;

        int attempts = 20; // if we try to put a grass more than 20 times without a success we assume that map is full
        while ((grassInJungle > 0 || grassInSawanna > 0) && attempts > 0) {
            Point newPosition = Point.getRandomPoint(
                    startingParams.lowerLeft.x,
                    startingParams.upperRight.x,
                    startingParams.lowerLeft.y,
                    startingParams.upperRight.y);
            if (!map.isOccupied(newPosition)) {
                if (newPosition.follow(jungleLowerLeft) && newPosition.precedes(jungleUpperRight) && grassInJungle > 0) {
                    map.getGrasses().put(newPosition, new Grass(newPosition));
                    grassInJungle--;
                    attempts = 20;
                } else if (grassInSawanna > 0) {
                    map.getGrasses().put(newPosition, new Grass(newPosition));
                    grassInSawanna--;
                    attempts = 20;
                } else {
                    break;
                }
            } else {
                attempts--;
            }
        }
        attempts = 20;
        while(grassInJungle > 0 && attempts > 0) {
            Point newPosition = Point.getRandomPoint(
                    jungleLowerLeft.x,
                    jungleUpperRight.x,
                    jungleLowerLeft.y,
                    jungleUpperRight.y);
            if (!map.isOccupied(newPosition)) {
                map.getGrasses().put(newPosition, new Grass(newPosition));
                grassInJungle--;
                attempts = 20;
            } else{
                attempts--;
            }
        }
    }
}
