package agh.wildWorldSimulator.classes;

import agh.wildWorldSimulator.constants.*;
import agh.wildWorldSimulator.interfaces.*;
import agh.wildWorldSimulator.util.MapVisualizer;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.stream.Collectors;

public class WorldMap implements IWorldMap {
    private Map<Point, Grass> grasses = new HashMap<>();
    private Map<Point, List<Animal>> animals = new HashMap<>();
    private StartingParams startingParams;
    private Point jungleLowerLeft;
    private Point jungleUpperRight;
    private int deaths = 0;
    private int lifeLengthSummary = 0;

    public WorldMap(StartingParams startingParams) {
        this.startingParams = startingParams;
        int jungleStartX = startingParams.width / 2 - startingParams.jungleWidth / 2;
        int jungleStartY = startingParams.height / 2 - startingParams.jungleHeight / 2;
        jungleLowerLeft = startingParams.lowerLeft.add(new Point(jungleStartX, jungleStartY));
        jungleUpperRight = jungleLowerLeft.add(new Point(startingParams.jungleWidth - 1, startingParams.jungleHeight - 1));
    }


    public Grass grassAt(Point position) {
        if (grasses.containsKey(position)) {
            return grasses.get(position);
        }
        return null;
    }

    public Animal animalAt(Point position) {
        if (animals.containsKey(position)) {
            animals.get(position).sort(Comparator.comparing(Animal::getEnergy));
            return animals.get(position).get(0);
        }
        return null;
    }

    @Override
    public Object objectAt(Point position) {
        Object searchedObject = animalAt(position);
        if (searchedObject == null) searchedObject = grassAt(position);
        return searchedObject;
    }

    @Override
    public Animal removeAnimalFromMap(Animal animalToRemove) {
        Point animalPosition = animalToRemove.getPosition();
        animals.get(animalPosition).remove(animalToRemove);
        if (animals.get(animalPosition).isEmpty()) animals.remove(animalPosition);
        return animalToRemove;
    }

    @Override
    public Grass removeGrassFromMap(Point position) {
        Grass grass = grasses.get(position);
        grasses.remove(position);
        return grass;
    }

    @Override
    public void executeDay() {
        spawnGrass();
        moveAnimals();
    }

    public void spawnGrass() {
        GrassGenerator.plantGrass(this);
    }

    private void moveAnimals() {
        List<Animal> animalsToRun = animals.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        animalsToRun.forEach(Animal::move);
    }

    @Override
    public boolean isOccupied(Point position) {
        return objectAt(position) != null;
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException {
        Point position = animal.getPosition();
        animals.putIfAbsent(position, new LinkedList<>());
        animals.get(position).add(animal);
        animal.setMap(this);
        animal.addObserver(this);
        if (grassAt(position) != null) {
            animal.eatGrass(position);
        }
    }

    @Override
    public void positionChanged(Animal animal, Point oldPosition) {
        animals.get(oldPosition).remove(animal);
        if (animals.get(oldPosition).isEmpty()) animals.remove(oldPosition);
        animals.putIfAbsent(animal.getPosition(), new LinkedList<>());
        animals.get(animal.getPosition()).add(animal);
    }

    @Override
    public void animalDied(Animal animal) {
        lifeLengthSummary += animal.getLifeLength();
        deaths++;
        removeAnimalFromMap(animal);
        animal.removeObserver(this);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(startingParams.lowerLeft, startingParams.upperRight);
    }

    @Override
    public StartingParams getStartingParams() {
        return startingParams;
    }

    @Override
    public Point getJungleLowerLeft() {
        return jungleLowerLeft;
    }

    @Override
    public Point getJungleUpperRight() {
        return jungleUpperRight;
    }

    @Override
    public Map<Point, Grass> getGrasses() {
        return grasses;
    }

    public Statistics getStatistics() {
        List<Animal> animalsList = animals.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        List<Grass> grassList = new ArrayList<>(grasses.values());
        int[] genesFrequency = new int[startingParams.genesRange];
        int animalCount = animalsList.size();
        int grassCount = grassList.size();
        double averageEnergy = 0;
        double averageChildrenNum = 0;
        double averageLifeLength;
        Map<List<Integer>, Integer> genesMap = new HashMap<>();
        for (Animal animal : animalsList) {
            averageEnergy += animal.getEnergy();
            averageChildrenNum += animal.getChildren().size();
            int[] animalGenesfrequency = animal.getGenes().getGenesFrequency();
            for (int i = 0; i < startingParams.genesRange; i++) {
                genesFrequency[i] += animalGenesfrequency[i];
            }
            List<Integer> genesAsArray = Arrays.stream(animal.getGenes().getGenesArray()).boxed().collect(Collectors.toList());
            if (genesMap.containsKey(genesAsArray)) {
                Integer prev = genesMap.get(genesAsArray);
                genesMap.put(genesAsArray, prev + 1);
            } else {
                genesMap.put(genesAsArray, 1);
            }
        }
        averageEnergy = animalCount > 0 ? averageEnergy / animalCount : 0;
        averageChildrenNum = animalCount > 0 ? averageChildrenNum / animalCount : 0;
        averageLifeLength = deaths > 0 ? (double) lifeLengthSummary / deaths : 0;
        int maxFrequency = 0;
        List <Integer> mostFrequentGenes = new ArrayList<>();
        for (Map.Entry<List<Integer>, Integer> entry : genesMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostFrequentGenes = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return new Statistics(
                startingParams,
                animalsList,
                grassList,
                animalCount,
                grassCount,
                averageEnergy,
                averageChildrenNum,
                averageLifeLength,
                genesFrequency.clone(),
                mostFrequentGenes
        );

    }
}
