package com.WildWorldSimulator.classes;

import java.util.Random;

public class Point {
    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public boolean precedes(Point other) {
        return (x <= other.x && y <= other.y);
    }

    public boolean follow(Point other) {
        return (x >= other.x && y >= other.y);
    }

    public Point upperRight(Point other) {
        return new Point(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Point lowerLeft(Point other) {
        return new Point(Math.min(x, other.x), Math.min(y, other.y));
    }

    public Point add(Point other) {
        return new Point(x + other.x, y + other.y);
    }

    public Point substract(Point other) {
        return new Point(x - other.x, y - other.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof Point))
            return false;
        Point that = (Point) other;
        return (this.x == that.x && this.y == that.y);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash += this.x * 1211;
        hash += this.y * 1337;
        return hash % 32181246;
    }

    public Point opposite() {
        return new Point(-x, -y);
    }

    // rands points from lower and higher coordinates INCLUSIVE
    public static Point getRandomPoint(int lowerX, int higherX, int lowerY, int higherY) {
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(higherX + 1) + lowerX;
        int y = randomGenerator.nextInt(higherY + 1) + lowerY;
        return new Point(x,y);
    };
}
