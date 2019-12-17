package com.WildWorldSimulator.util;

import com.WildWorldSimulator.classes.Grass;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GrassSerializer extends StdSerializer<Grass> {

    public GrassSerializer() {
        this(null);
    }

    public GrassSerializer(Class<Grass> grass) {
        super(grass);
    }

    @Override
    public void serialize(
            Grass value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartArray();
        jgen.writeObject(value.getPosition().x);
        jgen.writeObject(value.getPosition().y);
        jgen.writeEndArray();
    }
}
