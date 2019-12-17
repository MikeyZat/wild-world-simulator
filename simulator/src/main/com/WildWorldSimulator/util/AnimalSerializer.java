package com.WildWorldSimulator.util;

import com.WildWorldSimulator.classes.Animal;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AnimalSerializer extends StdSerializer<Animal> {

    public AnimalSerializer() {
        this(null);
    }

    public AnimalSerializer(Class<Animal> animal) {
        super(animal);
    }

    @Override
    public void serialize(
            Animal value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("energy", value.getEnergy());
        jgen.writeNumberField("lifeLength", value.getLifeLength());
        jgen.writeNumberField("childrenNumber", value.getChildren().size());
        jgen.writeFieldName("genes");
        jgen.writeArray(value.getGenes().getGenesArray(), 0, 32);
        jgen.writeArrayFieldStart("position");
        jgen.writeObject(value.getPosition().x);
        jgen.writeObject(value.getPosition().y);
        jgen.writeEndArray();
        jgen.writeEndObject();
    }
}
