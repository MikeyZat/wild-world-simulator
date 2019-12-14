package com.WildWorldSimulator.util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.WildWorldSimulator.constants.StartingParams;

public class JsonParser {

    public static StartingParams getStartingParamsFromJson(String fileName) throws JsonParserException {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(fileName)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);
            int width = ((Number) jsonObject.get("width")).intValue();
            int height = ((Number) jsonObject.get("height")).intValue();
            int startingEnergy = ((Number) jsonObject.get("startEnergy")).intValue();
            int everydayEnergyLoss = ((Number) jsonObject.get("moveEnergy")).intValue();
            int grassEnergy = ((Number) jsonObject.get("plantEnergy")).intValue();
            double jungleRatio = ((Number) jsonObject.get("jungleRatio")).doubleValue();
            int genesLength = 32;
            int genesRange = 8;
            int numberOfAnimals = 10;
            int numberOfGrass = 100;
            int minimumEnergyToCopulate = startingEnergy/2;
            int jungleWidth = (int)(width*jungleRatio);
            int jungleHeight = (int)(height*jungleRatio);

            return new StartingParams(
                    width,
                    height,
                    jungleWidth,
                    jungleHeight,
                    genesLength,
                    genesRange,
                    minimumEnergyToCopulate,
                    startingEnergy,
                    everydayEnergyLoss,
                    grassEnergy,
                    numberOfAnimals,
                    numberOfGrass
            );
        } catch (IOException | ParseException | ClassCastException e) {
            throw new JsonParserException("Błąd wczytywania pliku json", e);
        }
    }
}

class JsonParserException extends Exception {
    public JsonParserException(String message, Exception e) {
        System.out.println(message);
        e.printStackTrace();
    }
}
