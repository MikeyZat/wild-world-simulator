package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.StartingParams;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class AnimalTest {
    private Animal animal_0_0;
    private Animal animal_0_1;
    StartingParams startingParams = new StartingParams(
            10,
            10,
            3,
            3,
            32,
            8,
            25,
            25,
            1,
            5,
            0,
            0,
            2
    );
    WorldMap map;

    @Before
    public void init() {
        map = new WorldMap(startingParams);
        animal_0_0 = new Animal(new Point(0,0), startingParams.startingEnergy);
        animal_0_1 = new Animal(new Point(0,1), startingParams.startingEnergy);
    }

    @Test
    public void setMap() {
        assertNull(animal_0_0.getGenes());
        animal_0_0.setMap(map);
        assertNotNull(animal_0_0.getGenes());
    }

    @Test
    public void move() {
        Point oldPosition1 = animal_0_0.getPosition();
        Point oldPosition2 = animal_0_1.getPosition();
        animal_0_0.setMap(map);

        animal_0_0.move();
        animal_0_1.move();
        // Animal has moved
        assertNotEquals(oldPosition1, animal_0_0.getPosition());
        // Animal hasn't moved since it doesn't have a map assigned
        assertEquals(oldPosition2, animal_0_1.getPosition());
        // Animal has lost its energy
        assertEquals(startingParams.startingEnergy - startingParams.everydayEnergyLoss, animal_0_0.getEnergy());
        // Animal hasn't lost its energy since it doesn't have a map assigned
        assertEquals(startingParams.startingEnergy, animal_0_1.getEnergy());
    }

    @Test
    public void copulateWith() throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        animal_0_0.setMap(map);
        animal_0_1.setMap(map);
        int lostEnergy1 = animal_0_0.getEnergy()/4;
        int lostEnergy2 = animal_0_1.getEnergy()/4;

        Method method = Animal.class.getDeclaredMethod("copulateWith", Animal.class);
        method.setAccessible(true);
        Animal child = (Animal) method.invoke(animal_0_0, animal_0_1);

        assertNotNull(child);
        // parents give child good amount of energy
        assertEquals(lostEnergy1 + lostEnergy2, child.getEnergy());
        assertEquals(lostEnergy1, startingParams.startingEnergy - animal_0_0.getEnergy());
        assertEquals(lostEnergy2, startingParams.startingEnergy - animal_0_1.getEnergy());
        // child has different genes
        assertNotNull(child.getGenes());
        assertNotSame(child.getGenes(), animal_0_0.getGenes());
        assertNotSame(child.getGenes(), animal_0_1.getGenes());
        // child is next to parent animal
        assertNotEquals(animal_0_0.getPosition(), child.getPosition());

    }

    @Test
    public void eatGrass() {
        animal_0_0.setMap(map);
        // before eating
        assertEquals(startingParams.startingEnergy, animal_0_0.getEnergy());
        animal_0_0.eatGrass(new Point(2,2));
        // after eating
        assertEquals(startingParams.startingEnergy + startingParams.grassEnergy, animal_0_0.getEnergy());
    }

    @Test
    public void testToString() {
        assertEquals(animal_0_0.getOrientation().toString(), animal_0_0.toString());
    }
}