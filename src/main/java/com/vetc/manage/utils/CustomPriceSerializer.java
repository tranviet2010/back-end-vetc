package com.vetc.manage.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;
/** 
 * @Author HungVM
 */
@Configuration
public class CustomPriceSerializer extends StdSerializer<String> {
    public CustomPriceSerializer() {
        this(null);
    }

    public CustomPriceSerializer(Class<String> t) {
        super(t);
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (s.equals("0")) s = "---";
        jsonGenerator.writeString(s);
    }
}
