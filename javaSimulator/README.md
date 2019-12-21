### Customization:

- Starting parameters like:
`width, height, startEnergy, moveEnergy, plantEnergy, jungleRatio` are editable in `parameters.json`. \

- Remaining starting params such as:
`genesLength, genesRange, minimumEnergyToCopulate, numberOfAnimals, numberOfGrass, everydayGrassGain` are hardcoded in `constants/StartingParams.java`
```
 this(
            width,
            height,
            (int)(width*jungleRatio),
            (int)(height*jungleRatio),
            32,
            8,
            startingEnergy/2,
            startingEnergy,
            everydayEnergyLoss,
            grassEnergy,
            30,
            300,
            2
    );
```

Feel free to change their values to see different results.

- Number of iterations (evolution days) is hardcoded in `Simulator.java` (which is main simulator class btw).
```
WorldMap worldMap = new WorldMap(startingParams);
int ITERATIONS = 10000;
```
It's set to `10000` by default but you can change it anytime (for 10000 iterations results.json has ~200MB, be careful when incrementing that number)

- To see results of each day in the console set correspondive flag in `Simulator.runSimulation` to `true`: \
``` Simulator.runSimulation(startingParams, worldMap, ITERATIONS, --> true <--, false); ```

- To save final statistics to finalStatistics.json file set correspondive flag to `true`: \
``` Simulator.runSimulation(startingParams, worldMap, ITERATIONS, true, --> true <--); ```

#### Run:

Best way to run is to compile/run Simulator.java `main` method. You can also run it using the React/Node service provided for visualisation.
