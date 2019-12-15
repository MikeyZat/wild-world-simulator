package com.WildWorldSimulator.util;
import com.WildWorldSimulator.constants.Statistics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

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

            return new StartingParams(
                    width,
                    height,
                    startingEnergy,
                    everydayEnergyLoss,
                    grassEnergy,
                    jungleRatio
            );
        } catch (IOException | ParseException | ClassCastException e) {
            throw new JsonParserException("Error while reading JSON file using JsonParser", e);
        }
    }

    public static void writeStatsToJson(List<Statistics> stats, String fileName) {
        JSONArray statsAsJson = new JSONArray();
    }
}
