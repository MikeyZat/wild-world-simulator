package com.WildWorldSimulator.classes;

import com.WildWorldSimulator.constants.StartingParams;
import com.WildWorldSimulator.interfaces.IWorldMap;
import com.WildWorldSimulator.util.JsonParser;
import com.WildWorldSimulator.util.JsonParserException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldMapTest {

    private IWorldMap map;
    private Animal animal1;
    private Animal animal2;
    private Grass grass;
    private StartingParams startingParams;

    @Before
    public void init() throws JsonParserException {
        startingParams = JsonParser.getStartingParamsFromJson("testJsons/jsonParseGoodData.json");
        map = new WorldMap(startingParams);
        animal1 = new Animal(new Point(2,2), startingParams.startingEnergy);
        animal2 = new Animal(new Point(3,2), startingParams.startingEnergy);
        grass = new Grass(new Point(1,4));
        map.getGrasses().put(grass.getPosition(), grass);
        map.place(animal1);
        map.place(animal2);
    }

    @Test
    public void animalAt() {
        assertNull(map.animalAt(new Point(1, 1)));
        assertNull(map.animalAt(grass.getPosition()));
        assertEquals(animal1, map.animalAt(animal1.getPosition()));
        assertEquals(animal2, map.animalAt(animal2.getPosition()));
    }

    @Test
    public void grassAt() {
        assertNull(map.grassAt(new Point(1, 1)));
        assertNotNull(map.grassAt(grass.getPosition()));
        assertNull(map.grassAt(animal1.getPosition()));
        assertNull(map.grassAt(animal2.getPosition()));
    }

    @Test
    public void objectAt() {
        assertNull(map.objectAt(new Point(1, 1)));
        assertNotNull(map.objectAt(grass.getPosition()));
        assertEquals(animal1, map.objectAt(animal1.getPosition()));
        assertEquals(animal2, map.objectAt(animal2.getPosition()));
    }

    @Test
    public void removeAnimalFromMap() {
        assertNotNull(map.animalAt(animal1.getPosition()));
        assertNotNull(map.animalAt(animal2.getPosition()));
        assertEquals(animal1, map.removeAnimalFromMap(animal1));
        assertEquals(animal2, map.removeAnimalFromMap(animal2));
        assertNull(map.animalAt(animal1.getPosition()));
        assertNull(map.animalAt(animal2.getPosition()));
        assertNull(map.objectAt(animal1.getPosition()));
        assertNull(map.objectAt(animal2.getPosition()));
    }

    @Test
    public void removeGrassFromMap() {
        assertNotNull(map.grassAt(grass.getPosition()));
        assertEquals(grass, map.removeGrassFromMap(grass.getPosition()));
        assertNull(map.grassAt(grass.getPosition()));
        assertNull(map.objectAt(grass.getPosition()));
    }

    @Test
    public void isOccupied() {
        assertFalse(map.isOccupied(new Point(1, 1)));
        assertTrue(map.isOccupied(grass.getPosition()));
        assertTrue(map.isOccupied(animal1.getPosition()));
        assertTrue(map.isOccupied(animal2.getPosition()));
    }

    @Test
    public void place() {
        Animal animal3 = new Animal(new Point(5,5), startingParams.startingEnergy);
        map.place(animal3);
        assertEquals(animal3, map.animalAt(animal3.getPosition()));
        assertEquals(animal3, map.objectAt(animal3.getPosition()));
    }

    @Test
    public void positionChanged() {
        Point oldPosition = animal1.getPosition();
        animal1.setPosition(animal1.getPosition().add(new Point(1,1)));
        map.positionChanged(animal1, oldPosition);
        assertNull(map.animalAt(oldPosition));
        assertEquals(animal1, map.animalAt(animal1.getPosition()));
    }

    @Test
    public void animalDied() {
        map.animalDied(animal1);
        assertNull(map.animalAt(animal1.getPosition()));
    }
}