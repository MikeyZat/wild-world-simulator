package com.WildWorldSimulator.util;

import com.WildWorldSimulator.constants.StartingParams;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void getStartingParamsFromJson() {
        try {
            StartingParams startingParams = JsonParser.getStartingParamsFromJson("testJsons/jsonParseGoodData.json");
            assertEquals(80, startingParams.width);
            assertEquals(80, startingParams.height);
            assertEquals(200, startingParams.startingEnergy);
            assertEquals(2, startingParams.everydayEnergyLoss);
            assertEquals(40, startingParams.grassEnergy);
            assertEquals(8, startingParams.jungleWidth);
            assertEquals(8, startingParams.jungleHeight);
            assertEquals(80, startingParams.width);
            assertEquals(100, startingParams.minimumEnergyToCopulate);
        } catch ( JsonParserException e){
            assert false;
        }
    }

    @Test(expected = JsonParserException.class)
    public void getStartingParamsFromJsonWithWrongData() throws JsonParserException {
        JsonParser.getStartingParamsFromJson("jsonParseBadData.json");
    }
}